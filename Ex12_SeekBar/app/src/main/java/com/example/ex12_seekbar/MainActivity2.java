package com.example.ex12_seekbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    TextView textView2;
    SeekBar seekBar2;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        textView2 = findViewById(R.id.textView2);
        seekBar2 = findViewById(R.id.seekBar2);

        //SeekBar 이벤트 처리
        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
           //변경할때
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                position = seekBar2.getProgress();
                textView2.setText(position + "%");
            }
            //움직임이 시작할때
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                position = seekBar2.getProgress();
            }
            //움직임이 끝날때
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                position = seekBar2.getProgress();
            }
        });
    }
}
