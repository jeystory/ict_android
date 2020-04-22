package com.example.ex36_inflator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button button1;
    LinearLayout container1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = findViewById(R.id.button1);
        container1 = findViewById(R.id.container1);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  sub.xml를 화면에 띄우자 => 인플레이션
                LayoutInflater inflater =
                        (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                //inflater.inflate(R.layout.sub, container1,true);
                inflater.inflate(R.layout.sub,  container1, true);

               CheckBox checkBox1 = container1.findViewById((R.id.checkBox1));

              checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(isChecked){
                            Toast.makeText(MainActivity.this, "선택", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(MainActivity.this, "선택 안됨", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
    }
}
