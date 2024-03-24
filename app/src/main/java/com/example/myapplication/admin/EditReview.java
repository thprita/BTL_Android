package com.example.myapplication.admin;

import android.content.Intent;
import android.os.Bundle;
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

import java.util.ArrayList;

public class EditReview extends AppCompatActivity {

    private EditText edtId, edtMovieId, edtUserId, edtRating, edtComment;
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
        edtRating = findViewById(R.id.edtrating);
        edtComment = findViewById(R.id.edtcomment);
        imagetl = findViewById(R.id.tl1);
        btnThem = findViewById(R.id.btnthem);
        btnSua = findViewById(R.id.btnsua);
        btnXoa = findViewById(R.id.btnxoa);
        btnDs = findViewById(R.id.btnds);
        listView = findViewById(R.id.lv);
        data = new Data(this);
        //lv = findViewById(R.id.lv);
        mylist = new ArrayList<>();
        myadapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,mylist);
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy dữ liệu từ các EditText

                int movieId = Integer.parseInt(edtMovieId.getText().toString());
                int userId = Integer.parseInt(edtUserId.getText().toString());
                int rating = Integer.parseInt(edtRating.getText().toString());
                String comment = edtComment.getText().toString();
                Review review = new Review();
                review.setMovie_id(movieId);
                review.setUser_id(userId);
                review.setRating(rating);
                review.setComment(comment);

                data.addReview(review);
                Toast.makeText(EditReview.this, "Thêm thành công!", Toast.LENGTH_SHORT).show();

            }
        });

        // Xử lý sự kiện khi nhấn nút Sửa
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Thực hiện xử lý sửa review trong cơ sở dữ liệu hoặc danh sách review
                // Implement logic ở đây
            }
        });

        // Xử lý sự kiện khi nhấn nút Xóa
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Thực hiện xử lý xóa review khỏi cơ sở dữ liệu hoặc danh sách review
                // Implement logic ở đây
            }
        });

        // Xử lý sự kiện khi nhấn nút Danh sách
        btnDs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển sang màn hình hiển thị danh sách review
                // Implement logic ở đây
            }
        });
        imagetl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditReview.this, Homeadmin.class);
                startActivity(intent);
            }
        });
    }
}
