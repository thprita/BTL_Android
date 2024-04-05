package com.example.myapplication.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.admin.Edituser;
import com.example.myapplication.data.Data;
import com.example.myapplication.data.Taikhoan;
import com.example.myapplication.login_signup.Login_activity;

public class chitietuser extends AppCompatActivity {
    ImageView imagehome,imagefilm,imagetk,imageuser;
    EditText editpass,edtuser,edtTextphone,editavatar;
    TextView textviewuser,tvpass;
    Button btnthaydoi,btnuseruser;
    Data data;
    LinearLayout passwordLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitietuser);
        imagehome = findViewById(R.id.imagehome);
        passwordLayout = findViewById(R.id.passwordLayout);
        btnuseruser = findViewById(R.id.btnsuauser);
        imagefilm = findViewById(R.id.imagefilm);
        imageuser = findViewById(R.id.imageuser);
        data = new Data(this);
        imagetk = findViewById(R.id.iamgetk);
        editpass = findViewById(R.id.editpass);
        tvpass = findViewById(R.id.textpass);
        editavatar = findViewById(R.id.edtimage);
        edtTextphone = findViewById(R.id.editTextPhone);
        edtuser = findViewById(R.id.edtuser);
        textviewuser = findViewById(R.id.textviewuser);
        btnthaydoi = findViewById(R.id.btnthaydoi);
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("USERNAME", "");
        textviewuser.setText(username);
        edtuser.setText(username);
        Taikhoan tk = data.getTKByUser(username);
        edtTextphone.setText(tk.getMobile());
        Integer iduser = tk.getIdtk();
        String pass = tk.getPassword();
        editpass.setText(pass);
        String avatar = tk.getAvatar();
        Glide.with(this)
                .load(avatar)
                .placeholder(R.drawable.logo)
                .error(R.drawable.logo)
                .into(imageuser);
        if (avatar != null && !avatar.isEmpty()) {
            editavatar.setText(avatar);
        } else {
            // Hiển thị một giá trị mặc định hoặc thông báo khác
            editavatar.setText("Chưa có ảnh đại diện");
        }
        vohieu(false);
        passwordLayout.setVisibility(View.GONE);
        btnuseruser.setVisibility(View.GONE);
        btnuseruser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer id = iduser;
                String user = edtuser.getText().toString();
                String pass = editpass.getText().toString();
                String mobile = edtTextphone.getText().toString();
                String avatar = editavatar.getText().toString();
                if (!isValidMobileNumber(mobile)) {
                    Toast.makeText(chitietuser.this, "Số điện thoại phải 10 số", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!TextUtils.isEmpty(user)) {
                    Taikhoan tkcheck = data.getTKById(id);
                    if (tkcheck != null) {
                        Taikhoan tk = new Taikhoan();
                        tk.setIdtk(id);
                        tk.setUsername(user);
                        tk.setPassword(pass);
                        tk.setAvatar(avatar);
                        tk.setMobile(mobile);
                        data.updateTK(tk);
                        Toast.makeText(chitietuser.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                         vohieu(false);
                        passwordLayout.setVisibility(View.GONE);
                        btnuseruser.setVisibility(View.GONE);
                        btnthaydoi.setVisibility(View.VISIBLE);
                        Glide.with(chitietuser.this)
                                .load(avatar)
                                .placeholder(R.drawable.logo)
                                .error(R.drawable.logo)
                                .into(imageuser);
                        textviewuser.setText(user);

                    } else {
                        Toast.makeText(chitietuser.this, "Người dùng không tồn tại", Toast.LENGTH_SHORT).show();
                    }
                    return;
                }
            }
        });
        btnthaydoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passwordLayout.setVisibility(View.VISIBLE);
                vohieu(true);
                btnuseruser.setVisibility(View.VISIBLE);
                btnthaydoi.setVisibility(View.GONE);
            }
        });

        imagefilm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(chitietuser.this, ttfilm.class);
                startActivity(intent);
            }
        });
        imagehome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(chitietuser.this, Home_activity.class);
                startActivity(intent);
            }
        });
        imagetk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(chitietuser.this, UserActivity.class);
                startActivity(intent);
            }
        });
    }
    public void vohieu(boolean enabled){
        edtuser.setEnabled(enabled);
        edtTextphone.setEnabled(enabled);
        editavatar.setEnabled(enabled);
    }
    private boolean isValidMobileNumber(String mobile) {
        return mobile.length() == 10 && mobile.matches("\\d+");
    }

}