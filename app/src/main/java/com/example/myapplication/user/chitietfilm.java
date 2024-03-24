package com.example.myapplication.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.adapter.CommentAdapter;
import com.example.myapplication.adapter.MovieAdapter;
import com.example.myapplication.data.Data;
import com.example.myapplication.data.Movie;
import com.example.myapplication.data.Review;

import java.util.List;

public class chitietfilm extends AppCompatActivity {
    private ImageView imageView;
    private TextView titleTextView;
    private TextView descriptionTextView;
    private Button xemButton;
    private String videoUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitietfilm);
        Anhxa();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String title = extras.getString("MOVIE_TITLE");
            String description = extras.getString("MOVIE_DESCRIPTION");
            String img = extras.getString("MOVIE_IMAGE");
            videoUrl = extras.getString("MOVIE_VIDEO");
            // Hiển thị dữ liệu lên giao diện
            titleTextView.setText(title);
            descriptionTextView.setText(description);
            Glide.with(this).load(img).into(imageView);
            xemButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(chitietfilm.this, filmActivity.class);
                    intent.putExtra("VIDEO_URL", videoUrl);
                    startActivity(intent);
                }
            });
        }
    }
    public void Anhxa(){
        imageView = findViewById(R.id.image_movie);
        titleTextView = findViewById(R.id.text_title);
        descriptionTextView = findViewById(R.id.text_description);
        xemButton = findViewById(R.id.btnxemfilm);

    }
}