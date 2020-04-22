package com.ict.ex75_animation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class Customer extends AppCompatActivity {
    Button button1, button2 ;
    EditText editText1, editText2 ;

    String birth ;
    int mYear, mMonth, mDay ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);

        Calendar now = Calendar.getInstance();
        mYear = now.get(Calendar.YEAR);
        mMonth = now.get(Calendar.MONTH);
        mDay = now.get(Calendar.DATE);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(Customer.this, android.R.style.Theme_DeviceDefault_Dialog, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        mYear = year;
                        mMonth = month;
                        mDay = day ;

                        birth = String.format("%d. %d. %d. 선택", year, month+1, day);
                        Toast.makeText(Customer.this, birth ,Toast.LENGTH_SHORT).show();
                    }
                }, mYear, mMonth, mDay).show();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mName = editText1.getText().toString();
                String mAge = editText2.getText().toString();
                Toast.makeText(Customer.this, "이름 : " + mName  +", 나이 : "+ mAge +" 생년월일 : " +birth,
                        Toast.LENGTH_SHORT).show();

                finish();
                // 애니메이션 적용
                overridePendingTransition(R.anim.entry_back, R.anim.exit_back);
            }
        });
    }
}
