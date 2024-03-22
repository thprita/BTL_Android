package com.example.myapplication.login_signup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.data.Data;
import com.example.myapplication.data.Taikhoan;

public class Signup_activity extends AppCompatActivity {
    EditText edtuser, edtpass, edtpass1, edtphone;
    Button btnsignup;
    TextView tvlogin;
    Data datatk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Anhxa();
        Signup();;
        Login();
    }
    public void Anhxa(){
        edtuser = findViewById(R.id.edtuser);
        edtpass = findViewById(R.id.edtpass);
        edtpass1 = findViewById(R.id.edtpass1);
        edtphone = findViewById(R.id.edtphone);
        btnsignup = findViewById(R.id.btnsignup);
        tvlogin = findViewById(R.id.tvlogin);
        datatk = new Data(this);
    }
    public void Signup(){
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtuser.getText().toString();
                String password = edtpass.getText().toString();
                String confirmPassword = edtpass1.getText().toString();
                String phone = edtphone.getText().toString();

                if (password.equals(confirmPassword)) {
                    Taikhoan tk = new Taikhoan();
                    tk.setUsername(username);
                    tk.setPassword(password);
                    tk.setMobile((phone));

                    datatk.addTK(tk);

                    Toast.makeText(Signup_activity.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Signup_activity.this, "Mật khẩu không khớp!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void Login(){
        tvlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Signup_activity.this, Login_activity.class);
                startActivity(intent);
            }
        });
    }
}
