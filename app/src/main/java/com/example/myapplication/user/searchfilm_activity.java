package com.example.myapplication.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.FilmAdapter;
import com.example.myapplication.data.Data;
import com.example.myapplication.data.Movie;

import java.util.ArrayList;
import java.util.List;

public class searchfilm_activity extends AppCompatActivity {
    EditText edtSearch;
    RecyclerView recyclerViewFilm;
    Data data;
    FilmAdapter adapter;
    ImageView imagetrfilm;
    List<Movie> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchfilm);
        edtSearch = findViewById(R.id.edtsearch);
        recyclerViewFilm = findViewById(R.id.recyclerviewfilm);
        imagetrfilm = findViewById(R.id.imagetrfilm);
        data = new Data(this);
        adapter = new FilmAdapter(this);

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                // Lấy thông tin từ EditText khi nội dung thay đổi
                String searchText = edtSearch.getText().toString();
                recyclerViewFilm.setLayoutManager(new LinearLayoutManager(searchfilm_activity.this));
                recyclerViewFilm.setAdapter(adapter);
                if (searchText.isEmpty()) {
                    movieList = new ArrayList<>();
                    adapter.setData(movieList);
                } else {

                    movieList = data.getMoviesByTitle(searchText);
                }
                adapter.setData(movieList);
            }
        });
        imagetrfilm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(searchfilm_activity.this,ttfilm.class);
                startActivity(intent);
            }
        });
    }
}