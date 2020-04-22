package com.example.ex50_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editText1, editText2;
    Button button1, button2, button3;
    TextView textView1;

    SQLiteDatabase database;
    String tableName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        textView1 = findViewById(R.id.textView1);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String databaseName = editText1.getText().toString();
                //사용자 메소드 : 데이터베이스 생성
                createDatabase(databaseName);
            }
        });

       button2.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               tableName = editText2.getText().toString();
               //사용자 메소드 : 테이블 생성
               createTable(tableName);

               // 사용자 메소드 호출 : 레코드 insert
               insertRecord();
           }
       });

       button3.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               //select 실행
               selectAll();
           }
       });

    }
    private void createDatabase(String dbName){
        //create db
        database = openOrCreateDatabase(dbName, MODE_PRIVATE, null);
        textView1.append(dbName + "DB 생성\n");
        Log.i("my", dbName + "DB 생성");
    }

    private void createTable(String tbName){
        if(database == null){
            //Toast.makeText(this, "db 생성 먼저", Toast.LENGTH_SHORT).show();
            Log.i("my",  "db 생성 먼저");
            return;
        }
        //데이터베이스 실행하기(MariaDB, MySQL과 sql같음)
        database.execSQL("create table if not exists "+ tbName + "("
                + "_id integer primary key autoincrement, "
                + "name text, "
                + "age integer, "
                + "mobile text)" );

        textView1.append(tbName + "Table 생성\n");
        Log.i("my", tbName + "Table 생성");
    }

    private void insertRecord(){
        if(database == null){
            //Toast.makeText(this, "db 생성 먼저", Toast.LENGTH_SHORT).show();
            Log.i("my",  "db 생성 먼저");
            return;
        }

        if(tableName == null){
            //Toast.makeText(this, "TB 생성 먼저", Toast.LENGTH_SHORT).show();
            Log.i("my",  "TB 생성 먼저");
            return;
        }
        database.execSQL("insert into " + tableName + " (name, age, mobile) " + " values " + "('John', 20 , '010-1111-2222')" );

        textView1.append( " 레코드 추가\n ");
        Log.i("my",  "record append");
    }

    private void selectAll(){
        if(database == null){
            //Toast.makeText(this, "db 생성 먼저", Toast.LENGTH_SHORT).show();
            Log.i("my",  "db 생성 먼저");
            return;
        }

        if(tableName == null){
            //Toast.makeText(this, "TB 생성 먼저", Toast.LENGTH_SHORT).show();
            Log.i("my",  "TB 생성 먼저");
            return;
        }

        Cursor cursor = database.rawQuery("select * from " + tableName, null);

        while(cursor.moveToNext()){
            textView1.append("id : " + cursor.getInt(0) + "\n");
            textView1.append("name : " + cursor.getString(1) + "\n");
            textView1.append("age: " + cursor.getInt(2) + "\n");
            textView1.append("mobile : " + cursor.getString(3) + "\n");


        }
    }

}
