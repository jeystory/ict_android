package com.example.ex22_date_time_dialog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    Button button1, button2;
    TextView textView;
    int mYear, mMonth, mDay, mHour, mMinute ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        textView = findViewById(R.id.textView);

        //Dialog에 초기값을 현재 날짜로 지정
        Calendar calendar = Calendar.getInstance();
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);
        mHour = calendar.get(Calendar.HOUR_OF_DAY);
        mMinute = calendar.get(Calendar.MINUTE);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //DatePickerDialog 호출
                new DatePickerDialog(MainActivity.this, android.R.style.Theme_DeviceDefault, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        mYear = year;
                        mMonth = month;
                        mDay = day;

                        textView.setText(String.format("%d.%02d.%02d.%02d:%02d ", mYear, mMonth+1, mDay, mHour,mMinute));
                    }
                }, mYear,mMonth,mDay).show();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //DatePickerDialog 호출
                new TimePickerDialog(MainActivity.this, android.R.style.Theme_DeviceDefault, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        mHour = hourOfDay;
                        mMinute = minute;
                        textView.setText(String.format("%d.%02d.%02d.%02d:%02d ", mYear, mMonth+1, mDay, mHour,mMinute));
                    }

                }, mYear,mMonth,false).show();
            }
        });
    }
}
