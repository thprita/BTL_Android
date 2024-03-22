package com.example.myapplication.user;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;

public class chitietfilm extends AppCompatActivity {
    private ImageView imageView;
    private TextView titleTextView;
    private TextView descriptionTextView;
    private Button xemButton;
    WebView webView;
    VideoView videoView;
    private String videoUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitietfilm);
        imageView = findViewById(R.id.image_movie);
        titleTextView = findViewById(R.id.text_title);
        descriptionTextView = findViewById(R.id.text_description);
        xemButton = findViewById(R.id.btnxemfilm);

        webView  = findViewById(R.id.webview);
        // Nhận dữ liệu từ Intent
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
            WebSettings webSettings = webView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webView.setWebChromeClient(new WebChromeClient());
            xemButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (videoUrl != null && !videoUrl.isEmpty()) {
                        // Nếu videoUrl có giá trị, thì phát video
                        webView.loadData(videoUrl, "text/html", "utf-8");
                    } else {
                        // Nếu videoUrl không có giá trị, hiển thị thông báo
                        Toast.makeText(chitietfilm.this, "Video không khả dụng", Toast.LENGTH_SHORT).show();
                    }
                }
            });



        }
    }

//    public void Video(){
//        xemButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int rawResourceId = getResources().getIdentifier("h1", "raw", getPackageName());
//                Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + rawResourceId);
//                videoView.setVideoURI(uri);
//                MediaController mediaController = new MediaController(chitietfilm.this);
//                mediaController.setAnchorView(videoView);
//                videoView.setMediaController(mediaController);
//
//                // Bắt đầu phát video
//                videoView.start();
//            }
//        });
   // }
}