package com.example.ex62_myreceiver;

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
            // MainActivity에서 정보 수신
            String msg = intent.getStringExtra("msg");
            // Receiver에게 정보 송신
            Intent a_intent = new Intent(getApplicationContext(),MyReceiver.class);
            a_intent.setAction("TEST");
            a_intent.putExtra("msg",msg);

            // 리시버 호출
            sendBroadcast(a_intent);
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
