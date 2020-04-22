package com.example.ex52_sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

// SQLiteOpenHelper 상속 받기,
// 기본 생성자가 없으므로 Context 받는 생성자 만들기
public class MyDB  extends SQLiteOpenHelper {

    public MyDB(@Nullable Context context) {
        super(context, "phonelist.db", null, 1);
    }

    // 테이블 만드는 메소드
    // 생성자에저 지정한 DB 이름이 없으면 자동으로 onCreate()이동해서 DB를 만들어 주도록 한다.
    @Override
    public void onCreate(SQLiteDatabase db) {
        // 테이블 생성
        String sql = "create table p_list("+
                "idx integer primary key autoincrement, "+
                "id text unique , name text, phone text, addr text)" ;
        db.execSQL(sql);

        // 데이터 삽입
        sql = "insert into p_list values(null, 'hong' , '홍길동', '010-9797-9999', '서울 마포구 양화로 127 ')" ;
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "drop table p_list";
        db.execSQL(sql);

        // 다시 DB 생성하는 메소드 호출
        onCreate(db);
    }
}