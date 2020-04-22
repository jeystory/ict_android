package com.example.ex54_sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDB extends SQLiteOpenHelper {
    public MyDB(@Nullable Context context) {
        super(context, "myPlan.db", null, 1);
    }

    // 데이터베이스 테이블 생성 메소드
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table myPlann(" +
                "idx integer primary key autoincrement, "+
                "nalja text not null, plann text not null )";
        db.execSQL(sql);
    }

    // 데이터베이스 업데이트
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 삭제 후 생성 메소드 호출
        String sql = "drop table myPlann";
        db.execSQL(sql);

        onCreate(db);

    }
}

