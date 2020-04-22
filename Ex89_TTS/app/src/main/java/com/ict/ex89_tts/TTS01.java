package com.ict.ex89_tts;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class TTS01 extends AppCompatActivity {
    TextView txt1 ;
    Button btn1;
    TextToSpeech tts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tts01);

        txt1 = findViewById(R.id.txt1);
        txt1.setText("지구의 모든 물은 공기에서 시작해서" +
                        " 어딘가에 담기고 또 식물과 동물을 통해 배출되는 과정을 계속해서 반복합니다."+
                        "All water on planet Earth is constantly cycled from the atmosphere,"+
                "into reservoirs, and through plants and animals ");

        // tts 가능 여부 검사
        Intent intent = new Intent();
        intent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(intent, 200);

        btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==200){
            if(resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS){
                tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        tts.setLanguage(Locale.KOREAN);
                        tts.speak(txt1.getText().toString(), TextToSpeech.QUEUE_FLUSH,null, null);
                    }
                });
            }else{
                Toast.makeText(this, "TTS 실패", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
