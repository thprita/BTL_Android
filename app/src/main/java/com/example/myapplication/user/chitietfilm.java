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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.adapter.CommentAdapter;
import com.example.myapplication.adapter.MovieAdapter;
import com.example.myapplication.admin.EditReview;
import com.example.myapplication.data.Data;
import com.example.myapplication.data.Movie;
import com.example.myapplication.data.Review;

import java.util.List;
import java.util.Objects;

public class chitietfilm extends AppCompatActivity {
    private ImageView imageView,imagetl2;
    private TextView titleTextView,tvuser,tvtitle,descriptionTextView;
    private Button xemButton,btnbinhluan;
    EditText edtbinhluan,edtdanhgia;
    private String videoUrl,username;
    Data data;
    CommentAdapter adapter;
    List<Review> reviewList;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitietfilm);
        Anhxa();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int idmovie = getIntent().getIntExtra("MOVIE_ID", 0);
            String title = extras.getString("MOVIE_TITLE");
            String description = extras.getString("MOVIE_DESCRIPTION");
            String img = extras.getString("MOVIE_IMAGE");
            videoUrl = extras.getString("MOVIE_VIDEO");
            username = extras.getString("USERNAME");
            String textToShow = "TK: " + username;
            tvuser.setText(textToShow);
            tvtitle.setText(title);
            int id_username = data.getUserId(username);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            reviewList = data.getAllReviewsID(idmovie);
            adapter.setData(reviewList);
            titleTextView.setText(title);
            int id_user = data.getUserId(username);
            descriptionTextView.setText(description);
            Glide.with(this).load(img).into(imageView);

            xemButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(chitietfilm.this, filmActivity.class);
                    intent.putExtra("MOVIE_TITLE",title);
                    intent.putExtra("VIDEO_URL", videoUrl);
                    startActivity(intent);
                }
            });
            btnbinhluan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String binhluan = edtbinhluan.getText().toString();
                    int danhgia = Integer.parseInt(edtdanhgia.getText().toString());
                    int id_movie = idmovie;
                    int iduser = id_user;
                    Review review = new Review();
                    review.setMovie_id(id_movie);
                    review.setUser_id(iduser);
                    review.setRating(danhgia);
                    review.setComment(binhluan);

                    data.addReview(review);
                    reviewList.add(review);
                    adapter.setData(reviewList);

                    // Thông báo cho RecyclerView biết để cập nhật giao diện
                    adapter.notifyDataSetChanged();
                    Toast.makeText(chitietfilm.this, "Bình luận thành công!", Toast.LENGTH_SHORT).show();

                }
            });
        }
        trolai();
    }

    public void Anhxa(){
        imageView = findViewById(R.id.image_movie);
        titleTextView = findViewById(R.id.text_title);
        descriptionTextView = findViewById(R.id.text_description);
        xemButton = findViewById(R.id.btnxemfilm);
        recyclerView = findViewById(R.id.recyclerbl);
        tvuser = findViewById(R.id.tvuser);
        data = new Data(this);
        adapter = new CommentAdapter(this);
        tvtitle = findViewById(R.id.tvtitle);
        imagetl2= findViewById(R.id.imagetl2);
        btnbinhluan = findViewById(R.id.btnbinhluan);
        edtbinhluan=findViewById(R.id.edtbinhluan);
        edtdanhgia = findViewById(R.id.edtdanhgia);
    }
    public void trolai(){
        imagetl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}