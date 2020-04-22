package com.ict.ex90_sensor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;

// 중력 센서와 나침판 센서를 이용하자
public class Sensor04 extends AppCompatActivity implements SensorEventListener {
    CustomerView customerView;
    SensorManager manager;
    Sensor acc ; // 가속도
    Sensor mag ; // 자력
    float[] mGravity;     // 가속도 센서에서 구하기
    float[] mGeomagentic; // 자력 센서에서 구하기
    float azimut ;  // 방위각
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.sensor04);
        customerView =  new CustomerView(this);
        setContentView(customerView);

        manager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        acc = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mag = manager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        manager.registerListener(this, acc, SensorManager.SENSOR_DELAY_NORMAL);
        manager.registerListener(this, mag, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // 가속도 센서인 경우
        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            mGravity = event.values;
        }
        // 자력 센서인 경우
        if(event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD){
            mGeomagentic = event.values;
        }

        if(mGravity != null && mGeomagentic != null){
            float R[] = new float[9];  //회전
            float I[] = new float[9];  // 경사

            // 구글에서 지원하는 함수(나침판 구할 수 있음)를 사용 : 핸드폰 장치의 좌표계의 변화하는 값을 구한다.
            boolean success = SensorManager.getRotationMatrix(R, I, mGravity, mGeomagentic);
            if(success){
                // values[0] =>z축, values[1] = X축 (Pitch),  values[2] = Y축(Roll)
                float orientation[] = new float[3];
                // 핸트폰(장치)의 방향을 구하는 메소드
                 SensorManager.getOrientation(R, orientation);
                 azimut = orientation[0]; // 방위각
            }
        }
        // 값이 변할 때 그림도 변하기
        customerView.invalidate();
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}

    // 캔버스를 내부클래스로 만들자
    public class CustomerView extends View{
        Paint paint = new Paint();

        public CustomerView(Context context) {
            super(context);
            paint.setColor(Color.RED);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(4);
            paint.setAntiAlias(true);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            int width = getWidth();
            int height = getHeight();
            int centerX = width/2 ;
            int centerY = height/2;

            // 원 크기와 방위각
            float radius, dir ;

            // 작은 크기 기준으로 원을 그리기 위해서
            if(centerX > centerY){
                radius = (float)(centerY*0.9);
            }else{
                radius = (float)(centerX*0.9);
            }
            // 방위각 구하기
            dir = azimut * 360 / (2 * 3.14f) ;

            // 나침판 그리기
            canvas.drawCircle(centerX,centerY,radius,paint);
            canvas.drawLine(centerX, centerY-radius, centerX, centerY+radius, paint);
            canvas.drawLine(centerX-radius, centerY, centerX+radius, centerY, paint);


            // 방위 중앙에 표시
            paint.setColor(Color.DKGRAY);
            paint.setTextSize(300);
            canvas.drawText(String.valueOf((int)dir), centerX-150, centerY, paint);
            // 방위값 만큼 돌아라
            if(azimut != 0){
                canvas.rotate(-dir, centerX, centerY);
            }

            // 테두리에 N극, S극 보이기
            paint.setColor(Color.BLUE);
            paint.setTextSize(100);
            canvas.drawLine(centerX, centerY-radius, centerX, centerY+radius, paint);
            canvas.drawText("N", centerX-30, centerY-radius-20,paint);
            canvas.drawText("S", centerX-30, centerY+radius+80,paint);
            paint.setColor(Color.RED);

        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        manager.unregisterListener(this);
    }
}














