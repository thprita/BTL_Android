package com.example.myapplication.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.data.Data;
import com.example.myapplication.data.Taikhoan;
import com.example.myapplication.login_signup.Login_activity;
import com.example.myapplication.login_signup.Signup_activity;

public class UserActivity extends AppCompatActivity {
    TableRow tableuser;
    TextView tvuser,tvdx;
    ImageView imageuser,imagehome,imagefilm,imagetk;
    Data data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        data = new Data(this);
        tableuser = findViewById(R.id.tableuser);
        tvuser = findViewById(R.id.tvuser);
        tvdx = findViewById(R.id.tvdx);
        imagehome = findViewById(R.id.imagehome);
        imagefilm = findViewById(R.id.imagefilm);
        imagetk = findViewById(R.id.imagekh);
        imageuser = findViewById(R.id.imageuser);
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("USERNAME", "");
        Taikhoan tk = data.getTKByUser(username);
        String avatar = tk.getAvatar();
        Glide.with(this)
                .load(avatar)
                .placeholder(R.drawable.logo)
                .error(R.drawable.logo)
                .into(imageuser);
        imagehome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserActivity.this, Home_activity.class);
                startActivity(intent);
            }
        });
        imagefilm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserActivity.this,ttfilm.class);
                startActivity(intent);
            }
        });
        tableuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserActivity.this, chitietuser.class);
                startActivity(intent);
            }
        });
        tvuser.setText(username);
        tvdx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserActivity.this, Login_activity.class);
                startActivity(intent);
            }
        });
    }
}
