package com.example.myapplication.data;

public class Taikhoan {
    private int idtk;
    private String username;
    private String password;
    private String avatar;
    private String mobile;

    public Taikhoan() {
        // Constructor mặc định
    }

    public Taikhoan(int idtk, String username, String password,String avatar, String mobile) {
        this.idtk = idtk;
        this.username = username;
        this.password = password;
        this.avatar = avatar;
        this.mobile = mobile;
    }

    // Getter và Setter cho idtk

    public int getIdtk() {
        return idtk;
    }

    public void setIdtk(int idtk) {
        this.idtk = idtk;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}

