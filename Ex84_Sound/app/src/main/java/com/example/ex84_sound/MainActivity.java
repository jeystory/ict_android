package com.example.ex84_sound;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button button1, button2 ;
    NotificationManager manager ; // 통지(알림) 설정 매니져
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);

        manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        // 음악 재생
       button1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(MainActivity.this, MyService.class);
               startService(intent);

               // 알림 설정
               Notification.Builder builder = new Notification.Builder(MainActivity.this);

               builder.setSmallIcon(R.mipmap.ic_launcher_round);
               builder.setContentTitle("음악서비스");
               builder.setContentText("백지영... 총 맞는 것 처럼");

               // 다른 어플리케이션에게 인텐트를 위임하기 위해서는 PendingIntent가 필요하다.
               Intent p_intent =
                       new Intent(MainActivity.this, MainActivity.class);
               Intent[] intents = new Intent[1];
               intents[0] = p_intent ;

               PendingIntent pendingIntent =
                       PendingIntent.getActivities(MainActivity.this, 100, intents, 0);
               builder.setContentIntent(pendingIntent);
               Notification noti = builder.build();
               manager.notify(1, noti);
           }
       });

       //nitification 해제하면서 음악 중단
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MyService.class);
                stopService(intent);
                manager.cancel(1);
            }
        });


    }
}
