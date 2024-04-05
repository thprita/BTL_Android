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
import com.example.myapplication.data.Taikhoan;
import com.example.myapplication.user.chitietuser;

import java.util.ArrayList;
import java.util.List;

public class Edituser extends AppCompatActivity {
    EditText edtusername,edtpassword,edtavatar,edtmobile,edtid;
    ImageView tr3;
    Button btnthem,btnsua,btnxoa,btnds;
    Data data;
    ListView lv;
    ArrayList<String> mylist;
    ArrayAdapter<String> myadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edituser);
        Anhxa();
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtusername.getText().toString();
                String pass = edtpassword.getText().toString();
                String avatar = edtavatar.getText().toString();
                String mobile = edtmobile.getText().toString();
                if (!isValidMobileNumber(mobile)) {
                    Toast.makeText(Edituser.this, "Số điện thoại phải 10 số", Toast.LENGTH_SHORT).show();
                    return;
                }
                Taikhoan tk = new Taikhoan();
                tk.setUsername(username);
                tk.setPassword(pass);
                tk.setAvatar(avatar);
                tk.setMobile(mobile);
                data.addTK(tk);
                Toast.makeText(Edituser.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
            }
        });
        // Lắng nghe sự kiện khi nhấn nút xóa
        btnxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userIdText = edtid.getText().toString();
                if (!TextUtils.isEmpty(userIdText)) {
                    int userId = Integer.parseInt(userIdText);
                    Taikhoan tkcheck = data.getTKById(userId);
                    if (tkcheck != null) {
                        data.deleteTK(userId);
                        Toast.makeText(Edituser.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Edituser.this, "Người dùng không tồn tại", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Edituser.this, "Vui lòng nhập ID của người dùng", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtusername.getText().toString();
                String pass = edtpassword.getText().toString();
                String avatar = edtavatar.getText().toString();
                String mobile = edtmobile.getText().toString();
                String userIdText = edtid.getText().toString();
                if (!isValidMobileNumber(mobile)) {
                    Toast.makeText(Edituser.this, "Số điện thoại phải 10 số", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!TextUtils.isEmpty(userIdText)) {
                    int userId = Integer.parseInt(userIdText);
                    Taikhoan tkcheck = data.getTKById(userId);
                    if (tkcheck != null) {
                        Taikhoan tk = new Taikhoan();
                        tk.setIdtk(userId);
                        tk.setUsername(username);
                        tk.setPassword(pass);
                        tk.setAvatar(avatar);
                        tk.setMobile(mobile);
                        data.updateTK(tk);
                        Toast.makeText(Edituser.this, "Sửa thành công", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(Edituser.this, "Người dùng không tồn tại", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Edituser.this, "Vui lòng nhập ID của người dùng", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Taikhoan> tkList = data.getAllTaiKhoan();

                // Xóa dữ liệu cũ trong danh sách hiện có
                mylist.clear();

                // Thêm tiêu đề của mỗi bộ phim vào danh sách hiển thị
                for (Taikhoan tk : tkList) {
                    String tkInfo = String.format("ID: %d\nTài khoản: %s\nMật khẩu: %s\nAvatar: %s\nMobile: %s",
                         tk.getIdtk(),tk.getUsername(),tk.getPassword(),tk.getAvatar(),tk.getMobile());

                    mylist.add(tkInfo);
                }

                // Cập nhật adapter
                myadapter.notifyDataSetChanged();

                // Đặt adapter cho ListView
                lv.setAdapter(myadapter);
            }
        });
        tr3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Edituser.this, Homeadmin.class);
                startActivity(intent);
            }
        });
    }
    public void Anhxa(){
        edtusername = findViewById(R.id.edtusername);
        edtpassword = findViewById(R.id.edtpassword);
        edtavatar = findViewById(R.id.edtavatar);
        edtmobile = findViewById(R.id.edtmobile);
        edtid = findViewById(R.id.edtid);
        btnthem = findViewById(R.id.btnthem);
        btnsua = findViewById(R.id.btnsua);
        btnxoa = findViewById(R.id.btnxoa);
        btnds = findViewById(R.id.btnds);
        tr3 = findViewById(R.id.tr3);
        lv = findViewById(R.id.listview);
        data = new Data(this);
        //lv = findViewById(R.id.lv);
        mylist = new ArrayList<>();
        myadapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,mylist);

    }
    private boolean isValidMobileNumber(String mobile) {
        return mobile.length() == 10 && mobile.matches("\\d+");
    }
}