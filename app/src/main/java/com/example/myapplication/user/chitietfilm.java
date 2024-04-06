package com.example.myapplication.user;

import static android.view.View.GONE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.adapter.CommentAdapter;
import com.example.myapplication.admin.Editfilmlove;
import com.example.myapplication.admin.Editwatchs;
import com.example.myapplication.data.Data;
import com.example.myapplication.data.Monitor;
import com.example.myapplication.data.Movie;
import com.example.myapplication.data.Rating;
import com.example.myapplication.data.Review;
import com.example.myapplication.data.Taikhoan;
import com.example.myapplication.data.WatchHistory;

import java.util.ArrayList;
import java.util.List;

public class chitietfilm extends AppCompatActivity {
    ImageView imageView, imagetl2;
    TextView titleTextView, tvtitle,descriptionTextView, tvposition, tvtheloai,tvdanhgia;
    Button xemButton, btnbinhluan, btndanhgia,btnsua,btnupdate,btnhuytd,btntd;
    EditText edtbinhluan, edtdanhgia,edtsuadg;
    private String videoUrl, username;
    Data data;
    CommentAdapter adapter;
    List<Review> reviewList;
    RecyclerView recyclerView;
    LinearLayout layoutDanhGia;
    int ratingid = -1;
    String rating_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitietfilm);
        Anhxa();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int idmovie = extras.getInt("MOVIE_ID");
            String title = extras.getString("MOVIE_TITLE");
            String description = extras.getString("MOVIE_DESCRIPTION");
            String img = extras.getString("MOVIE_IMAGE");
            String category = extras.getString("MOVIE_CATEGORY");
            videoUrl = extras.getString("MOVIE_VIDEO");
            username = extras.getString("USERNAME");
            String duration = String.valueOf(extras.getFloat("MOVIE_DURATION"));
            String textToShow = "TK: " + username;
            tvtitle.setText(String.valueOf(title));
            int id_user = data.getUserId(username);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            reviewList = data.AllReviewsID(idmovie);
            adapter.setData(reviewList);
            String rating = String.valueOf(data.getAverageRating(idmovie));
            btnupdate.setVisibility(GONE);
            String ra = "Đánh giá TB: " + rating;
            titleTextView.setText(ra);
            btntd.setVisibility(GONE);
            btnhuytd.setVisibility(GONE);
            Taikhoan user = data.getTKById(id_user);
            Movie movie = data.getMovieById(idmovie);
            Monitor monitor = data.getMonitorByUserAndMovie(user.getIdtk(), movie.getIdmovie());
            if (monitor != null) {
                btnhuytd.setVisibility(View.VISIBLE);
            }
            else{
                btntd.setVisibility(View.VISIBLE);
            }
            Rating rating1 = data.getRatingByUserAndMovie(user.getIdtk(), movie.getIdmovie());
            if (rating1 != null) {
                layoutDanhGia.setVisibility(View.GONE);
                int ratingValue = rating1.getRating();
                ratingid = rating1.getId();
                String ratingString = "Bạn đánh giá phim: " + String.valueOf(ratingValue); // Chuyển giá trị đánh giá thành chuỗi
                tvdanhgia.setText(ratingString);
                edtsuadg.setVisibility(GONE);
            }
            else {
                edtsuadg.setVisibility(GONE);
                tvdanhgia.setVisibility(GONE);
                btnsua.setVisibility(GONE);
            }
            rating_id = String.valueOf(ratingid);

            String mota = "Mô tả: " + description;
            descriptionTextView.setText(mota);
            String theloai = "Thể loại: " + category;
            tvtheloai.setText(theloai);
            String position = "Thời lượng phim: " + duration;
            tvposition.setText(position);
            Glide.with(this)
                    .load(img)
                    .error(R.drawable.logo)
                    .into(imageView);

            btntd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id_movie = idmovie;
                    int iduser = id_user;
                    String love = "true";
                    Monitor monitors = new Monitor();
                    monitors.setMovie_id(id_movie);
                    monitors.setUser_id(id_user);
                    monitors.setMonitor(love);
                    data.addMonitor(monitors);
                    Toast.makeText(chitietfilm.this, "Đã theo dõi thành công!", Toast.LENGTH_SHORT).show();
                    btntd.setVisibility(GONE);
                    btnhuytd.setVisibility(View.VISIBLE);
                }
            });
            btnhuytd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Monitor monitor = data.getMonitorByUserAndMovie(user.getIdtk(), movie.getIdmovie());
                    int monitorid = monitor.getId();
                    data.deleteMonitor(monitorid);
                    Toast.makeText(chitietfilm.this, "Đã hủy theo dõi!", Toast.LENGTH_SHORT).show();
                    btnhuytd.setVisibility(GONE);
                    btntd.setVisibility(View.VISIBLE);
                }
            });
            xemButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id_movie = idmovie;
                    int iduser = id_user;
                    float position = 0;
                    Taikhoan user = data.getTKById(iduser);
                    Movie movie = data.getMovieById(id_movie);
                    if (user != null && movie != null) {
                        WatchHistory existingWatchs = data.getWatchHistoryByUserAndMovie(iduser,id_movie);
                        if (existingWatchs != null) {
                           // Toast.makeText(chitietfilm.this, "Đã được xem", Toast.LENGTH_SHORT).show();
                        } else {
                            WatchHistory watchHistory = new WatchHistory();
                            watchHistory.setMovieId(id_movie);
                            watchHistory.setUserId(iduser);
                            watchHistory.setPosition(position);
                            data.addWatchs(watchHistory);
                            //Toast.makeText(chitietfilm.this, "Thêm ls xem thành công!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        if (user == null) {
                            Toast.makeText(chitietfilm.this, "User không tồn tại!", Toast.LENGTH_SHORT).show();
                        }
                        if (movie == null) {
                            Toast.makeText(chitietfilm.this, "Movie không tồn tại!", Toast.LENGTH_SHORT).show();
                        }
                    }
                    Intent intent = new Intent(chitietfilm.this, filmActivity.class);
                    intent.putExtra("MOVIEID",id_movie);
                    intent.putExtra("USERUD",iduser);
                    intent.putExtra("MOVIE_TITLE", title);
                    intent.putExtra("VIDEO_URL", videoUrl);
                    startActivity(intent);
                }
            });

            btnbinhluan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String binhluan = edtbinhluan.getText().toString();
                    if(binhluan.isEmpty()) {
                        Toast.makeText(chitietfilm.this, "Vui lòng nhập bình luận!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    int id_movie = idmovie;
                    int iduser = id_user;
                    Review review = new Review();
                    review.setMovie_id(id_movie);
                    review.setUser_id(iduser);
                    review.setComment(binhluan);
                    data.addReview(review);
                    reviewList.add(review);
                    adapter.setData(reviewList);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(chitietfilm.this, "Bình luận thành công!", Toast.LENGTH_SHORT).show();


                }
            });
            btndanhgia.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String danhgiaStr = edtdanhgia.getText().toString();

                    if (danhgiaStr.isEmpty()) {
                        Toast.makeText(chitietfilm.this, "Vui lòng nhập đánh giá!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    int danhgia = Integer.parseInt(danhgiaStr);
                    if (danhgia < 0 || danhgia > 10) {
                        Toast.makeText(chitietfilm.this, "Đánh giá phải từ 0 đến 10!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    int iduser = id_user;
                    Rating rating = new Rating();
                    rating.setMovie_id(idmovie);
                    rating.setUser_id(iduser);
                    rating.setRating(danhgia);
                    data.addRating(rating);
                    Toast.makeText(chitietfilm.this, "Đánh giá thành công!", Toast.LENGTH_SHORT).show();
                    titleTextView.setText("");
                    String averageRating = String.valueOf(data.getAverageRating(idmovie));
                    String rating1 = String.valueOf(averageRating);
                    String ra = "Đánh giá TB: " + rating1;
                    titleTextView.setText(ra);
                    edtbinhluan.setText("");
                    edtdanhgia.setText("");
                    tvdanhgia.setVisibility(View.VISIBLE);
                    btnsua.setVisibility(View.VISIBLE);
                    Rating ratingcheck = data.getRatingByUserAndMovie(user.getIdtk(), movie.getIdmovie());
                    if (ratingcheck != null) {
                        layoutDanhGia.setVisibility(GONE);
                        int ratingValue = ratingcheck.getRating();
                        ratingid = ratingcheck.getId();
                        String ratingString = "Bạn đánh giá phim: " + String.valueOf(ratingValue);
                        tvdanhgia.setText(ratingString);
                    }
                    rating_id = String.valueOf(ratingid);

                }
            });
            btnsua.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tvdanhgia.setVisibility(GONE);
                    edtsuadg.setVisibility(View.VISIBLE);
                    btnupdate.setVisibility(View.VISIBLE);
                    btnsua.setVisibility(GONE);
                }
            });
            btnupdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String danhgiaStr = edtsuadg.getText().toString();

                    if (danhgiaStr.isEmpty()) {
                        Toast.makeText(chitietfilm.this, "Vui lòng nhập đánh giá!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    int danhgia = Integer.parseInt(danhgiaStr);
                    if (danhgia < 0 || danhgia > 10) {
                        Toast.makeText(chitietfilm.this, "Đánh giá phải từ 0 đến 10!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    int idrading = Integer.parseInt(rating_id);
                    int iduser = id_user;
                    Rating rating = new Rating();
                    rating.setId(idrading);
                    rating.setMovie_id(idmovie);
                    rating.setUser_id(iduser);
                    rating.setRating(danhgia);
                    data.updateRating(rating);
                    Toast.makeText(chitietfilm.this, "Sửa đánh giá thành công!", Toast.LENGTH_SHORT).show();
                    tvdanhgia.setVisibility(View.VISIBLE);
                    edtsuadg.setVisibility(GONE);
                    btnupdate.setVisibility(GONE);
                    btnsua.setVisibility(View.VISIBLE);
                    tvdanhgia.setText("");
                    titleTextView.setText("");
                    String ra = String.valueOf(data.getAverageRating(idmovie));
                    String radg = "Đánh giá TB: " + ra;
                    titleTextView.setText(radg);
                    Rating rating1 = data.getRatingByUserAndMovie(user.getIdtk(), movie.getIdmovie());
                    if (rating1 != null) {
                        int ratingValue = rating1.getRating();
                        String ratingString = "Bạn đánh giá phim: " + String.valueOf(ratingValue);
                        tvdanhgia.setText(ratingString);
                    }


                }
            });
        }
        trolai();
    }

    public void Anhxa() {
        imageView = findViewById(R.id.image_movie);
        titleTextView = findViewById(R.id.text_title);
        descriptionTextView = findViewById(R.id.text_description);
        xemButton = findViewById(R.id.btnxemfilm);
        recyclerView = findViewById(R.id.recyclerbl);
        data = new Data(this);
        adapter = new CommentAdapter(this);
        tvtitle = findViewById(R.id.tvtitle);
        imagetl2 = findViewById(R.id.imagetl2);
        btnbinhluan = findViewById(R.id.btnbinhluan);
        edtbinhluan = findViewById(R.id.edtbinhluan);
        edtbinhluan=findViewById(R.id.edtbinhluan);
        edtdanhgia = findViewById(R.id.edtdanhgia);
        tvposition = findViewById(R.id.text_watch_position);
        btndanhgia = findViewById(R.id.btndanhgia);
        tvtheloai = findViewById(R.id.text_category);
        layoutDanhGia = findViewById(R.id.iddanhgia);
        btnsua = findViewById(R.id.btnsua1);
        edtsuadg = findViewById(R.id.edtsuadg);
        tvdanhgia = findViewById(R.id.tvdanhgia);
        btnupdate = findViewById(R.id.btnupdate);
        btnhuytd = findViewById(R.id.btnhuy);
        btntd = findViewById(R.id.btntheodoi);
    }
    public void trolai(){
        imagetl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(chitietfilm.this,Home_activity.class);
                startActivity(intent);
            }
        });
    }

}
