package com.example.myapplication.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
import com.example.myapplication.data.Monitor;
import com.example.myapplication.data.Movie;
import com.example.myapplication.data.Taikhoan;

import java.util.ArrayList;
import java.util.List;

public class Editfilmlove extends AppCompatActivity {
    EditText edtId, edtMovieId, edtUserId, edtLove;
    Button btnthem, btnsua, btnxoa, btnds;
    ListView listView;
    ImageView imagetl;
    Data data;
    ArrayList<String> mylist;
    ArrayAdapter<String> myadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editlove);
        Anhxa();
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int movieId = Integer.parseInt(edtMovieId.getText().toString());
                int userId = Integer.parseInt(edtUserId.getText().toString());
                String love = edtLove.getText().toString();
                Taikhoan user = data.getTKById(userId);
                Movie movie = data.getMovieById(movieId);
                if (user != null && movie != null) {
                    Monitor existingMonitor = data.getMonitorByUserAndMovie(userId, movieId);
                    if (existingMonitor != null) {
                        Toast.makeText(Editfilmlove.this, "Đã được theo dõi", Toast.LENGTH_SHORT).show();
                    } else {
                        Monitor monitor = new Monitor();
                        monitor.setMovie_id(movieId);
                        monitor.setUser_id(userId);
                        monitor.setMonitor(love);
                        data.addMonitor(monitor);
                        Toast.makeText(Editfilmlove.this, "Thêm thành công!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if (user == null) {
                        Toast.makeText(Editfilmlove.this, "User không tồn tại!", Toast.LENGTH_SHORT).show();
                    }
                    if (movie == null) {
                        Toast.makeText(Editfilmlove.this, "Movie không tồn tại!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String MonitorText = edtId.getText().toString();
                int movieId = Integer.parseInt(edtMovieId.getText().toString());
                int userId = Integer.parseInt(edtUserId.getText().toString());
                String love = edtLove.getText().toString();
                if (!TextUtils.isEmpty(MonitorText)) {
                    int monitorId = Integer.parseInt(MonitorText);
                    Monitor existingMonitor = data.getAllMonitorsID(monitorId);
                    if (existingMonitor != null) {
                        Taikhoan user = data.getTKById(userId);
                        Movie movie = data.getMovieById(movieId);
                        if (user != null && movie != null) {
                            Monitor check = data.getMonitorByUserAndMovie(userId, movieId);
                            if (check != null) {
                                existingMonitor.setMonitor(love);
                                data.updateMonitor(existingMonitor);
                                Toast.makeText(Editfilmlove.this, "Đã cập nhật theo dõi", Toast.LENGTH_SHORT).show();
                            } else {
                                Monitor monitor = new Monitor();
                                monitor.setMovie_id(movieId);
                                monitor.setUser_id(userId);
                                monitor.setMonitor(love);
                                data.updateMonitor(monitor);
                                Toast.makeText(Editfilmlove.this, "Sửa thành công!", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            if (user == null) {
                                Toast.makeText(Editfilmlove.this, "User không tồn tại!", Toast.LENGTH_SHORT).show();
                            }
                            if (movie == null) {
                                Toast.makeText(Editfilmlove.this, "Movie không tồn tại!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    } else {
                        Toast.makeText(Editfilmlove.this, "Theo dõi không tồn tại!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Editfilmlove.this, "Vui lòng nhập ID của theo dõi", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = edtId.getText().toString();
                if (!TextUtils.isEmpty(id)) {
                    int monitorId = Integer.parseInt(id);
                    boolean isMonitorExists = data.isMonitorExists(monitorId);
                    if (isMonitorExists) {
                        data.deleteMonitor(monitorId);
                        Toast.makeText(Editfilmlove.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Editfilmlove.this, "Theo dõi không tồn tại", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Editfilmlove.this, "Vui lòng nhập ID theo dõi", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Monitor> monitors = data.getAllMonitors();
                mylist.clear();
                for (Monitor monitor : monitors) {
                    String monitorInfo = String.format("ID: %d\nMovieid: %d\nUserid: %d\nMonitor: %s",
                            monitor.getId(),monitor.getMovie_id(),monitor.getUser_id(),monitor.getMonitor());
                    mylist.add(monitorInfo);
                }
                myadapter.notifyDataSetChanged();
                listView.setAdapter(myadapter);
            }
        });
        imagetl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Editfilmlove.this, Homeadmin.class);
                startActivity(intent);
            }
        });
    }
    public void Anhxa(){
        edtId = findViewById(R.id.edtid);
        edtMovieId = findViewById(R.id.edtmovieid);
        edtUserId = findViewById(R.id.edtuserid);
        edtLove = findViewById(R.id.edtvt);
        btnthem = findViewById(R.id.btnthem);
        btnsua = findViewById(R.id.btnsua);
        btnxoa = findViewById(R.id.btnxoa);
        btnds = findViewById(R.id.btnds);
        listView = findViewById(R.id.listview);
        imagetl = findViewById(R.id.tl1);
        data = new Data(this);
        //lv = findViewById(R.id.lv);
        mylist = new ArrayList<>();
        myadapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,mylist);
    }
}