package com.ict.ex89_tts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.Locale;

public class TTS02 extends AppCompatActivity {
    TextToSpeech tts;
    EditText editText1;
    SeekBar seekBar1, seekBar2 ;
    Button button1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tts02);
        editText1 = findViewById(R.id.editText1);
        seekBar1 = findViewById(R.id.seekBar1);
        seekBar2 = findViewById(R.id.seekBar2);
        button1 = findViewById(R.id.button1);

        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status == TextToSpeech.SUCCESS){
                    int result = tts.setLanguage(Locale.KOREA);
                    if(result == TextToSpeech.LANG_MISSING_DATA  ||
                        result == TextToSpeech.LANG_NOT_SUPPORTED){
                        Toast.makeText(TTS02.this, "언어 데이터가 없거나 언어가 지원되지 않음", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(TTS02.this, "실패", Toast.LENGTH_SHORT).show();
                }
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak();
            }
        });
    }

    private void speak(){
        String msg = editText1.getText().toString();
        float pitch = (float) seekBar1.getProgress() / 50 ;
        if(pitch < 0.1) pitch = 0.1f;

        float speed = (float) seekBar2.getProgress() / 50 ;
        if(speed < 0.1) speed = 0.1f;

        // 음성 피치(톤) 조절
        tts.setPitch(pitch);
        // 음성 속도 조절
        tts.setSpeechRate(speed);

        tts.speak(msg, TextToSpeech.QUEUE_FLUSH , null, null);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(tts != null){
            tts.stop();
            tts.shutdown();
        }
    }
}
