package com.example.lab8;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RestaurantHelper extends SQLiteOpenHelper {
    // khai báo tên database và schema
    private static final String DATABASE_NAME = "lunchlist.db";
    private static final int SCHEMA_VERSION = 1;
    // Bổ sung constructor chứa một tham số kiểu Context
    public RestaurantHelper(Context context)
    {
// gọi constructor của SQLiteOpenHelper truyền tên database và chema
        super(context, DATABASE_NAME, null, SCHEMA_VERSION);
    }
    public RestaurantHelper(Context context, String name,
                            SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
// TODO Auto-generated constructor stub
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
// TODO Auto-generated method stub
        db.execSQL("CREATE TABLE restaurants (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, address TEXT, type TEXT);");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// TODO Auto-generated method stub
    }


    public void insert(String name, String address, String type)
    {
        // tao doi tuong du lieu ContentValue
        ContentValues cv = new ContentValues();
// dua cac du lieu vao theo tung cap ten field va value
        cv.put("name", name);
        cv.put("address",address);
        cv.put("type", type);
// yeu cau SQLiteDatabase insert du lieu vao database
        getWritableDatabase().insert("restaurants", "name", cv);
    }

    public Cursor getAll()
    {
        Cursor cur;
        cur = getReadableDatabase().rawQuery("SELECT _id, name, address, type FROM restaurants ORDER BY name", null);
        return (cur);
    }
    public String getName(Cursor c)
    {
// truy cap cot thu 2 la cot name
        return (c.getString(1));
    }
    public String getAddress(Cursor c)
    {
// truy cap cot thu 3 la cot address
        return (c.getString(2));
    }
    public String getType(Cursor c)
    {
// truy cap cot thu 4 la type
        return (c.getString(3));
    }
}
