package com.example.ex20_datepicker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;

public class MainActivity1 extends AppCompatActivity {
    TextView textView;
    DatePicker datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        datePicker = findViewById(R.id.datePicker);
        textView = findViewById(R.id.textView);

        //이벤트 처리
        datePicker.init(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                //
                textView.setText(String.format("%d 년 %d월 %d일", year, monthOfYear+1, dayOfMonth));
            }
        });
    }
}
