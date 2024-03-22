package com.example.myapplication.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.adapter.MovieAdapter;
import com.example.myapplication.data.Data;
import com.example.myapplication.data.Movie;

import java.util.ArrayList;
import java.util.List;

public class Home_activity extends AppCompatActivity {
    RecyclerView recyclerView;
    ViewFlipper viewFlipper;
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
    }
    public void Anhxa(){
        viewFlipper = findViewById(R.id.viewflipper);
        recyclerView = findViewById(R.id.recycleview);
        data = new Data(this);
        adapter = new MovieAdapter(this);
    }
    public void Recyclerview(){
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        movieList = data.getAllMovies();
        adapter.setData(movieList);
    }
    public void ActionViewFlipper() {
        List<String> mangquangcao = new ArrayList<>();
        mangquangcao.add("https://st.nettruyenff.com/data/comics/188/dai-quan-gia-la-ma-hoang-904.jpg");
        mangquangcao.add("https://st.nettruyenff.com/data/comics/131/dai-vuong-tha-mang-7670.jpg");
        mangquangcao.add("https://st.nettruyenff.com/data/comics/161/ta-la-ta-de-3254.jpg");

        for (String imageUrl : mangquangcao) {
            ImageView imageView = new ImageView(getApplicationContext());
            Glide.with(this)
                    .load(imageUrl)
                    .into(imageView);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);
        Animation side_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
        Animation side_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);
        viewFlipper.setInAnimation(side_in);
        viewFlipper.setOutAnimation(side_out);
    }
}
