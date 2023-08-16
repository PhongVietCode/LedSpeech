package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.Fragment.Speech;
import com.example.myapplication.Fragment.Text;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    private EditText edtURL, edtMes;
    private Button btnURL,btnMes;

    private BottomNavigationView bottomNav;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        checkPermissions();
        findViewById(R.id.parent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.closeKeyBoard(MainActivity.this,MainActivity.this);
            }
        });
        replaceFragment(new Speech());
        bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.speechToText){
                    replaceFragment(new Speech());
                }
                else{
                    replaceFragment(new Text());
                }
                return true;
            }
        });
        btnURL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.closeKeyBoard(MainActivity.this,MainActivity.this);
            }
        });
        btnMes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.closeKeyBoard(MainActivity.this,MainActivity.this);
            }
        });
    }

    private boolean checkPermissions() {
        if(checkSelfPermission(Utils.RECORD_PERMISSON) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Utils.RECORD_PERMISSON},Utils.RECORD_REQUESTCODE);
        }
        return true;

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == Utils.RECORD_REQUESTCODE){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(MainActivity.this,"Permission checked", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void initView() {
        edtURL = findViewById(R.id.edtURL);
        btnURL = findViewById(R.id.btnURL);
        edtMes = findViewById(R.id.edtMessage);
        btnMes = findViewById(R.id.btnMessage);
        bottomNav = findViewById(R.id.bottomNav);
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}