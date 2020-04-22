package com.example.ex59_myreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {
    //문자가 들어오면 실행
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("my","onReceive()");

        // Intent안에 Bundle객체 존재함
        Bundle bundle = intent.getExtras();
        SmsMessage[] msg = parseSms(bundle);
        if(msg!=null && msg.length>0){
            //정식 규격
            String sender = msg[0].getOriginatingAddress(); //send address
            String contents = msg[0].getMessageBody();  //send contents

            Log.d("my", "sender" +sender+ ",contents : " +contents);
            //화면에 메세지 보내기(이유 : 리시버도 화면이 없기 때문에
            sendToActivity(context, sender, contents);
        }
    }

    //정상적인 프로토콜을 이용해서 문자를 받았을 때 처리
    private SmsMessage[] parseSms(Bundle bundle){
        Object[] objs = (Object[])bundle.get("pdus");
        SmsMessage[] messages = new SmsMessage[objs.length];

        int smsCount = objs.length;
        for(int i=0;i<smsCount;i++){
            if(Build.VERSION.SDK_INT > Build.VERSION_CODES.M){
                String format = bundle.getString("format");
                messages[i] = SmsMessage.createFromPdu((byte[])objs[i], format);
            }else{
                messages[i]=SmsMessage.createFromPdu((byte[])objs[i]);
            }
        }
        return messages;
    }

    //화면 띄우는 메소드
    private  void sendToActivity(Context context, String sender, String contents){
        Intent intent = new Intent(context, SubActivity.class);
        //서비스 화면 띄우기와 비슷
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                Intent.FLAG_ACTIVITY_SINGLE_TOP |
                Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("sender","sender");
        intent.putExtra("contents",contents);

        context.startActivity(intent);

    }
}
