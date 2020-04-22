package com.example.ex53_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    DatePicker datePicker;
    EditText editText;
    Button btn;
    MyDB myDB = new MyDB(this);
    // db에 사용할 변수
    String dailyDate;
    String content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        datePicker = findViewById(R.id.datePicker);
        editText = findViewById(R.id.editText);
        btn = findViewById(R.id.btn);

        //오늘 날짜 구하기
        int mYear = Calendar.getInstance().get(Calendar.YEAR);
        int mMonth = Calendar.getInstance().get(Calendar.MONTH); // 0-11
        int mDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

        dailyDate = mYear + "_" + (mMonth +1) + "_" + mDay;

        // 오늘날짜의 스케쥴 체크하기 , 스케쥴 리턴 받기
        content = readDB();

        // 받은 정보를 editText에 표시하기
        editText.setText(content);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btn.getText().toString().equals("스케쥴 저장")){
                    // insert
                    SQLiteDatabase db = myDB.getWritableDatabase();
                    String sql = "insert into daily values(?,?)";
                    String[] arr = {dailyDate, editText.getText().toString()};
                    db.execSQL(sql,arr);
                    myDB.close();
                }else if(btn.getText().toString().equals("스케쥴 수정")){
                    //update
                    SQLiteDatabase db = myDB.getWritableDatabase();
                    String sql = "update daily set content=? where dailyDate=?";
                    String[] arr = { editText.getText().toString(), dailyDate};
                    db.execSQL(sql,arr);
                    myDB.close();
                }
            }
        });

        // DatePicker가 변경되면 감지해서 해당 날짜의 DB 정보를 존재하는지 파악해야 한다.
        datePicker.init(mYear, mMonth, mDay, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int y, int m, int d) {
                dailyDate = y + "_" +(m+1) + "_" + d;
                String msg = readDB();
                editText.setText(msg);
            }
        });

    }

    private String readDB(){
        String msg = "";
        SQLiteDatabase db = myDB.getWritableDatabase();
        String sql = "select * from daily where dailyDate = ?";
        String[] arr = {dailyDate};
        Cursor cursor = db.rawQuery(sql, arr);

        // 해당 날짜의 자료가 있는지 판별
        if(cursor.getCount() == 0){
            editText.setHint("스케쥴 없음");
            btn.setText("스케쥴 저장");
        } else {
            while(cursor.moveToNext()){
                msg = cursor.getString(1);
                btn.setText("스케쥴 수정");
            }
            editText.setTextColor(Color.BLUE);
        }

        cursor.close();
        myDB.close();
        return msg;
    }
}
