package com.example.myapplication.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.data.Data;
import com.example.myapplication.data.Movie;
import com.example.myapplication.data.Rating;
import com.example.myapplication.data.Taikhoan;
import com.example.myapplication.data.WatchHistory;

import java.util.ArrayList;
import java.util.List;

public class Editwatchs extends AppCompatActivity {
    private EditText edtId, edtMovieId, edtUserId, edtvt;
    private Button btnThem, btnSua, btnXoa, btnDs;
    ImageView imagetl;
    Data data;
    ListView listView;
    ArrayList<String> mylist;
    ArrayAdapter<String> myadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editwatchs);
        edtId = findViewById(R.id.edtid);
        edtMovieId = findViewById(R.id.edtmovieid);
        edtUserId = findViewById(R.id.edtuserid);
        edtvt = findViewById(R.id.edtvt);
        imagetl = findViewById(R.id.tl1);
        btnThem = findViewById(R.id.btnthem);
        btnSua = findViewById(R.id.btnsua);
        btnXoa = findViewById(R.id.btnxoa);
        btnDs = findViewById(R.id.btnds);
        listView = findViewById(R.id.listview);
        data = new Data(this);
        mylist = new ArrayList<>();
        myadapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,mylist);
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int movieId = Integer.parseInt(edtMovieId.getText().toString());
                int userId = Integer.parseInt(edtUserId.getText().toString());
                float position = Float.parseFloat(edtvt.getText().toString());
                Taikhoan user = data.getTKById(userId);
                Movie movie = data.getMovieById(movieId);
                if (user != null && movie != null) {
                    WatchHistory existingWatchs = data.getWatchHistoryByUserAndMovie(userId, movieId);
                    if (existingWatchs != null) {
                        Toast.makeText(Editwatchs.this, "Đã được xem", Toast.LENGTH_SHORT).show();
                    } else {
                        WatchHistory watchHistory = new WatchHistory();
                        watchHistory.setMovieId(movieId);
                        watchHistory.setUserId(userId);
                        watchHistory.setPosition(position);
                        data.addWatchs(watchHistory);
                        Toast.makeText(Editwatchs.this, "Thêm ls xem thành công!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if (user == null) {
                        Toast.makeText(Editwatchs.this, "User không tồn tại!", Toast.LENGTH_SHORT).show();
                    }
                    if (movie == null) {
                        Toast.makeText(Editwatchs.this, "Movie không tồn tại!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String watchsText = edtId.getText().toString();
                int movieId = Integer.parseInt(edtMovieId.getText().toString());
                int userId = Integer.parseInt(edtUserId.getText().toString());
                float position = Float.parseFloat(edtvt.getText().toString());
                if (!TextUtils.isEmpty(watchsText)) {
                    int watchId = Integer.parseInt(watchsText);
                    WatchHistory existingWatch = data.getAllWatchsID(watchId);
                    if (existingWatch != null) {
                        Taikhoan user = data.getTKById(userId);
                        Movie movie = data.getMovieById(movieId);
                        if (user != null && movie != null) {
                            WatchHistory check = data.getWatchHistoryByUserAndMovie(userId, movieId);
                            if (check != null) {
                                existingWatch.setPosition(position);
                                data.updateWathcs(existingWatch);
                                Toast.makeText(Editwatchs.this, "Đã cập nhật ls xem", Toast.LENGTH_SHORT).show();
                            } else {
                                WatchHistory watchHistory = new WatchHistory();
                                watchHistory.setId(watchId);
                                watchHistory.setMovieId(movieId);
                                watchHistory.setUserId(userId);
                                watchHistory.setPosition(position);
                                data.updateWathcs(watchHistory);
                                Toast.makeText(Editwatchs.this, "Sửa thành công!", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            if (user == null) {
                                Toast.makeText(Editwatchs.this, "User không tồn tại!", Toast.LENGTH_SHORT).show();
                            }
                            if (movie == null) {
                                Toast.makeText(Editwatchs.this, "Movie không tồn tại!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    } else {
                        Toast.makeText(Editwatchs.this, "ls xem không tồn tại!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Editwatchs.this, "Vui lòng nhập ID của ls xem", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = edtId.getText().toString();
                if (!TextUtils.isEmpty(id)) {
                    int watchsId = Integer.parseInt(id);
                    boolean isWatchsExists = data.isWatchHistoryExists(watchsId);
                    if (isWatchsExists) {
                        data.deleteWatchs(watchsId);
                        Toast.makeText(Editwatchs.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Editwatchs.this, "Lịch sử không tồn tại", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Editwatchs.this, "Vui lòng nhập ID ls xem", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnDs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<WatchHistory> watchHistories = data.getAllWatchs();
                mylist.clear();
                for (WatchHistory watchHistory : watchHistories) {
                    String watchsInfo = String.format("ID: %d\nMovieid: %d\nUserid: %d\nWatchs History: %f",
                            watchHistory.getId(),watchHistory.getMovieId(),watchHistory.getUserId(),watchHistory.getPosition());
                    mylist.add(watchsInfo);
                }
                myadapter.notifyDataSetChanged();
                listView.setAdapter(myadapter);
            }
        });
        imagetl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Editwatchs.this, Homeadmin.class);
                startActivity(intent);
            }
        });
    }
}