package com.example.myapplication.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.FilmAdapter;
import com.example.myapplication.adapter.MovieAdapter;
import com.example.myapplication.data.Data;
import com.example.myapplication.data.Movie;

import java.util.List;

public class ttfilm extends AppCompatActivity {
    ImageView imageserch,imagehome,imagetk;
    TextView tva1,tva2,tva3,tva4;
    RecyclerView r1,r2,r3,r4;
    Data data;
    MovieAdapter adapter1,adapter2,adapter3,adapter4;
    List<Movie> movieList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ttfilm);
        Anhxa();
        Image();
        String tla1 = tva1.getText().toString();
        String tla2 = tva2.getText().toString();
        String tla3 = tva3.getText().toString();
        String tla4 = tva4.getText().toString();
        r1.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        r1.setAdapter(adapter1);
        movieList = data.getMoviesByCategory(tla1);
        adapter1.setData(movieList);

        r2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        r2.setAdapter(adapter2);
        movieList = data.getMoviesByCategory(tla2);
        adapter2.setData(movieList);

        r3.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        r3.setAdapter(adapter3);
        movieList = data.getMoviesByCategory(tla3);
        adapter3.setData(movieList);

        r4.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        r4.setAdapter(adapter4);
        movieList = data.getMoviesByCategory(tla4);
        adapter4.setData(movieList);
    }
    public void Image(){
        imageserch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ttfilm.this,searchfilm_activity.class);
                startActivity(intent);
            }
        });
        imagehome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ttfilm.this,Home_activity.class);
                startActivity(intent);
            }
        });
        imagetk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ttfilm.this,UserActivity.class);
                startActivity(intent);
            }
        });
    }
    public void Anhxa(){
        tva1 = findViewById(R.id.tva1);
        tva2 = findViewById(R.id.tva2);
        tva3 = findViewById(R.id.tva3);
        tva4 = findViewById(R.id.tva4);
        r1 = findViewById(R.id.r1);
        r2 = findViewById(R.id.r2);
        r3 = findViewById(R.id.r3);
        r4 = findViewById(R.id.r4);
        data = new Data(this);
        adapter1 = new MovieAdapter(this);
        adapter2 = new MovieAdapter(this);
        adapter3 = new MovieAdapter(this);
        adapter4 = new MovieAdapter(this);
        imageserch = findViewById(R.id.imagesearch);
        imagehome = findViewById(R.id.imagehome);
        imagetk = findViewById(R.id.imagekh);
    }
}