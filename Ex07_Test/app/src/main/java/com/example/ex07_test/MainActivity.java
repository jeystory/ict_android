package com.example.ex07_test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    CheckBox checkBox1;
    TextView tv1;
    RadioGroup rg1 ;
    Button button1;
    ImageView imageView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkBox1 = findViewById(R.id.checkBox1);
        tv1 = findViewById(R.id.tv1);
        rg1 = findViewById(R.id.rg1);
        button1 = findViewById(R.id.button1);
        imageView1 = findViewById(R.id.imageView1);

        checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               if(isChecked){
                    tv1.setVisibility(View.VISIBLE);
                    rg1.setVisibility(View.VISIBLE);
                    button1.setVisibility(View.VISIBLE);
                    imageView1.setVisibility(View.VISIBLE);
               }else{
                   tv1.setVisibility(View.INVISIBLE);
                   rg1.setVisibility(View.INVISIBLE);
                   button1.setVisibility(View.INVISIBLE);
                   imageView1.setVisibility(View.INVISIBLE);

                   // 초기화
                   rg1.clearCheck();
                   imageView1.setImageResource(0);
               }
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (rg1.getCheckedRadioButtonId()){
                    case R.id.radioButton1 : imageView1.setImageResource(R.drawable.boy);  break;
                    case R.id.radioButton2 : imageView1.setImageResource(R.drawable.coffe);  break;
                    case R.id.radioButton3 : imageView1.setImageResource(R.drawable.dog);  break;
                    case R.id.radioButton4 : imageView1.setImageResource(R.drawable.donald);  break;
                    default:
                        Toast.makeText(MainActivity.this, "선택 후 누르세요", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
