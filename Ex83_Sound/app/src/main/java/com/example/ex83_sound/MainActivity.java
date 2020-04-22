package com.example.ex83_sound;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button button1,button2, button3;

    static  final  String AUDIO_URL =
            Environment.getDataDirectory() + "/data/com.example.ex83_sound/back.mp3";

    private MediaPlayer mediaPlayer;
    private int playbackPosition = 0;   //재 실행시 시작 위치 기억하는 변수
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);

        //실행
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    if(mediaPlayer != null){
                        mediaPlayer.release();
                    }
                    mediaPlayer = new MediaPlayer();
                    mediaPlayer.setDataSource(AUDIO_URL);
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                } catch (Exception e){}
            }
        });

        //일시정지
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer != null){
                    playbackPosition = mediaPlayer.getCurrentPosition();
                    mediaPlayer.pause();
                    Toast.makeText(MainActivity.this, "일시정지", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //재실행
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    if(mediaPlayer != null && !mediaPlayer.isPlaying()){
                        mediaPlayer.start();
                        mediaPlayer.seekTo(playbackPosition);
                        Toast.makeText(MainActivity.this, "재실행", Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){}
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mediaPlayer != null){
            mediaPlayer.release();
        }
    }
}
