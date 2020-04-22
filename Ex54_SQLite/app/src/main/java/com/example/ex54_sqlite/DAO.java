package com.example.ex54_sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/// DB 처리 하는 클래스
public class DAO {
    Context context;
    MyDB myDB;
    SQLiteDatabase db;

    public DAO() {   }

    public DAO(Context context) {
        myDB = new MyDB(context);
        db = myDB.getWritableDatabase();
    }
    public static DAO db_open(Context context) throws SQLException{
        DAO dao = new DAO(context);
        return dao;
    }

    public void db_close(){
        db.close();
    }

    // DB처리하는 메소드들 만들어서  MainActivity에서 호출에서 사용

    // 날짜를 받아서 DB에 해당 날짜 존재 여부를 체크하는 메소드
    public Cursor select_date(String nalja){
        String sql = "select * from  myPlann where nalja = ? ";
        String[] arr = {nalja};
        Cursor cursor = db.rawQuery(sql, arr);
        return cursor ;
    }

    // 날짜와 내용을 받아서 DB에 저장하는 메소드
    public void insertData(String nalja, String plann){
        String sql = "insert into myPlann values(null,?,?)" ;
        String[] arr = {nalja, plann};
        db.execSQL(sql, arr);
    }

    // idx를 받아서  삭제하는 메소드
    public void deleteData(String idx){
        String sql = "delete from myPlann where idx = ? ";
        String[] arr = {idx};
        db.execSQL(sql,arr);
    }

    // idx와 내용을 받아서 수정하는 메소드
    public void updateData(String idx, String plann){
        String sql = "update myPlann set plann = ? where idx = ?" ;
        String[] arr = {plann, idx};
        db.execSQL(sql, arr);
    }

    // 내림 차순
    public Cursor selectDesc(){
        String sql = "select * from myPlann order by nalja desc";
        return db.rawQuery(sql, null);
    }

    // 오름 차순
    public Cursor selectAsc(){
        String sql = "select * from myPlann order by nalja asc";
        return db.rawQuery(sql, null);
    }


}
