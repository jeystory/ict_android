package com.example.ex60_myservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(intent != null){
            process(intent);
        } else {
            return  Service.START_STICKY;
        }

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void process(Intent intent){
        String str = intent.getStringExtra("str");
        Intent sendIntent = new Intent(getApplicationContext(), MainActivity.class) ;

        // 액티비티를 다시 만들어서 보이기는 것이아니라 기존에 있는 액티비티를 다시 띄는 역할하는 FLAG 추가
        sendIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                Intent.FLAG_ACTIVITY_SINGLE_TOP |
                Intent.FLAG_ACTIVITY_CLEAR_TOP);
        sendIntent.putExtra("str",str);
        // 다시 액션 호출
        startActivity(sendIntent);
    }

}
