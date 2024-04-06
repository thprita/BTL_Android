package com.example.myapplication.data;

public class Monitor {
    private int id;
    private int movie_id;
    private int user_id;
    private String monitor;

    public Monitor(){

    }

    public Monitor(int id, int movie_id, int user_id, String monitor) {
        this.id = id;
        this.movie_id = movie_id;
        this.user_id = user_id;
        this.monitor = monitor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getMonitor() {
        return monitor;
    }

    public void setMonitor(String monitor) {
        this.monitor = monitor;
    }
}
