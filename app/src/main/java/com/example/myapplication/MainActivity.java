package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Chuyển sang màn hình tiếp theo
                Intent intent = new Intent(MainActivity.this, Login_activity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);
    }
}