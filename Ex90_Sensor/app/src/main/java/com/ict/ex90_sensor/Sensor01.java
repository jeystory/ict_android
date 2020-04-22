package com.ict.ex90_sensor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class Sensor01 extends AppCompatActivity {
    TextView textView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sensor01);

        textView1 = findViewById(R.id.textView1);

        SensorManager manager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> list = manager.getSensorList(Sensor.TYPE_ALL);

        String msg = "전체 센서의 수 : " + list.size()+"\n";
        for(Sensor k : list){
            msg += "sensor_name : " + k.getName() +"\n" +
                   "sensor_power : " + k.getPower() +"\n" +            // 단위
                   "sensor_resolution : " + k.getResolution() +"\n" +  // 해상도
                   "sensor_range : " + k.getMaximumRange()+"\n"     +  // 최대범위
                   "=======================\n";
        }
        textView1.setText(msg);
    }
}
