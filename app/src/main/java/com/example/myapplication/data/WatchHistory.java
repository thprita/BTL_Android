package com.example.myapplication.data;

public class WatchHistory {
    private int id;
    private int movieId;
    private int userId;
    private int position;
    private String timestamp;

    // Constructor
    public WatchHistory(int id, int movieId, int userId, int position, String timestamp) {
        this.id = id;
        this.movieId = movieId;
        this.userId = userId;
        this.position = position;
        this.timestamp = timestamp;
    }

    // Getter và Setter cho các trường
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
