package com.example.myapplication.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.ThongBaoAdapter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class tb_activity extends AppCompatActivity {
    TextView tvtb;
    ImageView tl4;
    ListView lvtb;
    List<String> thongbaoList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tb);
        tl4 = findViewById(R.id.tl4);
        tl4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(tb_activity.this,UserActivity.class);
                startActivity(intent);
            }
        });
        lvtb = findViewById(R.id.listView);
        SharedPreferences sharedPreferences = getSharedPreferences("MyTB", Context.MODE_PRIVATE);
        Set<String> thongbaoSet = sharedPreferences.getStringSet("thongbaoSet", new HashSet<String>());
        List<String> thongbaoList = new ArrayList<>(thongbaoSet);
        ThongBaoAdapter adapter = new ThongBaoAdapter(this, thongbaoList);
        lvtb.setAdapter(adapter);
    }
}