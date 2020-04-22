package com.example.ex64_location;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

// 1. 메니페스트에 uses-permission 지정 : ACCESS_FINE_LOCATION
// 2. 위험 권한 자동 지정 : Gradle Scripts - build.gradle(Module)
//   implementation 'com.github.pedroSG94:AutoPermissions:1.0.3'
//   allprojects {
//     repositories {
//        maven {url 'https://jitpack.io'}
//      }
//   }
// 3. AutoPermissionsListener implements 하기
public class MainActivity extends AppCompatActivity implements AutoPermissionsListener{
    Button button1;
    TextView textView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.button1);
        textView1 = findViewById(R.id.textView1);

        // 위험 권한 자동 부여
        AutoPermissions.Companion.loadAllPermissions(this,100);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLocationService();
            }
        });
    }

    private  void showLocationService(){
        LocationManager manager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

        if(ActivityCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            return ;
        }
        try{
            Location location = manager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if(location != null){
                double lat = location.getLatitude();
                double lon = location.getLongitude();

                String msg = "최근 위치 : 위도 :" +lat + ", 경도 : " + lon;
                textView1.setText(msg);
                //내부 클래스
                MyListener myListener = new MyListener();
                long minType = 5000;
                float minDistance = 0;  //이동거리

                manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, minType, minDistance, myListener);

                Toast.makeText(this, "내 위치 확인 요청", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e){}
    }


    class MyListener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {
            double lat = location.getLatitude();
            double lon = location.getLongitude();

            String msg = "내 위치 : 위도 :" +lat + ", 경도 : " + lon;
            textView1.setText(msg);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    }

    @Override
    public void onDenied(int i, String[] strings) {

    }

    @Override
    public void onGranted(int i, String[] strings) {

    }
}
