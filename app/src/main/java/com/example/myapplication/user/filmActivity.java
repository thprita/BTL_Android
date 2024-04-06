package com.example.myapplication.user;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class filmActivity extends AppCompatActivity {
    private TextView textView;
    private WebView webView;
    private ImageView imageView;
    private String videoUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film);

        textView = findViewById(R.id.textviewtitle);
        webView = findViewById(R.id.webview);
        imageView = findViewById(R.id.tl4);

        String title = getIntent().getStringExtra("MOVIE_TITLE");
        textView.setText(title);

        // Cấu hình WebView
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new MyChrome());

        videoUrl = getIntent().getStringExtra("VIDEO_URL");
        if (videoUrl != null && !videoUrl.isEmpty()) {
            webView.loadData(videoUrl, "text/html", "utf-8");
        } else {
            Toast.makeText(this, "Video không khả dụng", Toast.LENGTH_SHORT).show();
            finish();
        }
        webView.loadUrl("javascript:getCurrentTime()");
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private class MyChrome extends WebChromeClient {
        View fullscreen = null;

        @Override
        public void onHideCustomView() {
            fullscreen.setVisibility(View.GONE);
            webView.setVisibility(View.VISIBLE);
        }

        @Override
        public void onShowCustomView(View view, CustomViewCallback callback) {
            webView.setVisibility(View.GONE);

            if (fullscreen != null) {
                ((FrameLayout) getWindow().getDecorView()).removeView(fullscreen);
            }

            fullscreen = view;
            ((FrameLayout) getWindow().getDecorView()).addView(fullscreen, new FrameLayout.LayoutParams(-1, -1));
            fullscreen.setVisibility(View.VISIBLE);
        }
    }
}
