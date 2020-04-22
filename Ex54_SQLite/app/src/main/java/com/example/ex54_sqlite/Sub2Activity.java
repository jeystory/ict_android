package com.example.ex54_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Sub2Activity extends AppCompatActivity {
    String nalja ;
    TextView dateText;
    EditText eResult ;
    Button button7, button8, button9;
    DAO dao;
    String idx ; // 수정, 삭제를 할때 사용할 변수(테이블의 기본키)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub2);
        dateText = findViewById(R.id.dateText);
        eResult = findViewById(R.id.eResult);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);

        // MainActivity에서 넘어온 날짜 받기
        Intent intent = getIntent();
        nalja = intent.getStringExtra("nalja");

        dateText.setText(nalja +"  일정");

        // DB 처리
        dao = DAO.db_open(Sub2Activity.this);
        Cursor cursor = dao.select_date(nalja);

        if(cursor.moveToFirst()){
            button7.setText("수정하기");
            idx = cursor.getString(0);
            eResult.setText(cursor.getString(2));
        }else{
            button7.setText("저장하기");
            Toast.makeText(this, "스케줄이 존재하지 않습니다.", Toast.LENGTH_SHORT).show();
        }

        // 수정과 저장
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(button7.getText().equals("저장하기")){
                    dao.insertData(nalja, eResult.getText().toString().trim());
                }else if(button7.getText().equals("수정하기")){
                    dao.updateData(idx,eResult.getText().toString().trim());
                }
            }
        });
        // 삭제
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dao.deleteData(idx);
                eResult.setText("");
                button7.setText("저장하기");
            }
        });
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // 날짜를 누르면 데이터 피커 다이얼로그 표시
        dateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 2020_1_29
                String[] c_nalja = nalja.split("_");
                int y = Integer.parseInt(c_nalja[0]);  // 2020
                int m = Integer.parseInt(c_nalja[1])-1;  // 1-1 = 0
                int d = Integer.parseInt(c_nalja[2]);  // 29

                new DatePickerDialog(Sub2Activity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        nalja = year+"_"+(month+1)+"_"+day;
                        dateText.setText(nalja+"  일정");
                        eResult.setText("");

                        // 다이얼 로그가 변경되면 다시 DB 검색을 해서 정보를 출력 해야 된다.
                        Cursor cursor1 = dao.select_date(nalja);
                        if(cursor1.moveToFirst()){
                            button7.setText("수정하기");
                            idx = cursor1.getString(0);
                            eResult.setText(cursor1.getString(2));
                        }else{
                            button7.setText("저장하기");
                            Toast.makeText(Sub2Activity.this, "스케줄이 존재하지 않습니다.", Toast.LENGTH_SHORT).show();
                        }
                    }
                },y, m, d).show();

            }
        });
    }
}
