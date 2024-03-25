package com.example.myapplication.data;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class Data extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "FILM_DATA";
    private static final int DATABASE_VERSION = 1;
    //USer
    private static final String TABLE_USER = "taikhoan";
    private static final String USER_ID = "idtk";
    private static final String USER_NAME = "username";
    private static final String USER_PASS = "password";
    private static final String USER_MOBILE = "mobile";
    //Movies
    private static final String TABLE_MOVIES = "Movies";
    private static final String MOVIE_ID = "movie_id";
    private static final String MOVIE_TITLE = "title";
    private static final String MOVIE_DESCRIPTION = "description";
    private static final String MOVIE_IMAGE_URL = "image_url";
    private static final String MOVIE_VIDEO_URL = "video_url";
    private static final String MOVIE_WATCH_POSITION = "watch_position";

    //Review
    // Reviews
    private static final String TABLE_REVIEWS = "Reviews";
    private static final String REVIEW_ID = "id";
    private static final String REVIEW_MOVIE_ID = "movie_id";
    private static final String REVIEW_USER_ID = "user_id";
    private static final String REVIEW_RATING = "rating";
    private static final String REVIEW_COMMENT = "comment";
    public Data(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_table = "CREATE TABLE " + TABLE_USER + "(" +
                USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                USER_NAME + " TEXT," +
                USER_PASS + " TEXT," +
                USER_MOBILE + " TEXT" + ")";
        db.execSQL(create_table);
        String createTableQuery = "CREATE TABLE " + TABLE_MOVIES + "(" +
                MOVIE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                MOVIE_TITLE + " TEXT NOT NULL, " +
                MOVIE_DESCRIPTION + " TEXT, " +
                MOVIE_IMAGE_URL + " TEXT, " +
                MOVIE_VIDEO_URL + " TEXT, " +
                MOVIE_WATCH_POSITION + " REAL DEFAULT 0)";
        db.execSQL(createTableQuery);
        String create_table_reviews = "CREATE TABLE " + TABLE_REVIEWS + "(" +
                REVIEW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                REVIEW_MOVIE_ID + " INTEGER NOT NULL, " +
                REVIEW_USER_ID + " INTEGER NOT NULL, " +
                REVIEW_RATING + " INTEGER, " +
                REVIEW_COMMENT + " TEXT, " +
                "FOREIGN KEY (" + REVIEW_MOVIE_ID + ") REFERENCES " + TABLE_MOVIES + "(" + MOVIE_ID + ")," +
                "FOREIGN KEY (" + REVIEW_USER_ID + ") REFERENCES " + TABLE_USER + "(" + USER_ID + ")" +
                ")";
        db.execSQL(create_table_reviews);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REVIEWS);
        onCreate(db);
    }

    public void addTK(Taikhoan tk) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USER_NAME, tk.getUsername());
        values.put(USER_PASS, tk.getPassword());
        values.put(USER_MOBILE, tk.getMobile());
        db.insert(TABLE_USER, null, values);
        db.close();
    }
    @SuppressLint("Range")
    public List<Taikhoan> getAllTaiKhoan() {
        List<Taikhoan> tkList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USER, null);
        while (cursor.moveToNext()) {
            Taikhoan tk = new Taikhoan();
            tk.setIdtk(cursor.getInt(cursor.getColumnIndex(USER_ID)));
            tk.setUsername(cursor.getString(cursor.getColumnIndex(USER_NAME)));
            tk.setPassword(cursor.getString(cursor.getColumnIndex(USER_PASS)));
            tk.setMobile(cursor.getString(cursor.getColumnIndex(USER_MOBILE)));
            tkList.add(tk);
        }
        cursor.close();
        db.close();
        return tkList;
    }

    public boolean checkLogin(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = { USER_NAME };
        String selection = USER_NAME + " = ?" + " AND " + USER_PASS + " = ?";
        String[] selectionArgs = { username, password };
        Cursor cursor = db.query(TABLE_USER, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        return count > 0;
    }
    @SuppressLint("Range")
    public int getUserId(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = { USER_ID };
        String selection = USER_NAME + " = ?";
        String[] selectionArgs = { username };
        Cursor cursor = db.query(TABLE_USER, columns, selection, selectionArgs, null, null, null);
        int userId = -1;

        if (cursor.moveToFirst()) {
            userId = cursor.getInt(cursor.getColumnIndex(USER_ID));
        }

        cursor.close();
        db.close();
        return userId;
    }


    @SuppressLint("Range")
    public String getPasswordByUsername(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String password = null;

        String[] columns = { USER_PASS };
        String selection = USER_NAME + " = ?";
        String[] selectionArgs = { username };

        Cursor cursor = db.query(TABLE_USER, columns, selection, selectionArgs, null, null, null);
        if (cursor.moveToFirst()) {
            password = cursor.getString(cursor.getColumnIndex(USER_PASS));
        }
        cursor.close();
        db.close();

        return password;
    }
    public void addMovie(Movie movie) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MOVIE_TITLE, movie.getTitle());
        values.put(MOVIE_DESCRIPTION, movie.getDescription());
        values.put(MOVIE_IMAGE_URL, movie.getImageUrl());
        values.put(MOVIE_VIDEO_URL, movie.getVideoUrl());
        values.put(MOVIE_WATCH_POSITION, movie.getWatchPosition());
        db.insert(TABLE_MOVIES, null, values);
        db.close();
    }
    public void deleteMovie(int movieId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MOVIES, MOVIE_ID + "=?", new String[]{String.valueOf(movieId)});
        db.close();
    }
    public void updateMovie(Movie movie) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MOVIE_TITLE, movie.getTitle());
        values.put(MOVIE_DESCRIPTION, movie.getDescription());
        values.put(MOVIE_IMAGE_URL, movie.getImageUrl());
        values.put(MOVIE_VIDEO_URL, movie.getVideoUrl());
        values.put(MOVIE_WATCH_POSITION, movie.getWatchPosition());
        db.update(TABLE_MOVIES, values, MOVIE_ID + "=?", new String[]{String.valueOf(movie.getIdmovie())});
        db.close();
    }
    @SuppressLint("Range")
    public List<Movie> getAllMovies() {
        List<Movie> movieList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_MOVIES, null);
        while (cursor.moveToNext()) {
            Movie movie = new Movie();
            movie.setIdmovie(cursor.getInt(cursor.getColumnIndex(MOVIE_ID)));
            movie.setTitle(cursor.getString(cursor.getColumnIndex(MOVIE_TITLE)));
            movie.setDescription(cursor.getString(cursor.getColumnIndex(MOVIE_DESCRIPTION)));
            movie.setImageUrl(cursor.getString(cursor.getColumnIndex(MOVIE_IMAGE_URL)));
            movie.setVideoUrl(cursor.getString(cursor.getColumnIndex(MOVIE_VIDEO_URL)));
            movie.setWatchPosition(cursor.getFloat(cursor.getColumnIndex(MOVIE_WATCH_POSITION)));
            movieList.add(movie);
        }
        cursor.close();
        db.close();
        return movieList;
    }
    @SuppressLint("Range")
    public List<Movie> getMoviesByImageUrl(String imageUrl) {
        List<Movie> movieList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {
                MOVIE_ID,
                MOVIE_TITLE,
                MOVIE_DESCRIPTION,
                MOVIE_IMAGE_URL,
                MOVIE_VIDEO_URL,
                MOVIE_WATCH_POSITION
        };
        String selection = MOVIE_IMAGE_URL + "=?";
        String[] selectionArgs = { imageUrl };
        Cursor cursor = db.query(TABLE_MOVIES, columns, selection, selectionArgs, null, null, null);
        while (cursor.moveToNext()) {
            Movie movie = new Movie();
            movie.setIdmovie(cursor.getInt(cursor.getColumnIndex(MOVIE_ID)));
            movie.setTitle(cursor.getString(cursor.getColumnIndex(MOVIE_TITLE)));
            movie.setDescription(cursor.getString(cursor.getColumnIndex(MOVIE_DESCRIPTION)));
            movie.setImageUrl(cursor.getString(cursor.getColumnIndex(MOVIE_IMAGE_URL)));
            movie.setVideoUrl(cursor.getString(cursor.getColumnIndex(MOVIE_VIDEO_URL)));
            movie.setWatchPosition(cursor.getFloat(cursor.getColumnIndex(MOVIE_WATCH_POSITION)));
            movieList.add(movie);
        }
        cursor.close();
        db.close();
        return movieList;
    }

    @SuppressLint("Range")
    public Movie getMovieById(int movieId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_MOVIES, null, MOVIE_ID + "=?", new String[]{String.valueOf(movieId)}, null, null, null);
        Movie movie = null;
        if (cursor != null && cursor.moveToFirst()) {
            movie = new Movie();
            movie.setIdmovie(cursor.getInt(cursor.getColumnIndex(MOVIE_ID)));
            movie.setTitle(cursor.getString(cursor.getColumnIndex(MOVIE_TITLE)));
            movie.setDescription(cursor.getString(cursor.getColumnIndex(MOVIE_DESCRIPTION)));
            movie.setImageUrl(cursor.getString(cursor.getColumnIndex(MOVIE_IMAGE_URL)));
            movie.setVideoUrl(cursor.getString(cursor.getColumnIndex(MOVIE_VIDEO_URL)));
            movie.setWatchPosition(cursor.getFloat(cursor.getColumnIndex(MOVIE_WATCH_POSITION)));
            cursor.close();
        }
        db.close();
        return movie;
    }
    //Review
    public void addReview(Review review) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(REVIEW_MOVIE_ID, review.getMovie_id());
        values.put(REVIEW_USER_ID, review.getUser_id());
        values.put(REVIEW_RATING, review.getRating());
        values.put(REVIEW_COMMENT, review.getComment());
        db.insert(TABLE_REVIEWS, null, values);
        db.close();
    }

    @SuppressLint("Range")
    public List<Review> getAllReviews() {
        List<Review> reviewList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_REVIEWS, null);
        while (cursor.moveToNext()) {
            Review review = new Review();
            review.setId(cursor.getInt(cursor.getColumnIndex(REVIEW_ID)));
            review.setMovie_id(cursor.getInt(cursor.getColumnIndex(REVIEW_MOVIE_ID)));
            review.setUser_id(cursor.getInt(cursor.getColumnIndex(REVIEW_USER_ID)));
            review.setRating(cursor.getInt(cursor.getColumnIndex(REVIEW_RATING)));
            review.setComment(cursor.getString(cursor.getColumnIndex(REVIEW_COMMENT)));
            reviewList.add(review);
        }
        cursor.close();
        db.close();
        return reviewList;
    }
    @SuppressLint("Range")
    public List<Review> getAllReviewsID(int idmovie) {
        List<Review> reviewList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_REVIEWS + " WHERE " + REVIEW_MOVIE_ID + " = ?", new String[]{String.valueOf(idmovie)});
        while (cursor.moveToNext()) {
            Review review = new Review();
            review.setId(cursor.getInt(cursor.getColumnIndex(REVIEW_ID)));
            review.setMovie_id(cursor.getInt(cursor.getColumnIndex(REVIEW_MOVIE_ID)));
            review.setUser_id(cursor.getInt(cursor.getColumnIndex(REVIEW_USER_ID)));
            review.setRating(cursor.getInt(cursor.getColumnIndex(REVIEW_RATING)));
            review.setComment(cursor.getString(cursor.getColumnIndex(REVIEW_COMMENT)));
            reviewList.add(review);
        }
        cursor.close();
        db.close();
        return reviewList;
    }
    @SuppressLint("Range")
    public float getAverageRating() {
        int totalRating = 0;
        int totalReviews = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + REVIEW_RATING + " FROM " + TABLE_REVIEWS, null);
        while (cursor.moveToNext()) {
            int rating = cursor.getInt(cursor.getColumnIndex(REVIEW_RATING));
            totalRating += rating;
            totalReviews++;
        }
        cursor.close();
        db.close();
        if (totalReviews > 0) {
            return (float) totalRating / totalReviews;
        } else {
            return 0;
        }
    }


}
