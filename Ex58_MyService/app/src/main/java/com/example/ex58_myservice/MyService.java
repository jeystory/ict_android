package com.example.ex58_myservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    public MyService() { }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    // startService를 하면 자동으로 실행
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("my","onCreate() 호출");
    }

    // 자동 호출됨.// 인텐트를 통해서 정보를 받고 보낼 때 사용
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("my","onStartCommand() 호출");
        if(intent != null){
            process(intent);
        }else{
            // 재실행. 없어도 재실행 된다.
            return Service.START_STICKY;
        }

        return super.onStartCommand(intent, flags, startId);

    }

    // 인텐트가 존재할때 실행할 메소드
    private void process(Intent intent){

        // MainActivity에서 보낸 인텐트 받기
        String command = intent.getStringExtra("command");
        String str = intent.getStringExtra("str");
        Log.d("my", command);
        Log.d("my",str);

        // 5초 뒤에 MainActivity에 정보 보내기
        try {
            Thread.sleep(5000);
        }catch (Exception e){   }

        // Intent sendIntent = new Intent(getApplicationContext(), MainActivity.class) ;    //메인으로 보낼때 사용1
        //Intent sendIntent = new Intent(MyService.this, MainActivity.class) ;              // 메인으로 보낼때 사용2
        Intent sendIntent = new Intent(MyService.this, SubActivity.class) ;

        // 액티비티를 다시 만들어서 보이기는 것이아니라 기존에 있는 액티비티를 다시 띄는 역할하는 FLAG 추가
        sendIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                Intent.FLAG_ACTIVITY_SINGLE_TOP |
                Intent.FLAG_ACTIVITY_CLEAR_TOP);
        sendIntent.putExtra("command","ReturnService");
        sendIntent.putExtra("str",str);

        // 다시 액션 호출
        startActivity(sendIntent);
    }

    // 서비스가 중단 될때
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("my","onDestroy() 호출");

    }
}

