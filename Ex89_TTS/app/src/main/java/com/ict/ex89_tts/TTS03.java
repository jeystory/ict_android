package com.ict.ex89_tts;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class TTS03 extends AppCompatActivity {
    TextToSpeech tts;
    TextView msg;
    ListView list;
    Button btn2 ;
    String[] arr = {"안드로 보이", "아이폰 보이", "핫 맨", "아이언 맨"};
    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tts03);

        msg = findViewById(R.id.msg);
        list = findViewById(R.id.list);
        btn2 = findViewById(R.id.btn2);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, arr);
        list.setAdapter(adapter);

        // tts 사용가능 여부 검사
        Intent intent = new Intent();
        intent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(intent, 100);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
                startActivityForResult(intent, 200);

                if(position == 0){
                    result = arr[position].toString()+" 정답  입니다.";
                }else {
                    result = arr[position].toString()+" 오답  입니다.";
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100){
            if(resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS){
                tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        tts.setLanguage(Locale.KOREA);
                        tts.speak(msg.getText().toString(),TextToSpeech.QUEUE_FLUSH, null, null);
                    }
                });
            }else if(resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_FAIL){
                Toast.makeText(this, "문제 읽기 실패", Toast.LENGTH_SHORT).show();
            }
        }else if(requestCode == 200){
            if(resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS){
                tts.speak(result,TextToSpeech.QUEUE_FLUSH, null, null );
            }else if(resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_FAIL){
                Toast.makeText(this, "정답 /오답 읽기 실패", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(tts != null)  tts.stop();
    }
}
