package com.example.myapplication.admin;

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

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.data.Data;
import com.example.myapplication.data.Movie;
import com.example.myapplication.data.Rating;
import com.example.myapplication.data.Taikhoan;

import java.util.ArrayList;
import java.util.List;

public class EditRating extends AppCompatActivity {

    private EditText edtId, edtMovieId, edtUserId, edtRating;
    private Button btnThem, btnSua, btnXoa, btnDs;
    ImageView imagetl;
    Data data;
    ListView listView;
    ArrayList<String> mylist;
    ArrayAdapter<String> myadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_rating);

        // Ánh xạ các view từ layout
        edtId = findViewById(R.id.edtid);
        edtMovieId = findViewById(R.id.edtmovieid);
        edtUserId = findViewById(R.id.edtuserid);
        edtRating = findViewById(R.id.edtvt);
        imagetl = findViewById(R.id.tl1);
        btnThem = findViewById(R.id.btnthem);
        btnSua = findViewById(R.id.btnsua);
        btnXoa = findViewById(R.id.btnxoa);
        btnDs = findViewById(R.id.btnds);
        listView = findViewById(R.id.listview);
        data = new Data(this);
        //lv = findViewById(R.id.lv);
        mylist = new ArrayList<>();
        myadapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,mylist);
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int movieId = Integer.parseInt(edtMovieId.getText().toString());
                int userId = Integer.parseInt(edtUserId.getText().toString());
                int ratingValue = Integer.parseInt(edtRating.getText().toString());
                Taikhoan user = data.getTKById(userId);
                Movie movie = data.getMovieById(movieId);
                if (user != null && movie != null) {
                    Rating existingRating = data.getRatingByUserAndMovie(userId, movieId);
                    if (existingRating != null) {
                        Toast.makeText(EditRating.this, "Đã được đánh giá", Toast.LENGTH_SHORT).show();
                    } else {
                        Rating newRating = new Rating();
                        newRating.setMovie_id(movieId);
                        newRating.setUser_id(userId);
                        newRating.setRating(ratingValue);
                        data.addRating(newRating);
                        Toast.makeText(EditRating.this, "Thêm đánh giá thành công!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if (user == null) {
                        Toast.makeText(EditRating.this, "User không tồn tại!", Toast.LENGTH_SHORT).show();
                    }
                    if (movie == null) {
                        Toast.makeText(EditRating.this, "Movie không tồn tại!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String RatingText = edtId.getText().toString();
                int movieId = Integer.parseInt(edtMovieId.getText().toString());
                int userId = Integer.parseInt(edtUserId.getText().toString());
                int rating = Integer.parseInt(edtRating.getText().toString());
                if (!TextUtils.isEmpty(RatingText)) {
                    int ratingId = Integer.parseInt(RatingText);
                    Rating existingRating = data.getAllRatingsID(ratingId);
                    if (existingRating != null) {
                        Taikhoan user = data.getTKById(userId);
                        Movie movie = data.getMovieById(movieId);
                        if (user != null && movie != null) {
                            Rating check = data.getRatingByUserAndMovie(userId, movieId);
                            if (check != null) {
                                existingRating.setRating(rating);
                                data.updateRating(existingRating);
                                Toast.makeText(EditRating.this, "Đã cập nhật đánh giá", Toast.LENGTH_SHORT).show();
                            } else {
                                Rating rating1 = new Rating();
                                rating1.setId(ratingId);
                                rating1.setMovie_id(movieId);
                                rating1.setUser_id(userId);
                                rating1.setRating(rating);
                                data.updateRating(rating1);
                                Toast.makeText(EditRating.this, "Sửa thành công!", Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            if (user == null) {
                                Toast.makeText(EditRating.this, "User không tồn tại!", Toast.LENGTH_SHORT).show();
                            }
                            if (movie == null) {
                                Toast.makeText(EditRating.this, "Movie không tồn tại!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    } else {
                        Toast.makeText(EditRating.this, "Rating không tồn tại!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(EditRating.this, "Vui lòng nhập ID của rating", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy dữ liệu từ các EditText
                String id = edtId.getText().toString();
                if (!TextUtils.isEmpty(id)) {
                    int ratingId = Integer.parseInt(id);
                    boolean isRatingExists = data.isRatingExists(ratingId);
                    if (isRatingExists) {
                        data.deleteRating(ratingId);
                        Toast.makeText(EditRating.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(EditRating.this, "Đánh giá không tồn tại", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(EditRating.this, "Vui lòng nhập ID đánh giá", Toast.LENGTH_SHORT).show();
                }
            }
        });
       btnDs.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               List<Rating> ratings = data.getAllRating();
               mylist.clear();
               for (Rating rating : ratings) {
                   String reviewInfo = String.format("ID: %d\nMovieid: %d\nUserid: %d\nRating: %d",
                           rating.getId(),rating.getMovie_id(),rating.getUser_id(),rating.getRating());
                   mylist.add(reviewInfo);
               }
               myadapter.notifyDataSetChanged();
               listView.setAdapter(myadapter);
           }
       });


        imagetl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditRating.this, Homeadmin.class);
                startActivity(intent);
            }
        });
    }
}
