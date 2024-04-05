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
import com.example.myapplication.data.Review;
import com.example.myapplication.data.Taikhoan;

import java.util.ArrayList;
import java.util.List;

public class EditComment extends AppCompatActivity {

    private EditText edtId, edtMovieId, edtUserId, edtComment;
    private Button btnThem, btnSua, btnXoa, btnDs;
    ImageView imagetl;
    Data data;
    ListView listView;
    ArrayList<String> mylist;
    ArrayAdapter<String> myadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_review);

        // Ánh xạ các view từ layout
        edtId = findViewById(R.id.edtid);
        edtMovieId = findViewById(R.id.edtmovieid);
        edtUserId = findViewById(R.id.edtuserid);
        edtComment = findViewById(R.id.edtcomment);
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
                String comment = edtComment.getText().toString();
                Taikhoan user = data.getTKById(userId);
                Movie movie = data.getMovieById(movieId);

                if (user != null && movie != null) {
                    Review review = new Review();
                    review.setMovie_id(movieId);
                    review.setUser_id(userId);
                    review.setComment(comment);
                    data.addReview(review);
                    Toast.makeText(EditComment.this, "Thêm thành công!", Toast.LENGTH_SHORT).show();
                } else {
                    if (user == null) {
                        Toast.makeText(EditComment.this, "User không tồn tại!", Toast.LENGTH_SHORT).show();
                    }
                    if (movie == null) {
                        Toast.makeText(EditComment.this, "Movie không tồn tại!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String commentid = edtId.getText().toString();
                if (!TextUtils.isEmpty(commentid)) {
                    int commentId = Integer.parseInt(commentid);
                    boolean isCommentExists = data.isCommentExists(commentId);
                    if (isCommentExists) {
                        data.deleteComment(commentId);
                        Toast.makeText(EditComment.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(EditComment.this, "Comment không tồn tại", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(EditComment.this, "Vui lòng nhập ID của comment", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String commentText = edtId.getText().toString();
                int movieId = Integer.parseInt(edtMovieId.getText().toString());
                int userId = Integer.parseInt(edtUserId.getText().toString());
                String comment = edtComment.getText().toString();
                if (!TextUtils.isEmpty(commentText)) {
                    int commentId = Integer.parseInt(commentText);
                    Review existingComment = data.getAllReviewsID(commentId);
                    if (existingComment != null) {
                        Taikhoan user = data.getTKById(userId);
                        Movie movie = data.getMovieById(movieId);
                        if (user != null && movie != null) {
                            Review review = new Review();
                            review.setId(commentId);
                            review.setMovie_id(movieId);
                            review.setUser_id(userId);
                            review.setComment(comment);
                            data.updateComment(review);
                            Toast.makeText(EditComment.this, "Sửa thành công!", Toast.LENGTH_SHORT).show();
                        } else {
                            if (user == null) {
                                Toast.makeText(EditComment.this, "User không tồn tại!", Toast.LENGTH_SHORT).show();
                            }
                            if (movie == null) {
                                Toast.makeText(EditComment.this, "Movie không tồn tại!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    } else {
                        Toast.makeText(EditComment.this, "Comment không tồn tại!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(EditComment.this, "Vui lòng nhập ID của bộ phim", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnDs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Review> reviewList = data.getAllReviews();
                mylist.clear();
                for (Review review: reviewList) {
                    String reviewInfo = String.format("ID: %d\nMovieid: %d\nUserid: %d\nComment: %s",
                           review.getId(),review.getMovie_id(),review.getUser_id(),review.getComment());
                    mylist.add(reviewInfo);
                }
                myadapter.notifyDataSetChanged();
                listView.setAdapter(myadapter);
            }
        });
        imagetl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditComment.this, Homeadmin.class);
                startActivity(intent);
            }
        });

    }


}
