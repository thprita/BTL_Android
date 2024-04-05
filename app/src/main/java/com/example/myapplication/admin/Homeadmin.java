package com.example.myapplication.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.myapplication.R;
import com.example.myapplication.data.Rating;
import com.example.myapplication.login_signup.Login_activity;

public class Homeadmin extends AppCompatActivity {
    Button btnthoat;
    CardView btnmovies,btnuser,btnreviews,btnrating;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeadmin);
        Anhxa();
        Movies();
        Review();
        User();
        Rating();

    }
    public void Anhxa(){
        btnmovies = findViewById(R.id.cardmovie);
        btnuser = findViewById(R.id.carduser);
        btnreviews = findViewById(R.id.cardcomment);
        btnrating = findViewById(R.id.cardrating);
        btnthoat = findViewById(R.id.btnthoat);
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
}