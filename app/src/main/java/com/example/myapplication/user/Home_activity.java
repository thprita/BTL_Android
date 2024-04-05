package com.example.myapplication.user;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.adapter.MovieAdapter;
import com.example.myapplication.data.Data;
import com.example.myapplication.data.Movie;
import com.example.myapplication.data.Rating;
import com.example.myapplication.data.Review;
import com.example.myapplication.login_signup.Login_activity;
import com.example.myapplication.login_signup.Signup_activity;

import java.util.ArrayList;
import java.util.List;

public class Home_activity extends AppCompatActivity {
    RecyclerView recyclerView;
    ViewFlipper viewFlipper;
    ImageView imagehome,imagefilm,imagetk;
    Data data;
    MovieAdapter adapter;
    List<Movie> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Anhxa();
        ActionViewFlipper();
        Recyclerview();
        imagetk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home_activity.this,UserActivity.class);
                startActivity(intent);
            }
        });
        imagefilm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home_activity.this,ttfilm.class);
                startActivity(intent);
            }
        });
    }

    public void Anhxa() {
        viewFlipper = findViewById(R.id.viewflipper);
        recyclerView = findViewById(R.id.recycleview);
        imagefilm = findViewById(R.id.imagefilm);
        imagehome = findViewById(R.id.imagehome);
        imagetk = findViewById(R.id.imagekh);
        data = new Data(this);
        adapter = new MovieAdapter(this);
    }

    public void Recyclerview() {
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(adapter);
        movieList = data.getAllMovies();
        adapter.setData(movieList);
    }


    public void ActionViewFlipper() {
        List<Rating> topRatings = data.getTop5Ratings();
        List<String> mangquangcao = new ArrayList<>();
        for (Rating rating : topRatings) {
            Movie movie = data.getMovieById(rating.getMovie_id());
            if (movie != null) {
                mangquangcao.add(movie.getImageUrl());
            }
        }
        for (int i = 0; i < mangquangcao.size(); i++) {
            ImageView imageView = new ImageView(getApplicationContext());
            final String imageUrl = mangquangcao.get(i);
            Glide.with(this)
                    .load(mangquangcao.get(i))
                    .into(imageView);

            imageView.setTag(imageUrl);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String imageUrl = (String) v.getTag();
                    for (Movie movie : movieList) {
                        if (imageUrl.equals(movie.getImageUrl())) {
                            SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                            String username = sharedPreferences.getString("USERNAME", "");
                            Intent intent = new Intent(Home_activity.this, chitietfilm.class);
                            intent.putExtra("USERNAME", username);
                            intent.putExtra("MOVIE_ID", movie.getIdmovie());
                            intent.putExtra("MOVIE_TITLE", movie.getTitle());
                            intent.putExtra("MOVIE_IMAGE", movie.getImageUrl());
                            intent.putExtra("MOVIE_VIDEO", movie.getVideoUrl());
                            intent.putExtra("MOVIE_DESCRIPTION", movie.getDescription());
                            intent.putExtra("MOVIE_CATEGORY", movie.getCategory());
                            intent.putExtra("MOVIE_DURATION", movie.getDuration());
                            startActivity(intent);
                            return;

                        }
                    }
                    Toast.makeText(getApplicationContext(), "Không tìm thấy thông tin chi tiết của phim", Toast.LENGTH_SHORT).show();
                }
            });

            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);
        Animation side_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
        Animation side_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);
        viewFlipper.setInAnimation(side_in);
        viewFlipper.setOutAnimation(side_out);
    }

    public String getUser() {
        String username = getIntent().getStringExtra("USERNAME");
        if (username != null && !username.isEmpty()) {
            return username;
        } else {
            return null;
        }
    }

}

