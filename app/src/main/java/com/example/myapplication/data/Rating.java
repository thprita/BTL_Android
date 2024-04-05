package com.example.myapplication.data;

public class Rating {
    private int id;
    private int movie_id;
    private int user_id;
    private int rating;

    public Rating() {
        // Constructor mặc định
    }

    public Rating(int id, int movie_id, int user_id, int rating) {
        this.id = id;
        this.movie_id = movie_id;
        this.user_id = user_id;
        this.rating = rating;
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}