package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Signup_activity extends AppCompatActivity {
    EditText edtuser, edtpass, edtpass1, edtphone;
    Button btnsignup;
    TextView tvlogin;
    DataTK datatk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        edtuser = findViewById(R.id.edtuser);
        edtpass = findViewById(R.id.edtpass);
        edtpass1 = findViewById(R.id.edtpass1);
        edtphone = findViewById(R.id.edtphone);
        btnsignup = findViewById(R.id.btnsignup);
        tvlogin = findViewById(R.id.tvlogin);
        datatk = new DataTK(this);

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
                    tk.setMobile(Integer.parseInt(phone));

                    datatk.addTK(tk);

                    Toast.makeText(Signup_activity.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Signup_activity.this, "Mật khẩu không khớp!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tvlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Signup_activity.this, Login_activity.class);
                startActivity(intent);
            }
        });
    }
}
