package com.example.assemalturifi.databaseapp3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//step12
public class DataBase extends SQLiteOpenHelper {
    //step13
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "StudentDatabase";


    //step14
    public DataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //step15
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="CREATE TABLE students " +
                "( id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "firstname TEXT, " +
                "email TEXT ) ";
        db.execSQL(sql);
    }

    //step16
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS students";
        db.execSQL(sql);

        onCreate(db);

    }
}
