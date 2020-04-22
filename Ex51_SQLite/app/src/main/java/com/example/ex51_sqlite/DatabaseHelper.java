package com.example.ex51_sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

// HeperClass 이용하기
// 1. 생성자 처리
// 2. implement method 구현
public class DatabaseHelper extends SQLiteOpenHelper {
    String name ;
    public static int VERSION = 1;
    // 생성자 처리
    public DatabaseHelper(@Nullable Context context, String name) {
        super(context, name, null, VERSION);
        this.name = name;
    }
    // 2. implement method 구현
    // 데이터베이스가 존재하지 않으면 자동으로 호출됨
    @Override
    public void onCreate(SQLiteDatabase db) {

        Log.i("my", "onCreate() 호출");
        String sql = "create table if not exists  emp ( "
                + "_id integer primary key autoincrement ,  "
                + "name text,  "
                + "age integer, "
                + " mobile text)";


    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        Log.i("my", "onOpen() 호출");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 현재 db 버전이 사용되고 있는 SQLiteDatabse 파일의 버전과 다른 경우 자동으로 호출
        Log.i("my", "onUpgrade() 호출 : " + oldVersion + "===>" + newVersion);
        if(newVersion > 1){
            db.execSQL("drop table if exists emp");
        }

    }
}
