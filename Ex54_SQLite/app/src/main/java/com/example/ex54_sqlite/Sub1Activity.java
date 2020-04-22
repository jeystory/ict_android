package com.example.ex54_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class Sub1Activity extends AppCompatActivity {
    EditText eYear, eMonth, eDay, ePlay ;
    Button button5, button6;
    String nalja;
    DAO dao ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub1);
        eYear = findViewById(R.id.eYear);
        eMonth = findViewById(R.id.eMonth);
        eDay = findViewById(R.id.eDay);
        ePlay = findViewById(R.id.ePlay);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);

        // 오늘 날짜
        int y = Calendar.getInstance().get(Calendar.YEAR);
        int m = Calendar.getInstance().get(Calendar.MONTH);
        int d = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

        eYear.setText(String.valueOf(y));
        eMonth.setText(String.valueOf(m+1));
        eDay.setText(String.valueOf(d));

        nalja = y+"_"+(m+1)+"_"+d ;

        // DB 처리
        dao = DAO.db_open(Sub1Activity.this);
        Cursor cursor = dao.select_date(nalja);
        if(cursor.moveToFirst()){
            ePlay.setText(cursor.getString(2));
            ePlay.setEnabled(false);
            ePlay.setTextColor(Color.BLUE);
            button5.setEnabled(false);
        }else{
            ePlay.setHint("오늘 스케줄 없음");
            ePlay.setEnabled(true);
            Toast.makeText(this, "오늘 스케줄 없음", Toast.LENGTH_SHORT).show();
        }

        // 오늘 스케줄이 없으면 스케줄 작성 하고  DB 에 저장하기
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dao.insertData(nalja, ePlay.getText().toString().trim());
            }
        });
        // 홈으로 이동
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
