package com.example.ex07_imgexam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity1 extends AppCompatActivity {
    ImageView imgView1;
    Button btn1;
    RadioGroup rg1;
    TextView textView2;
    CheckBox chb1;
    RadioButton rb1, rb2, rb3, rb4;
    int[] img_prn ={R.drawable.boy, R.drawable.coffe, R.drawable.dog, R.drawable.donald};
    int idx = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        imgView1 = findViewById(R.id.imgView1);
        btn1 = findViewById(R.id.btn1);
        rg1 = findViewById(R.id.rg1);
        textView2 = findViewById(R.id.textView1);
        chb1 = findViewById(R.id.chb1);

        chb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    textView2.setVisibility(View.VISIBLE);
                    rg1.setVisibility(View.VISIBLE);
                    btn1.setVisibility(View.VISIBLE);
                    imgView1.setVisibility(View.VISIBLE);
                }else {
                    textView2.setVisibility(View.INVISIBLE);
                    rg1.setVisibility(View.INVISIBLE);
                    btn1.setVisibility(View.INVISIBLE);
                    imgView1.setVisibility(View.INVISIBLE);
                    rg1.clearCheck();
                    imgView1.setImageResource(0);
                }
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (rg1.getCheckedRadioButtonId()){
                    case R.id.rbtn1:  imgView1.setImageResource(R.drawable.boy); break;
                    case R.id.rbtn2:  imgView1.setImageResource(R.drawable.coffe); break;
                    case R.id.rbtn3:  imgView1.setImageResource(R.drawable.dog); break;
                    case R.id.rbtn4:  imgView1.setImageResource(R.drawable.donald); break;
                    default:
                        Toast.makeText(MainActivity1.this, "선택 후 누르세요", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
