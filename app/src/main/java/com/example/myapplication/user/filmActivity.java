package com.example.myapplication.user;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class filmActivity extends AppCompatActivity {
    private WebView webView;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film);
        webView = findViewById(R.id.webview);
        imageView = findViewById(R.id.imagechitiet);
        // Cấu hình WebView
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        String videoUrl = getIntent().getStringExtra("VIDEO_URL");
        if (videoUrl != null && !videoUrl.isEmpty()) {
            // Load videoUrl vào WebView
            webView.loadData(videoUrl, "text/html", "utf-8");
        } else {
            Toast.makeText(this, "Video không khả dụng", Toast.LENGTH_SHORT).show();
            finish(); // Đóng activity nếu không có videoUrl
        }
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}