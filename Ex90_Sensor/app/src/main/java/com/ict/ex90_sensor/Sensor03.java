package com.ict.ex90_sensor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;

public class Sensor03 extends AppCompatActivity implements SensorEventListener {
    SensorManager manager;
    Sensor sensor;
    View mRoot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sensor03);

        // 센서 메니저를 통해서 센서를 구하자
        manager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor =  manager.getDefaultSensor(Sensor.TYPE_LIGHT);
        mRoot = findViewById(R.id.mRoot);

        manager.registerListener(this,sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // values의 값은 3개  x, y, z
        // 조도 센서는 values[0]에 넣어온다.
        float lux = event.values[0];
        getSupportActionBar().setTitle("조도 : " + lux +"lx");

        // 무지개 색
        if(lux < 320f / 7f ){
           mRoot.setBackgroundColor(Color.MAGENTA);
        }else if(lux < 320f / 7f * 2f){
            mRoot.setBackgroundColor(Color.parseColor("#4b0082"));
        }else if(lux < 320f / 7f * 3f){
            mRoot.setBackgroundColor(Color.BLUE);
        }else if(lux < 320f / 7f * 4f){
            mRoot.setBackgroundColor(Color.GREEN);
        }else if(lux < 320f / 7f * 5f){
            mRoot.setBackgroundColor(Color.YELLOW);
        }else if(lux < 320f / 7f * 6f){
            mRoot.setBackgroundColor(Color.parseColor("#FFFF57"));
        }else{
            mRoot.setBackgroundColor(Color.RED);
        }
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    protected void onPause() {
        super.onPause();
        manager.unregisterListener(this);
    }
}
