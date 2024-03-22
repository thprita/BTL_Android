package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login_activity extends AppCompatActivity {
    EditText edtuser,edtpass;
    Button btnlogin;
    TextView tvsignup,tvpass;
    DataTK datatk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtuser = findViewById(R.id.edtuser);
        edtpass = findViewById(R.id.edtpass);
        btnlogin = findViewById(R.id.btnlogin);
        tvsignup = findViewById(R.id.tvsignup);
        tvpass = findViewById(R.id.tvpass);
        datatk = new DataTK(this);
        tvsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login_activity.this, Signup_activity.class);
                startActivity(intent);
            }
        });
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtuser.getText().toString();
                String password = edtpass.getText().toString();
                if (datatk.checkLogin(username, password)) {
                    Intent intent = new Intent(Login_activity.this, Home_activity.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(Login_activity.this, "Tên người dùng hoặc mật khẩu không đúng!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}