package com.example.mylibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentReference;

public class MainActivity extends AppCompatActivity {

    private Button btnAllBooks, btnAddBooks, btnAboutUs, btnFindBooks;
    private EditText edtFindBooks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();


        btnAllBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                @SuppressLint("IntentWithNullActionLaunch") Intent intent = new Intent(MainActivity.this,AllBookActivity.class);
                startActivity(intent);
            }
        });
        btnAddBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                @SuppressLint("IntentWithNullActionLaunch") Intent intent = new Intent(MainActivity.this,AddBook.class);
                startActivity(intent);
            }
        });
        btnFindBooks.setOnClickListener(new View.OnClickListener() {
            // todo: find book in all book or in database.
            @Override
            public void onClick(View view) {
                if(!edtFindBooks.getText().toString().equals("")){
                     Utils.getInstance().getBook();
                }
            }
        });
        btnAboutUs.setOnClickListener(new View.OnClickListener() {
            //todo: add about us page
            @Override
            public void onClick(View view) {

            }
        });
        btnFindBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edtFindBooks.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this,"Empty book's name", Toast.LENGTH_SHORT).show();
                }
                else{
                    checkBook(String.valueOf(edtFindBooks.getText()));
                }

            }


        });
    }
    private void checkBook(String text) {
        //todo: check data on database
        Toast.makeText(this,"Update soon", Toast.LENGTH_SHORT).show();

    }
    private void initView() {
        btnAllBooks = findViewById(R.id.btnAllbook);
        btnAddBooks = findViewById(R.id.btnAddbook);
        btnAboutUs = findViewById(R.id.btnAboutus);
        btnFindBooks = findViewById(R.id.btnFindBook);
        edtFindBooks = findViewById(R.id.edtFindBook);
    }
}