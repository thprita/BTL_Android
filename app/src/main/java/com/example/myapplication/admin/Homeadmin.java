package com.example.myapplication.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.data.Rating;
import com.example.myapplication.login_signup.Login_activity;

import java.util.HashSet;
import java.util.Set;

public class Homeadmin extends AppCompatActivity {
    EditText edttb;
    Button btnthoat,btntb;
    CardView btnmovies,btnuser,btnreviews,btnrating,cardwwatch,cardmonitor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeadmin);
        Anhxa();
        Movies();
        Review();
        User();
        Rating();
        Watchs();

    }
    public void Anhxa(){
        btnmovies = findViewById(R.id.cardmovie);
        btnuser = findViewById(R.id.carduser);
        btnreviews = findViewById(R.id.cardcomment);
        btnrating = findViewById(R.id.cardrating);
        btnthoat = findViewById(R.id.btnthoat);
        cardwwatch = findViewById(R.id.cardwatch);
        cardmonitor = findViewById(R.id.cardmonitor);
        btntb = findViewById(R.id.btntb);
        edttb = findViewById(R.id.edittb);
        btntb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String thongbao = edttb.getText().toString();

                // Lấy danh sách các thông báo từ SharedPreferences
                SharedPreferences sharedPreferences = getSharedPreferences("MyTB", Context.MODE_PRIVATE);
                Set<String> thongbaoSet = sharedPreferences.getStringSet("thongbaoSet", new HashSet<String>());

                // Thêm thông báo mới vào danh sách
                thongbaoSet.add(thongbao);

                // Lưu danh sách mới vào SharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putStringSet("thongbaoSet", thongbaoSet);
                editor.apply();

                Toast.makeText(Homeadmin.this, "Đã thông báo cho người dùng!", Toast.LENGTH_SHORT).show();
            }
        });

        cardmonitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Homeadmin.this, Editfilmlove.class);
                startActivity(intent);
            }
        });
        btnthoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Homeadmin.this, Login_activity.class);
                startActivity(intent);
            }
        });
    }
    public void Movies(){
        btnmovies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Homeadmin.this,Editfilm.class);
                startActivity(intent);
            }
        });
    }
    public void Review(){
        btnreviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Homeadmin.this, EditComment.class);
                startActivity(intent);
            }
        });
    }
    public void User(){
        btnuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Homeadmin.this,Edituser.class);
                startActivity(intent);
            }
        });
    }
    public void Rating(){
        btnrating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Homeadmin.this,EditRating.class);
                startActivity(intent);
            }
        });
    }
    public void Watchs(){
        cardwwatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Homeadmin.this,Editwatchs.class);
                startActivity(intent);
            }
        });
    }
}