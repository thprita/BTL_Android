package com.example.myapplication.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
import android.widget.AdapterView;
public class Editfilm extends AppCompatActivity {
    EditText edttitle,edtnd,edtimg,edtvideo,edtwatch,edtid;
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
        item();


    }
    public void item(){
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Lấy dữ liệu từ item được chọn
                String selectedItem = mylist.get(position);

                // Tách các trường thông tin bộ phim từ chuỗi đã chọn
                String[] movieInfoParts = selectedItem.split("\n");

                // Lấy ID từ phần tử đầu tiên của chuỗi
                String idString = movieInfoParts[0].substring(movieInfoParts[0].indexOf(":") + 1).trim();
                int movieId = Integer.parseInt(idString);

                // Lấy thông tin còn lại từ các phần tử còn lại của chuỗi
                String title = movieInfoParts[1].substring(movieInfoParts[1].indexOf(":") + 1).trim();
                String description = movieInfoParts[2].substring(movieInfoParts[2].indexOf(":") + 1).trim();
                String imageUrl = movieInfoParts[3].substring(movieInfoParts[3].indexOf(":") + 1).trim();
                String videoUrl = movieInfoParts[4].substring(movieInfoParts[4].indexOf(":") + 1).trim();
                String watchPositionString = movieInfoParts[5].substring(movieInfoParts[5].indexOf(":") + 1).trim();
                float watchPosition = Float.parseFloat(watchPositionString);

                // Đổ dữ liệu vào các EditText
                edtid.setText(String.valueOf(movieId));
                edttitle.setText(title);
                edtnd.setText(description);
                edtimg.setText(imageUrl);
                edtvideo.setText(videoUrl);
                edtwatch.setText(String.valueOf(watchPosition));
            }
        });

    }
    public void Anhxa(){
        edttitle = findViewById(R.id.edtidmovie);
        edtnd = findViewById(R.id.edtnd);
        edtimg = findViewById(R.id.edtimg);
        edtvideo = findViewById(R.id.edtvideo);
        edtwatch = findViewById(R.id.edtwatch);
        edtid = findViewById(R.id.edtid);
        btnthem = findViewById(R.id.btnthem);
        btnsua = findViewById(R.id.btnsua);
        btnxoa = findViewById(R.id.btnxoa);
        btnds = findViewById(R.id.btnds);
        imagetl = findViewById(R.id.imgeditfilm);
        lv = findViewById(R.id.lv);
        data = new Data(this);
        //lv = findViewById(R.id.lv);
        mylist = new ArrayList<>();
        myadapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,mylist);
    }
    public void Reset(){
        edttitle.setText("");
        edtnd.setText("");
        edtimg.setText("");
        edtvideo.setText("");
        edtwatch.setText("");
    }
    public void them(){
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = edttitle.getText().toString();
                String description = edtnd.getText().toString();
                String imageUrl = edtimg.getText().toString();
                String videoUrl = edtvideo.getText().toString();
                float watchPosition = Float.parseFloat(edtwatch.getText().toString());

                // Tạo một đối tượng Movies mới
                Movie movie = new Movie();
                movie.setTitle(title);
                movie.setDescription(description);
                movie.setImageUrl(imageUrl);
                movie.setVideoUrl(videoUrl);
                movie.setWatchPosition(watchPosition);
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
                int movieId = Integer.parseInt(edtid.getText().toString());
                String title = edttitle.getText().toString();
                String description = edtnd.getText().toString();
                String imageUrl = edtimg.getText().toString();
                String videoUrl = edtvideo.getText().toString();
                float watchPosition = Float.parseFloat(edtwatch.getText().toString());

                // Kiểm tra xem bộ phim có tồn tại trong cơ sở dữ liệu không
                Movie existingMovie = data.getMovieById(movieId);
                if (existingMovie != null) {
                    // Bộ phim tồn tại, thực hiện sửa thông tin
                    Movie movie = new Movie();
                    movie.setIdmovie(movieId);
                    movie.setTitle(title);
                    movie.setDescription(description);
                    movie.setImageUrl(imageUrl);
                    movie.setVideoUrl(videoUrl);
                    movie.setWatchPosition(watchPosition);
                    data.updateMovie(movie);
                    Toast.makeText(Editfilm.this, "Sửa thành công", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(Editfilm.this, "Bộ phim không tồn tại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void xoa(){
        btnxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               int movieId = Integer.parseInt(edtid.getText().toString());

              data.deleteMovie(movieId);
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
                    String movieInfo = String.format("ID: %d\nTitle: %s\nDescription: %s\nImage URL: %s\nVideo URL: %s\nWatch Position: %f",
                            movie.getIdmovie(),movie.getTitle(), movie.getDescription(), movie.getImageUrl(), movie.getVideoUrl(), movie.getWatchPosition());

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
