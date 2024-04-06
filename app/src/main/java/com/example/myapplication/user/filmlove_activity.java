package com.example.myapplication.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.FilmAdapter;
import com.example.myapplication.data.Data;
import com.example.myapplication.data.Monitor;
import com.example.myapplication.data.Movie;
import com.example.myapplication.data.Taikhoan;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class filmlove_activity extends AppCompatActivity {
    ImageView tl4;
    RecyclerView recyclerViewFilm;
    Data data;
    FilmAdapter adapter;
    TextView tvtext;
    List<Monitor> monitorList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filmlove);
        tl4 = findViewById(R.id.tl4);
        recyclerViewFilm = findViewById(R.id.recyclerlove);
        tvtext = findViewById(R.id.tvtext);
        data = new Data(this);
        adapter = new FilmAdapter(this);
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("USERNAME", "");
        Taikhoan tk = data.getTKByUser(username);
        int monitorid = tk.getIdtk();
        monitorList = data.getAllMonitorsByUserId(monitorid);
        List<Integer> movieIds = new ArrayList<>();
        for (Monitor monitor : monitorList) {
            movieIds.add(monitor.getMovie_id());

        }
        recyclerViewFilm.setLayoutManager(new LinearLayoutManager(filmlove_activity.this));
        recyclerViewFilm.setAdapter(adapter);
        List<Movie> movies = new ArrayList<>();
        for (int movieId : movieIds) {
            Movie movie = data.getMovieById(movieId);
            if (movie != null) {
                movies.add(movie);
            }
        }
        if (movies.isEmpty()) {
            tvtext.setVisibility(View.VISIBLE);
            recyclerViewFilm.setVisibility(View.GONE);
            tvtext.setText("Hiện bạn chưa theo dõi phim nào");
        } else {

            tvtext.setVisibility(View.GONE);
            recyclerViewFilm.setVisibility(View.VISIBLE);
            adapter.setData(movies);
        }
        tl4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(filmlove_activity.this,UserActivity.class);
                startActivity(intent);
            }
        });
    }
}