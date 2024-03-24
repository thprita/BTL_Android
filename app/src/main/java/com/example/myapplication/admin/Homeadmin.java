package com.example.myapplication.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.myapplication.R;

public class Homeadmin extends AppCompatActivity {
    Button btnmovies,btnuser,btnreviews,btnwatchhistory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeadmin);
        Anhxa();
        Movies();
        Review();

    }
    public void Anhxa(){
        btnmovies = findViewById(R.id.btnmovies);
        btnuser = findViewById(R.id.btnuser);
        btnreviews = findViewById(R.id.btnreviews);
        btnwatchhistory = findViewById(R.id.btnwatchhistory);
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
                Intent intent = new Intent(Homeadmin.this,EditReview.class);
                startActivity(intent);
            }
        });
    }
}