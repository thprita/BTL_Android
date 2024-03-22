package com.example.myapplication.data;
public class Movie {
    private int idmovie;
    private String title;



    private String description;
    private String imageUrl;
    private String videoUrl;
    private float watchPosition;
    public Movie(){

    }
    public Movie(String imageUrl, String title) {
        this.imageUrl = imageUrl;
        this.title = title;
    }
    public Movie(int idmovie, String title, String description, String imageUrl, String videoUrl, float watchPosition) {
        this.idmovie = idmovie;
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.videoUrl = videoUrl;
        this.watchPosition = watchPosition;
    }


    // Getters and setters
    public int getIdmovie() {
        return idmovie;
    }

    public void setIdmovie(int idmovie) {
        this.idmovie = idmovie;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public float getWatchPosition() {
        return watchPosition;
    }

    public void setWatchPosition(float watchPosition) {
        this.watchPosition = watchPosition;
    }
}
