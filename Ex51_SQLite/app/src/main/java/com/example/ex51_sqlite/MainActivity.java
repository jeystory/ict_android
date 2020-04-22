package com.example.ex51_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editText1, editText2;
    Button button1, button2, button3;
    TextView textView1;
    DatabaseHelper dbHelper;
    SQLiteDatabase databse;
    String tbName;

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
                String dbName = editText1.getText().toString();
               // db 생성
                createDatabase(dbName);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 상용자 메소드 : 테이블 생성
                tbName = editText2.getText().toString();
                createTable(tbName);

                // record 한줄 삽입하는 사용자 메소드
                inseartRecord();
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //조회하는 사용자 메소드
                executeQuery();
            }
        });
    }

    private void createDatabase(String dbName){
        textView1.append("createDatabase call \n");

        dbHelper = new DatabaseHelper(this, dbName);
        databse = dbHelper.getWritableDatabase();

        textView1.append("createDatabase create :  \n" + dbName);

    }

    private void createTable(String tbName){
        if(databse == null) {
            Toast.makeText(this, "db 먼저 생성", Toast.LENGTH_SHORT).show();
            return;
        }

        textView1.append("createTable call \n");

        String sql = "create table if not exists " +  tbName + "( "
                + "_id integer primary key autoincrement ,  "
                + "name text,  "
                + "age integer, "
                + " mobile text)";


        databse.execSQL(sql);

        textView1.append("creatTable create :  \n" + tbName);
    }

    private void inseartRecord(){
        if(databse == null) {
            Toast.makeText(this, "db 먼저 생성", Toast.LENGTH_SHORT).show();
            return;
        }

        if(tbName == null) {
            Toast.makeText(this, "tb 먼저 생성", Toast.LENGTH_SHORT).show();
            return;
        }

        databse.execSQL("insert into " + tbName
                        + "(name, age, mobile) "
                        + " values "
                        + "('hong', 20, '010-1111-2222')");

         textView1.append("레코드 추가 \n");
    }

    private  void  executeQuery(){
        textView1.append(" executeQuery() 호출됨 \n");

        Cursor cursor = databse.rawQuery("select * from emp",null);
        textView1.append("레코드 수 : " + cursor.getCount() + "\n");

        while (cursor.moveToNext()){
           int id = cursor.getInt(0);
           String name = cursor.getString(1);
           int age = cursor.getInt(2);
           String mobile = cursor.getString(3);

           textView1.append("reocrd #" + id + "," + name + "," + age +"," + mobile +"\n");
        }
    }
}
