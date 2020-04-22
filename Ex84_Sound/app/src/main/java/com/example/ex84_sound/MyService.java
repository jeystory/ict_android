package com.example.ex84_sound;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Environment;
import android.os.IBinder;

public class MyService extends Service {
    MediaPlayer player;
    String path = Environment.getDataDirectory()+"/data/com.ict.ex84_sound/back.mp3";
    public MyService() { }
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    // MainActivity에서 startService(intent)를 받아서 실행하는 메소드
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        try {
            player = new MediaPlayer();
            player.setDataSource(path);
            player.prepare();
            player.start();
        }catch (Exception e){
        }
        return super.onStartCommand(intent, flags, startId);
    }

    // stopService(intent)을 받아서 처리하는 메소드
    @Override
    public void onDestroy() {
        super.onDestroy();
        player.stop();
        player.release();
    }
}
