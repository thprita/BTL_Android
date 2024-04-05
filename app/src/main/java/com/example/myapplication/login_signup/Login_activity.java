package com.example.myapplication.login_signup;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.admin.Homeadmin;
import com.example.myapplication.data.Data;
import com.example.myapplication.user.Home_activity;

public class Login_activity extends AppCompatActivity {
    EditText edtuser,edtpass;
    Button btnlogin;
    TextView tvpass,tvsignup;
    Data datatk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Anhxa();
        Login();
        Signup();
        Pass();
    }
    public void Anhxa(){
        edtuser = findViewById(R.id.edtuser);
        edtpass = findViewById(R.id.edtpass1);
        btnlogin = findViewById(R.id.btnlogin);
        tvsignup = findViewById(R.id.tvsignup);
        tvpass = findViewById(R.id.tvpass);
        datatk = new Data(this);
    }
    public void Login(){
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtuser.getText().toString();
                String password = edtpass.getText().toString();
                if (datatk.checkLogin(username, password)) {
                    if (username.equals("admin") && password.equals("admin")) {
                        Intent intent = new Intent(Login_activity.this, Homeadmin.class);

                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(Login_activity.this, Home_activity.class);
                        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("USERNAME", username);
                        editor.apply();

                        startActivity(intent);
                    }
                } else {
                    Toast.makeText(Login_activity.this, "Tên người dùng hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void Signup(){
        tvsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login_activity.this, Signup_activity.class);
                startActivity(intent);
            }
        });
    }
    public void Pass(){
        tvpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Login_activity.this);
                View dialogView = getLayoutInflater().inflate(R.layout.activity_pass, null);
                builder.setView(dialogView);
                EditText edtUsername = dialogView.findViewById(R.id.edtPassword);
                Button btnShowPassword = dialogView.findViewById(R.id.btnConfirm);
                TextView tvPassword = dialogView.findViewById(R.id.tvPassword);
                ImageView imagetl = dialogView.findViewById(R.id.imagetl);
                btnShowPassword.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String username = edtUsername.getText().toString();
                        String password = datatk.getPasswordByUsername(username);
                        if (password != null) {
                            tvPassword.setText("Mật khẩu: " + password);
                        } else {
                            tvPassword.setText("Tên người dùng không hợp lệ hoặc không có mật khẩu.");
                        }
                        tvPassword.setVisibility(View.VISIBLE);
                    }
                });
                imagetl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Login_activity.this, Login_activity.class);
                        startActivity(intent);
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }
}