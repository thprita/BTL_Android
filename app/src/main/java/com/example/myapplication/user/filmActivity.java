package com.example.myapplication.user;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

import java.util.ResourceBundle;

public class filmActivity extends AppCompatActivity {
    TextView textView;
    private WebView webView;
    ImageView imageView;
    private ResourceBundle extras;
    private String videoUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film);
        webView = findViewById(R.id.webview);
        imageView = findViewById(R.id.imagechitiet);
        textView = findViewById(R.id.textviewtitle);

        String title = getIntent().getStringExtra("MOVIE_TITLE");
        textView.setText(title);

        // Cấu hình WebView
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        videoUrl = getIntent().getStringExtra("VIDEO_URL");
        if (videoUrl != null && !videoUrl.isEmpty()) {
            webView.loadData(videoUrl, "text/html", "utf-8");
        } else {
            Toast.makeText(this, "Video không khả dụng", Toast.LENGTH_SHORT).show();
            finish();
        }
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}