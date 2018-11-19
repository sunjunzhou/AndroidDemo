package com.example.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "mydb";
    public static final String TABLE_NAME = "friends";
    public static final int DATABASE_VERSION = 1;
    public static final int FRIENDS = 1;
    public static final int FRIENDS_ID = 2;
    public static final String ID = "_id";
    public static final String NAME = "name";
    public static final String AGE = "age";

    public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(_id integer primary key autoincrement,name varchar,age integer)");
        //db.execSQL("insert into "+TABLE_NAME+"values(,'wq',22)");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TABLE_NAME);
        onCreate(db);
    }
}