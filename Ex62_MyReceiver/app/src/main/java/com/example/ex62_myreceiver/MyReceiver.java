package com.example.ex62_myreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {
    // 매니페스트에서  intent-filer를 적용해야함
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("my", "onReceive()" );
        String name = intent.getAction();
        if(name.equals("TEST")){
            String msg = intent.getStringExtra("msg");
            // 화면에 메세지 보내기 (이유 : receiver도  화면이 없기 때문에)
            sendToActivity(context, msg);
        }
    }

    // 화면 띄우는 메소드
    private void sendToActivity(Context context, String msg){

        Intent intent = new Intent(context, MainActivity.class);
        // 서비스의 화면 띄우기와 비슷하다.
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                Intent.FLAG_ACTIVITY_SINGLE_TOP |
                Intent.FLAG_ACTIVITY_CLEAR_TOP);

        intent.putExtra("msg",msg);
        context.startActivity(intent);
    }
}
