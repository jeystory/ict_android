package com.example.ex52_sqlite;

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
    EditText id, name, phone, addr;
    TextView result;
    Button btn1, btn2, btn3, btn4, btn5;

    MyDB myDB = new MyDB(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        id = findViewById(R.id.id);
        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        addr = findViewById(R.id.addr);
        result = findViewById(R.id.result);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);

        // 저장하기
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(id.getText().length()>0 && name.getText().length()>0 && phone.getText().length() > 0 && addr.getText().length() >0 ) {
                    SQLiteDatabase db = myDB.getWritableDatabase();

                    // 집어넣는 데이터가 정확하지 않거나 일정하지 을 경우
                    String sql = "insert into p_list values(null, ? , ?, ?, ?)" ;
                    // ? 채워줄 배열
                    String[] arr = {id.getText().toString(), name.getText().toString(), phone.getText().toString(), addr.getText().toString() };
                    db.execSQL(sql, arr);
                    myDB.close();

                    id.setText("");
                    name.setText("");
                    phone.setText("");
                    addr.setText("");

                    // 삽입된 정보를 확인 하는 메소드
                    selectAll();
                }else{
                    Toast.makeText(MainActivity.this, "모든 정보를 입력해야만 삽입이 가능합니다..", Toast.LENGTH_SHORT).show();
                }
            }
        });
        // 전체보기
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectAll();
            }
        });

        // id를 이용해서 찾은 정보를 표시하기
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = myDB.getWritableDatabase();
                String sql = "select * from p_list where id = ?";
                String[] arr = {id.getText().toString()};
                Cursor cursor =  db.rawQuery(sql, arr);
                if(cursor.getCount()==0){
                    Toast.makeText(MainActivity.this, "찾는 정보가 없습니다.", Toast.LENGTH_SHORT).show();
                    id.setEnabled(true);
                    id.setText("");
                }else {
                    // 결과 저장해서 출력하기
                    while (cursor.moveToNext()) {
                        name.setText(cursor.getString(2));
                        phone.setText(cursor.getString(3));
                        addr.setText(cursor.getString(4));
                    }
                    cursor.close();
                    myDB.close();
                    id.setEnabled(false);
                }
            }
        });
        // id 가지고  찾은 정보를 수정하기
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(id.getText().length()>0 && name.getText().length()>0 && phone.getText().length() > 0 && addr.getText().length() >0 ) {
                    SQLiteDatabase db = myDB.getWritableDatabase();
                    String sql = "update p_list set name=?, phone=?, addr=? where id =?";
                    String[] arr = {name.getText().toString(), phone.getText().toString(), addr.getText().toString(), id.getText().toString()};
                    db.execSQL(sql, arr);
                    db.close();
                    id.setText("");
                    id.setEnabled(true);
                    name.setText("");
                    phone.setText("");
                    addr.setText("");
                    selectAll();
                }else{
                    Toast.makeText(MainActivity.this, "수정 정보를 수정 후 수정하세요", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(id.getText().length()>0){
                    SQLiteDatabase db = myDB.getWritableDatabase();
                    String sql = "delete from p_list where id = ? ";
                    String[] arr = {id.getText().toString()};
                    db.execSQL(sql, arr);
                    myDB.close();
                    id.setText("");
                    selectAll();
                }else {
                    Toast.makeText(MainActivity.this, "id 정보를 입력 후 삭제하세요", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void selectAll(){
        SQLiteDatabase db = myDB.getWritableDatabase();
        String sql = "select * from p_list";

        // select문의 결과 Cursor 이다.
        Cursor  cursor = db.rawQuery(sql, null);

        // 결과 저장해서 출력하기
        StringBuffer sb = new StringBuffer();
        while(cursor.moveToNext()){
            int idx = cursor.getInt(0);
            String id = cursor.getString(1);
            String name = cursor.getString(2);
            String phone = cursor.getString(3);
            String addr = cursor.getString(4);

            sb.append(idx+",  "+id+",  "+ name+",  "+ phone+",  "+addr+"\n");
        }

        result.setText(sb.toString());
        cursor.close();
        myDB.close();
    }
}