package com.ict.ex88_vibrator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Vibrator vibrator;
    Button button1, button2, button3 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);

        vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(Build.VERSION.SDK_INT >=26){
                   vibrator.vibrate(new long[]{500,500,100,50}, 1);
               }else{
                   // vibrate(long milli) => 1/1000총
                   // vibrate(long[] pattern,int repect)
                   // pattern : 짝수,진동수, 홀수,진동수
                   // repeat : 0 횟수(0이면 무한반복), -1 (반복안됨)
                   // cancel() : 중지
                   vibrator.vibrate(new long[]{500,500,100,50}, 1);
               }
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                // 자체 제공하는 소리 호출
                Ringtone ringtone = RingtoneManager.getRingtone(getApplicationContext(),uri);
                ringtone.play();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 소리, 동영상 재생은
                MediaPlayer player = MediaPlayer.create(getApplicationContext(),R.raw.beep);
                player.start();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        vibrator.cancel();
    }
}
