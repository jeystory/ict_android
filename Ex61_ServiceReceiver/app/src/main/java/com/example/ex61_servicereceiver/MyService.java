package com.example.ex61_servicereceiver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

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
            String str = intent.getStringExtra("str");
            Log.d("my",str);

            Intent sendIntent = new Intent();
            sendIntent.setAction("show");
            sendIntent.putExtra("str", str);

            sendBroadcast(sendIntent);
        }

        return super.onStartCommand(intent, flags, startId);

    }




    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
