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
import com.example.myapplication.data.Movie;

import java.util.ArrayList;
import java.util.List;

public class Editfilm extends AppCompatActivity {
    EditText edttitle,edtnd,edtimg,edtvideo,edtduration,edtcategory,edtid;
    ImageView imagetl;
    Button btnthem,btnsua,btnxoa,btnds;
    Data data;
    ListView lv;
    ArrayList<String> mylist;
    ArrayAdapter<String> myadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editfilm);
        Anhxa();
        them();
        sua();
        xoa();
        danhsach();
        trove();
       // item();


    }
//    public void item(){
//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                // Lấy dữ liệu từ item được chọn
//                String selectedItem = mylist.get(position);
//
//                // Tách các trường thông tin bộ phim từ chuỗi đã chọn
//                String[] movieInfoParts = selectedItem.split("\n");
//
//                // Lấy ID từ phần tử đầu tiên của chuỗi
//                String idString = movieInfoParts[0].substring(movieInfoParts[0].indexOf(":") + 1).trim();
//                int movieId = Integer.parseInt(idString);
//
//                // Lấy thông tin còn lại từ các phần tử còn lại của chuỗi
//                String title = movieInfoParts[1].substring(movieInfoParts[1].indexOf(":") + 1).trim();
//                String description = movieInfoParts[2].substring(movieInfoParts[2].indexOf(":") + 1).trim();
//                String imageUrl = movieInfoParts[3].substring(movieInfoParts[3].indexOf(":") + 1).trim();
//                String videoUrl = movieInfoParts[4].substring(movieInfoParts[4].indexOf(":") + 1).trim();
//                String watchPositionString = movieInfoParts[5].substring(movieInfoParts[5].indexOf(":") + 1).trim();
//                float watchPosition = Float.parseFloat(watchPositionString);
//
//                // Đổ dữ liệu vào các EditText
//                edtid.setText(String.valueOf(movieId));
//                edttitle.setText(title);
//                edtnd.setText(description);
//                edtimg.setText(imageUrl);
//                edtvideo.setText(videoUrl);
//                edtduration.setText(String.valueOf(watchPosition));
//            }
//        });

  //  }
    public void Anhxa(){
        edttitle = findViewById(R.id.edtidmovie);
        edtnd = findViewById(R.id.edtnd);
        edtimg = findViewById(R.id.edtimg);
        edtvideo = findViewById(R.id.edtvideo);
        edtduration = findViewById(R.id.edtwatch);
        edtcategory = findViewById(R.id.edttheloai);
        edtid = findViewById(R.id.edtid);
        btnthem = findViewById(R.id.btnthem);
        btnsua = findViewById(R.id.btnsua);
        btnxoa = findViewById(R.id.btnxoa);
        btnds = findViewById(R.id.btnds);
        imagetl = findViewById(R.id.tr3);
        lv = findViewById(R.id.listview);
        data = new Data(this);
        //lv = findViewById(R.id.lv);
        mylist = new ArrayList<>();
        myadapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,mylist);
    }
//    public void Reset(){
//        edttitle.setText("");
//        edtnd.setText("");
//        edtimg.setText("");
//        edtvideo.setText("");
//        edtduration.setText("");
//        edtcategory.setText("");
//    }
    public void them(){
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = edttitle.getText().toString();
                String description = edtnd.getText().toString();
                String category = edtcategory.getText().toString();
                String imageUrl = edtimg.getText().toString();
                String videoUrl = edtvideo.getText().toString();
                float duration;
                try {
                    duration = Float.parseFloat(edtduration.getText().toString());
                } catch (NumberFormatException e) {
                    Toast.makeText(Editfilm.this, "Vui lòng nhập đúng định dạng cho Duration", Toast.LENGTH_SHORT).show();
                    return;
                }
                Movie movie = new Movie();
                movie.setTitle(title);
                movie.setDescription(description);
                movie.setCategory(category);
                movie.setImageUrl(imageUrl);
                movie.setVideoUrl(videoUrl);
                movie.setDuration(duration);
                data.addMovie(movie);
                Toast.makeText(Editfilm.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
             //   Reset();
            }
        });
    }
    public void sua(){
        btnsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = edttitle.getText().toString();
                String description = edtnd.getText().toString();
                String category = edtcategory.getText().toString();
                String imageUrl = edtimg.getText().toString();
                String videoUrl = edtvideo.getText().toString();
                float duration;
                try {
                    duration = Float.parseFloat(edtduration.getText().toString());
                } catch (NumberFormatException e) {
                    Toast.makeText(Editfilm.this, "Vui lòng nhập đúng định dạng cho Duration", Toast.LENGTH_SHORT).show();
                    return;
                }
                String movieIdText = edtid.getText().toString();
                if (!TextUtils.isEmpty(movieIdText)) {
                    int movieId = Integer.parseInt(movieIdText);
                    Movie existingMovie = data.getMovieById(movieId);
                    if (existingMovie != null) {
                        Movie movie = new Movie();
                        movie.setIdmovie(movieId);
                        movie.setTitle(title);
                        movie.setDescription(description);
                        movie.setCategory(category);
                        movie.setImageUrl(imageUrl);
                        movie.setVideoUrl(videoUrl);
                        movie.setDuration(duration);
                        data.updateMovie(movie);
                        Toast.makeText(Editfilm.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Editfilm.this, "Bộ phim không tồn tại", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Editfilm.this, "Vui lòng nhập ID của bộ phim", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void xoa(){
        btnxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String movieIdText = edtid.getText().toString();
                if (!TextUtils.isEmpty(movieIdText)) {
                    int movieId = Integer.parseInt(movieIdText);
                    Movie existingMovie = data.getMovieById(movieId);
                    if (existingMovie != null) {
                        data.deleteMovie(movieId);
                        Toast.makeText(Editfilm.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Editfilm.this, "Bộ phim không tồn tại", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Editfilm.this, "Vui lòng nhập ID của bộ phim", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public void danhsach(){
        btnds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Movie> movieList = data.getAllMovies();

                // Xóa dữ liệu cũ trong danh sách hiện có
                mylist.clear();

                // Thêm tiêu đề của mỗi bộ phim vào danh sách hiển thị
                for (Movie movie : movieList) {
                    String movieInfo = String.format("ID: %d\nTitle: %s\nDescription: %s\nCategory: %s\nImage URL: %s\nVideo URL: %s\nDuration: %f",
                            movie.getIdmovie(),movie.getTitle(), movie.getDescription(),movie.getCategory(), movie.getImageUrl(), movie.getVideoUrl(), movie.getDuration());

                    mylist.add(movieInfo);
                }

                // Cập nhật adapter
                myadapter.notifyDataSetChanged();

                // Đặt adapter cho ListView
                lv.setAdapter(myadapter);
            }
        });
    }
    public void trove(){
        imagetl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Editfilm.this, Homeadmin.class);
                startActivity(intent);
            }
        });
    }
}
