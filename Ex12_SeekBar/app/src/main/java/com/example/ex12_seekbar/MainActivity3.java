package com.example.ex12_seekbar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity3 extends AppCompatActivity {
    TextView textView3;
    SeekBar seekBar3;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        textView3 = findViewById(R.id.textView3);
        seekBar3 = findViewById(R.id.seekBar3);

        seekBar3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView3.setText("lightness : " + progress + "%");
                position = seekBar3.getProgress();
                // 실제폰의 밝기 조절
                setBrightness(progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void setBrightness(int brightness){
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.screenBrightness = (float)brightness/100;
        getWindow().setAttributes(params);
    }
}
