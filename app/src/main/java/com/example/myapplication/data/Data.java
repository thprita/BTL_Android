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
    private static final String USER_AVATAR = "avatar";
    private static final String USER_MOBILE = "mobile";
    //Movies
    private static final String TABLE_MOVIES = "Movies";
    private static final String MOVIE_ID = "movie_id";
    private static final String MOVIE_TITLE = "title";
    private static final String MOVIE_DESCRIPTION = "description";
    private static final String MOVIE_CATEGORY = "category";
    private static final String MOVIE_IMAGE_URL = "image_url";
    private static final String MOVIE_VIDEO_URL = "video_url";
    private static final String MOVIE_DURATION = "duration";


    //Review
    private static final String TABLE_COMMENTS = "Comments";
    private static final String COMMENTS_ID = "id";
    private static final String COMMENTS_MOVIE_ID = "movie_id";
    private static final String COMMENTS_USER_ID = "user_id";
    private static final String COMMENTS_COMMENT = "comment";
    //Rating
    private static final String TABLE_RATINGS = "Ratings";
    private static final String RATINGS_ID = "id";
    private static final String RATINGS_MOVIE_ID = "movie_id";
    private static final String RATINGS_USER_ID = "user_id";
    private static final String RATINGS_RATING = "rating";
    //WatchHistory
    private static final String TABLE_WATCHS = "Watchs";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_MOVIE_ID = "movie_id";
    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_POSITION = "position";
    public static final String COLUMN_TIMESTAMP = "timestamp";
    public Data(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_table = "CREATE TABLE " + TABLE_USER + "(" +
                USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                USER_NAME + " TEXT," +
                USER_PASS + " TEXT," +
                USER_AVATAR + " TEXT," +
                USER_MOBILE + " TEXT" + ")";
        db.execSQL(create_table);
        String createTableQuery = "CREATE TABLE " + TABLE_MOVIES + "(" +
                MOVIE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                MOVIE_TITLE + " TEXT NOT NULL, " +
                MOVIE_DESCRIPTION + " TEXT, " +
                MOVIE_CATEGORY + " TEXT, " +
                MOVIE_IMAGE_URL + " TEXT, " +
                MOVIE_VIDEO_URL + " TEXT, " +
                MOVIE_DURATION + " REAL DEFAULT 0)";
        db.execSQL(createTableQuery);
        String create_table_reviews = "CREATE TABLE " + TABLE_COMMENTS + "(" +
                COMMENTS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COMMENTS_MOVIE_ID + " INTEGER NOT NULL, " +
                COMMENTS_USER_ID + " INTEGER NOT NULL, " +
                COMMENTS_COMMENT + " TEXT, " +
                "FOREIGN KEY (" + COMMENTS_MOVIE_ID + ") REFERENCES " + TABLE_MOVIES + "(" + MOVIE_ID + ") ON DELETE NO ACTION, " +
                "FOREIGN KEY (" + COMMENTS_USER_ID + ") REFERENCES " + TABLE_USER + "(" + USER_ID + ") ON DELETE NO ACTION)";

        db.execSQL(create_table_reviews);
        String create_table_rating = "CREATE TABLE " + TABLE_RATINGS + "(" +
                RATINGS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                RATINGS_MOVIE_ID + " INTEGER NOT NULL, " +
                RATINGS_USER_ID + " INTEGER NOT NULL, " +
                RATINGS_RATING + " INTEGER, " +
                "FOREIGN KEY (" + RATINGS_MOVIE_ID + ") REFERENCES " + TABLE_MOVIES + "(" + MOVIE_ID + ") ON DELETE NO ACTION, " +
                "FOREIGN KEY (" + RATINGS_USER_ID + ") REFERENCES " + TABLE_USER + "(" + USER_ID + ") ON DELETE NO ACTION)";

        db.execSQL(create_table_rating);
        String create_table_watchs = "CREATE TABLE " + TABLE_WATCHS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_MOVIE_ID + " INTEGER NOT NULL," +
                COLUMN_USER_ID + " INTEGER NOT NULL," +
                COLUMN_POSITION + " INTEGER," +
                COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP," +
                "FOREIGN KEY (" + COLUMN_MOVIE_ID + ") REFERENCES Movies(id)," +
                "FOREIGN KEY (" + COLUMN_USER_ID + ") REFERENCES Users(id)" +
                ");";
        db.execSQL(create_table_watchs);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMMENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RATINGS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WATCHS);
        onCreate(db);
    }

    public void addTK(Taikhoan tk) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USER_NAME, tk.getUsername());
        values.put(USER_PASS, tk.getPassword());
        values.put(USER_AVATAR, tk.getAvatar());
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
            tk.setAvatar(cursor.getString(cursor.getColumnIndex(USER_AVATAR)));
            tk.setMobile(cursor.getString(cursor.getColumnIndex(USER_MOBILE)));
            tkList.add(tk);
        }
        cursor.close();
        db.close();
        return tkList;
    }
    public void deleteTK(int userid) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER, USER_ID + "=?", new String[]{String.valueOf(userid)});
        db.close();
    }
    public void updateTK(Taikhoan tk) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USER_NAME,tk.getUsername());
        values.put(USER_PASS,tk.getPassword());
        values.put(USER_AVATAR,tk.getAvatar());
        values.put(USER_MOBILE,tk.getMobile());
        db.update(TABLE_USER, values, USER_ID + "=?", new String[]{String.valueOf(tk.getIdtk())});
        db.close();
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
    public String getUsername(int userId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = { USER_NAME };
        String selection = USER_ID + " = ?";
        String[] selectionArgs = { String.valueOf(userId) };
        Cursor cursor = db.query(TABLE_USER, columns, selection, selectionArgs, null, null, null);
        String username = null;

        if (cursor.moveToFirst()) {
            username = cursor.getString(cursor.getColumnIndex(USER_NAME));
        }

        cursor.close();
        db.close();
        return username;
    }
    @SuppressLint("Range")
    public String getAvatar(String user) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = { USER_AVATAR };
        String selection = USER_NAME + " = ?";
        String[] selectionArgs = { String.valueOf(user) };
        Cursor cursor = db.query(TABLE_USER, columns, selection, selectionArgs, null, null, null);
        String avatar = null;

        if (cursor.moveToFirst()) {
            avatar = cursor.getString(cursor.getColumnIndex(USER_AVATAR));
        }

        cursor.close();
        db.close();
        return avatar;
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
    @SuppressLint("Range")
    public Taikhoan getTKById(int userid) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USER, null, USER_ID + "=?", new String[]{String.valueOf(userid)}, null, null, null);
        Taikhoan tk = null;
        if (cursor != null && cursor.moveToFirst()) {
            tk = new Taikhoan();
            tk.setIdtk(cursor.getInt(cursor.getColumnIndex(USER_ID)));
            tk.setUsername(cursor.getString(cursor.getColumnIndex(USER_NAME)));
            tk.setPassword(cursor.getString(cursor.getColumnIndex(USER_PASS)));
            tk.setAvatar(cursor.getString(cursor.getColumnIndex(USER_AVATAR)));
            tk.setMobile(cursor.getString(cursor.getColumnIndex(USER_MOBILE)));
            cursor.close();
        }
        db.close();
        return tk;
    }
    @SuppressLint("Range")
    public Taikhoan getTKByUser(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USER, null, USER_NAME + "=?", new String[]{String.valueOf(username)}, null, null, null);
        Taikhoan tk = null;
        if (cursor != null && cursor.moveToFirst()) {
            tk = new Taikhoan();
            tk.setIdtk(cursor.getInt(cursor.getColumnIndex(USER_ID)));
            tk.setUsername(cursor.getString(cursor.getColumnIndex(USER_NAME)));
            tk.setPassword(cursor.getString(cursor.getColumnIndex(USER_PASS)));
            tk.setAvatar(cursor.getString(cursor.getColumnIndex(USER_AVATAR)));
            tk.setMobile(cursor.getString(cursor.getColumnIndex(USER_MOBILE)));
            cursor.close();
        }
        db.close();
        return tk;
    }
    //Movie
    public void addMovie(Movie movie) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MOVIE_TITLE, movie.getTitle());
        values.put(MOVIE_DESCRIPTION, movie.getDescription());
        values.put(MOVIE_CATEGORY, movie.getCategory());
        values.put(MOVIE_IMAGE_URL, movie.getImageUrl());
        values.put(MOVIE_VIDEO_URL, movie.getVideoUrl());
        values.put(MOVIE_DURATION, movie.getDuration());
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
        values.put(MOVIE_CATEGORY, movie.getCategory());
        values.put(MOVIE_IMAGE_URL, movie.getImageUrl());
        values.put(MOVIE_VIDEO_URL, movie.getVideoUrl());
        values.put(MOVIE_DURATION, movie.getDuration());
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
            movie.setCategory(cursor.getString(cursor.getColumnIndex(MOVIE_CATEGORY)));
            movie.setImageUrl(cursor.getString(cursor.getColumnIndex(MOVIE_IMAGE_URL)));
            movie.setVideoUrl(cursor.getString(cursor.getColumnIndex(MOVIE_VIDEO_URL)));
            movie.setDuration(cursor.getFloat(cursor.getColumnIndex(MOVIE_DURATION)));
            movieList.add(movie);
        }
        cursor.close();
        db.close();
        return movieList;
    }
    @SuppressLint("Range")
    public List<Movie> getMoviesByTitle(String title) {
        List<Movie> movieList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Truy vấn SQL để lấy danh sách các phim có tiêu đề giống nhau
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_MOVIES + " WHERE " + MOVIE_TITLE + " LIKE ?", new String[]{"%" + title + "%"});

        while (cursor.moveToNext()) {
            Movie movie = new Movie();
            movie.setIdmovie(cursor.getInt(cursor.getColumnIndex(MOVIE_ID)));
            movie.setTitle(cursor.getString(cursor.getColumnIndex(MOVIE_TITLE)));
            movie.setDescription(cursor.getString(cursor.getColumnIndex(MOVIE_DESCRIPTION)));
            movie.setCategory(cursor.getString(cursor.getColumnIndex(MOVIE_CATEGORY)));
            movie.setImageUrl(cursor.getString(cursor.getColumnIndex(MOVIE_IMAGE_URL)));
            movie.setVideoUrl(cursor.getString(cursor.getColumnIndex(MOVIE_VIDEO_URL)));
            movie.setDuration(cursor.getFloat(cursor.getColumnIndex(MOVIE_DURATION)));
            movieList.add(movie);
        }

        cursor.close();
        db.close();
        return movieList;
    }
    @SuppressLint("Range")
    public List<Movie> getMoviesByCategory(String category) {
        List<Movie> movieList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_MOVIES + " WHERE " + MOVIE_CATEGORY + "=?";
        Cursor cursor = db.rawQuery(query, new String[]{category});

        while (cursor.moveToNext()) {
            Movie movie = new Movie();
            movie.setIdmovie(cursor.getInt(cursor.getColumnIndex(MOVIE_ID)));
            movie.setTitle(cursor.getString(cursor.getColumnIndex(MOVIE_TITLE)));
            movie.setDescription(cursor.getString(cursor.getColumnIndex(MOVIE_DESCRIPTION)));
            movie.setCategory(cursor.getString(cursor.getColumnIndex(MOVIE_CATEGORY)));
            movie.setImageUrl(cursor.getString(cursor.getColumnIndex(MOVIE_IMAGE_URL)));
            movie.setVideoUrl(cursor.getString(cursor.getColumnIndex(MOVIE_VIDEO_URL)));
            movie.setDuration(cursor.getFloat(cursor.getColumnIndex(MOVIE_DURATION)));
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
                MOVIE_CATEGORY,
                MOVIE_IMAGE_URL,
                MOVIE_VIDEO_URL,
                MOVIE_DURATION
        };
        String selection = MOVIE_IMAGE_URL + "=?";
        String[] selectionArgs = { imageUrl };
        Cursor cursor = db.query(TABLE_MOVIES, columns, selection, selectionArgs, null, null, null);
        while (cursor.moveToNext()) {
            Movie movie = new Movie();
            movie.setIdmovie(cursor.getInt(cursor.getColumnIndex(MOVIE_ID)));
            movie.setTitle(cursor.getString(cursor.getColumnIndex(MOVIE_TITLE)));
            movie.setDescription(cursor.getString(cursor.getColumnIndex(MOVIE_DESCRIPTION)));
            movie.setCategory(cursor.getString(cursor.getColumnIndex(MOVIE_CATEGORY)));
            movie.setImageUrl(cursor.getString(cursor.getColumnIndex(MOVIE_IMAGE_URL)));
            movie.setVideoUrl(cursor.getString(cursor.getColumnIndex(MOVIE_VIDEO_URL)));
            movie.setDuration(cursor.getFloat(cursor.getColumnIndex(MOVIE_DURATION)));
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
            movie.setCategory(cursor.getString(cursor.getColumnIndex(MOVIE_CATEGORY)));
            movie.setImageUrl(cursor.getString(cursor.getColumnIndex(MOVIE_IMAGE_URL)));
            movie.setVideoUrl(cursor.getString(cursor.getColumnIndex(MOVIE_VIDEO_URL)));
            movie.setDuration(cursor.getFloat(cursor.getColumnIndex(MOVIE_DURATION)));
            cursor.close();
        }
        db.close();
        return movie;
    }
    //Review
    public void addReview(Review review) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COMMENTS_MOVIE_ID, review.getMovie_id());
        values.put(COMMENTS_USER_ID, review.getUser_id());
        values.put(COMMENTS_COMMENT, review.getComment());
        db.insert(TABLE_COMMENTS, null, values);
        db.close();
    }
    public void deleteComment(int commentId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_COMMENTS, COMMENTS_ID + "=?", new String[]{String.valueOf(commentId)});
        db.close();
    }
    public void updateComment(Review review) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COMMENTS_MOVIE_ID,review.getMovie_id());
        values.put(COMMENTS_USER_ID,review.getUser_id());
        values.put(COMMENTS_COMMENT,review.getComment());
        db.update(TABLE_COMMENTS, values, COMMENTS_ID + "=?", new String[]{String.valueOf(review.getId())});
        db.close();
    }
    @SuppressLint("Range")
    public List<Review> getAllReviews() {
        List<Review> reviewList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_COMMENTS, null);
        while (cursor.moveToNext()) {
            Review review = new Review();
            review.setId(cursor.getInt(cursor.getColumnIndex(COMMENTS_ID)));
            review.setMovie_id(cursor.getInt(cursor.getColumnIndex(COMMENTS_MOVIE_ID)));
            review.setUser_id(cursor.getInt(cursor.getColumnIndex(COMMENTS_USER_ID)));
            review.setComment(cursor.getString(cursor.getColumnIndex(COMMENTS_COMMENT)));
            reviewList.add(review);
        }
        cursor.close();
        db.close();
        return reviewList;
    }
    public boolean isCommentExists(int commentId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_COMMENTS + " WHERE " + COMMENTS_ID + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(commentId)});
        boolean exists = (cursor != null && cursor.getCount() > 0);
        if (cursor != null) {
            cursor.close();
        }
        db.close();
        return exists;
    }
    @SuppressLint("Range")
    public Review getAllReviewsID(int idcomment) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_COMMENTS, null, COMMENTS_ID + "=?", new String[]{String.valueOf(idcomment)}, null, null, null);
        Review review =null;
        if (cursor != null && cursor.moveToFirst()) {
            review = new Review();
            review.setId(cursor.getInt(cursor.getColumnIndex(COMMENTS_ID)));
            review.setMovie_id(cursor.getInt(cursor.getColumnIndex(COMMENTS_MOVIE_ID)));
            review.setUser_id(cursor.getInt(cursor.getColumnIndex(COMMENTS_USER_ID)));
            review.setComment(cursor.getString(cursor.getColumnIndex(COMMENTS_COMMENT)));
            cursor.close();
        }
        db.close();
        return review;
    }
    @SuppressLint("Range")
    public List<Review> AllReviewsID(int idmovie) {
        List<Review> reviewList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_COMMENTS, null, COMMENTS_MOVIE_ID + "=?", new String[]{String.valueOf(idmovie)}, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Review review = new Review();
                review.setId(cursor.getInt(cursor.getColumnIndex(COMMENTS_ID)));
                review.setMovie_id(cursor.getInt(cursor.getColumnIndex(COMMENTS_MOVIE_ID)));
                review.setUser_id(cursor.getInt(cursor.getColumnIndex(COMMENTS_USER_ID)));
                review.setComment(cursor.getString(cursor.getColumnIndex(COMMENTS_COMMENT)));
                reviewList.add(review);
            } while (cursor.moveToNext());
        }

        if (cursor != null) {
            cursor.close();
        }

        db.close();
        return reviewList;
    }
    @SuppressLint("Range")
    public float getAverageRating(int movieId) {
        int totalRating = 0;
        int totalReviews = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + RATINGS_RATING + " FROM " + TABLE_RATINGS +
                " WHERE " + RATINGS_MOVIE_ID + " = ?", new String[]{String.valueOf(movieId)});
        while (cursor.moveToNext()) {
            int rating = cursor.getInt(cursor.getColumnIndex(RATINGS_RATING));
            totalRating += rating;
            totalReviews++;
        }
        cursor.close();
        db.close();
        if (totalReviews > 0) {
            float averageRating = (float) totalRating / totalReviews;
            return Math.round(averageRating * 100.0) / 100.0f;
        } else {
            return 0;
        }
    }


    //Rating
        public void addRating(Rating rating) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(RATINGS_MOVIE_ID, rating.getMovie_id());
            values.put(RATINGS_USER_ID,rating.getUser_id());
            values.put(RATINGS_RATING, rating.getRating());
            db.insert(TABLE_RATINGS, null, values);
            db.close();
        }
    public void deleteRating(int ratingId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_RATINGS, RATINGS_ID + "=?", new String[]{String.valueOf(ratingId)});
        db.close();
    }

    public void updateRating(Rating rating) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(RATINGS_MOVIE_ID,rating.getMovie_id());
        values.put(RATINGS_USER_ID,rating.getUser_id());
        values.put(RATINGS_RATING,rating.getRating());
        db.update(TABLE_RATINGS, values, RATINGS_ID + "=?", new String[]{String.valueOf(rating.getId())});
        db.close();
    }

    @SuppressLint("Range")
    public List<Rating> getAllRating() {
        List<Rating> ratingsList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_RATINGS, null);
        while (cursor.moveToNext()) {
            Rating rating = new Rating();
            rating.setId(cursor.getInt(cursor.getColumnIndex(RATINGS_ID)));
            rating.setMovie_id(cursor.getInt(cursor.getColumnIndex(RATINGS_MOVIE_ID)));
            rating.setUser_id(cursor.getInt(cursor.getColumnIndex(RATINGS_USER_ID)));
            rating.setRating(cursor.getInt(cursor.getColumnIndex(RATINGS_RATING)));
            ratingsList.add(rating);
        }
        cursor.close();
        db.close();
        return ratingsList;
    }

    @SuppressLint("Range")
    public Rating getAllRatingsID(int idrating) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_RATINGS + " WHERE " + RATINGS_ID + " =?", new String[]{String.valueOf(idrating)});
        Rating rating = null;
        if (cursor != null && cursor.moveToFirst()) {
            rating = new Rating();
            rating.setId(cursor.getInt(cursor.getColumnIndex(RATINGS_ID)));
            rating.setMovie_id(cursor.getInt(cursor.getColumnIndex(RATINGS_MOVIE_ID)));
            rating.setUser_id(cursor.getInt(cursor.getColumnIndex(RATINGS_USER_ID)));
            rating.setRating(cursor.getInt(cursor.getColumnIndex(RATINGS_RATING)));
            cursor.close();
        }
        db.close();
        return rating;
    }
    public boolean isRatingExists(int ratingId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_RATINGS + " WHERE " + RATINGS_ID + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(ratingId)});
        boolean exists = (cursor != null && cursor.getCount() > 0);
        if (cursor != null) {
            cursor.close();
        }
        db.close();
        return exists;
    }
    @SuppressLint("Range")
    public Rating getRatingByUserAndMovie(int userId, int movieId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Rating rating = null;
        Cursor cursor = db.query(TABLE_RATINGS, null,
                RATINGS_USER_ID + " = ? AND " + RATINGS_MOVIE_ID + " = ?",
                new String[]{String.valueOf(userId), String.valueOf(movieId)},
                null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            rating = new Rating();
            rating.setId(cursor.getInt(cursor.getColumnIndex(RATINGS_ID)));
            rating.setMovie_id(cursor.getInt(cursor.getColumnIndex(RATINGS_MOVIE_ID)));
            rating.setUser_id(cursor.getInt(cursor.getColumnIndex(RATINGS_USER_ID)));
            rating.setRating(cursor.getInt(cursor.getColumnIndex(RATINGS_RATING)));
            cursor.close();
        }
        db.close();
        return rating;
    }
    @SuppressLint("Range")
    public List<Rating> getTop5Ratings() {
        List<Rating> topRatings = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_RATINGS + " ORDER BY " + RATINGS_RATING + " DESC LIMIT 5", null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Rating rating = new Rating();
                rating.setId(cursor.getInt(cursor.getColumnIndex(RATINGS_ID)));
                rating.setMovie_id(cursor.getInt(cursor.getColumnIndex(RATINGS_MOVIE_ID)));
                rating.setUser_id(cursor.getInt(cursor.getColumnIndex(RATINGS_USER_ID)));
                rating.setRating(cursor.getInt(cursor.getColumnIndex(RATINGS_RATING)));
                topRatings.add(rating);
            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return topRatings;
    }

}
