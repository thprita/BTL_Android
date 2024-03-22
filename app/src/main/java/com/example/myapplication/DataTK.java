package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataTK extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "dstk";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "taikhoan";
    private static final String KEY_ID = "idtk";
    private static final String KEY_NAME = "username";
    private static final String KEY_PASS = "password";
    private static final String KEY_MOBILE = "mobile";

    public DataTK(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_table = "CREATE TABLE " + TABLE_NAME + "(" +
                KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                KEY_NAME + " TEXT," +
                KEY_PASS + " TEXT," +
                KEY_MOBILE + " INTEGER" + ")";
        db.execSQL(create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addTK(Taikhoan tk) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, tk.getUsername());
        values.put(KEY_PASS, tk.getPassword());
        values.put(KEY_MOBILE, tk.getMobile());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }
    public boolean checkLogin(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = { KEY_NAME };
        String selection = KEY_NAME + " = ?" + " AND " + KEY_PASS + " = ?";
        String[] selectionArgs = { username, password };
        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        return count > 0;
    }
}
