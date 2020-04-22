package com.ict.ex90_sensor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Sensor02 extends AppCompatActivity implements SensorEventListener {
    TextView txt1, txt2, txt3, txt4 , txt5;
    Sensor sensor1, sensor2, sensor3, sensor4, sensor5;
    SensorManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sensor02);

        txt1 = findViewById(R.id.txt1);
        txt2 = findViewById(R.id.txt2);
        txt3 = findViewById(R.id.txt3);
        txt4 = findViewById(R.id.txt4);
        txt5 = findViewById(R.id.txt5);

        // 센서 매니저를 통해서 센서를 구하자
        manager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);

        sensor1 = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) ;   // 가속도
        sensor2 = manager.getDefaultSensor(Sensor.TYPE_GRAVITY) ;         // 중력
        sensor3 = manager.getDefaultSensor(Sensor.TYPE_LIGHT) ;           // 밝기
        sensor4 = manager.getDefaultSensor(Sensor.TYPE_PROXIMITY) ;       // 근접
        sensor5 = manager.getDefaultSensor(Sensor.TYPE_GYROSCOPE) ;       // 회전

        manager.registerListener(this,sensor1,SensorManager.SENSOR_DELAY_NORMAL);
        manager.registerListener(this,sensor2,SensorManager.SENSOR_DELAY_NORMAL);
        manager.registerListener(this,sensor3,SensorManager.SENSOR_DELAY_NORMAL);
        manager.registerListener(this,sensor4,SensorManager.SENSOR_DELAY_NORMAL);
        manager.registerListener(this,sensor5,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        synchronized (this){
            float v1 = event.values[0];
            switch (event.sensor.getType()){
                case Sensor.TYPE_ACCELEROMETER :
                        txt1.setText("x = " + v1 ); break;
                case Sensor.TYPE_GRAVITY :
                        txt2.setText("x = " + v1 ); break;
                case Sensor.TYPE_LIGHT :
                        txt3.setText("x = " + v1 ); break;
                case Sensor.TYPE_PROXIMITY :
                        txt4.setText("x = " + v1 );
                    if(v1 == 0){
                        Toast.makeText(this,"접근완료", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(this,"접근해제", Toast.LENGTH_SHORT).show();
                    }
                break;
                case Sensor.TYPE_GYROSCOPE :
                    txt5.setText("x = " + v1 ); break;
            }
        }
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}

    @Override
    protected void onPause() {
        super.onPause();
        manager.unregisterListener(this);
    }
}
