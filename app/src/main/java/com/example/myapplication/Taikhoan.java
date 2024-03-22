package com.example.myapplication;

public class Taikhoan {
    private int idtk;
    private String username;
    private String password;
    private int mobile;

    public Taikhoan() {
        // Constructor mặc định
    }

    public Taikhoan(int idtk, String username, String password, int mobile) {
        this.idtk = idtk;
        this.username = username;
        this.password = password;
        this.mobile = mobile;
    }

    // Getter và Setter cho idtk
    public int getIdtk() {
        return idtk;
    }

    public void setIdtk(int idtk) {
        this.idtk = idtk;
    }

    // Getter và Setter cho username
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Getter và Setter cho pass
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Getter và Setter cho mobile
    public int getMobile() {
        return mobile;
    }

    public void setMobile(int mobile) {
        this.mobile = mobile;
    }
}

