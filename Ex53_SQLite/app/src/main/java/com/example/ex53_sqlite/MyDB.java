package com.example.ex53_sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDB  extends SQLiteOpenHelper {
    public MyDB(Context context) {
        super(context, "daily.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table daily(" +
                "dailyDate text primary key, " +
                "content text)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "drop table daily";
        db.execSQL(sql);

        onCreate(db);
    }
}
