package com.example.mylibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class BookActivity extends AppCompatActivity {

    private ImageView imgBook;
    private TextView txtname, txtauthor, txtpage, txtdes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        initView();
        Intent intent = getIntent();
        if(intent != null){
            int id = intent.getIntExtra("bookID", -1);
            if(id != -1){
                MyBook incomingBook = Utils.getInstance().getID(id);
                if(incomingBook != null){
                    setData(incomingBook);
                }
            }
        }
    }

    private void setData(MyBook incomingBook) {
        Glide.with(this)
                .asBitmap()
                .load(incomingBook.getImgURL())
                .into(imgBook);
        txtname.setText(incomingBook.getBookName());
        txtauthor.setText(incomingBook.getBookAuthor());
        txtpage.setText(String.valueOf(incomingBook.getBookPages()));
        txtdes.setText(incomingBook.getShortDes());
    }

    private void initView() {
        imgBook = findViewById(R.id.imgactivity);
        txtname = findViewById(R.id.textBookName);
        txtauthor = findViewById(R.id.textAuthor);
        txtpage = findViewById(R.id.textPage);
        txtdes = findViewById(R.id.textDescription);
    }

}