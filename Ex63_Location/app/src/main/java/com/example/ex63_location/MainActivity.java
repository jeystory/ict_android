package com.example.ex63_location;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    TextView textView1;
    Button button1;
    String msg="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = findViewById(R.id.textView1);
        button1 = findViewById(R.id.button1);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLocationService();
            }
        });
    }

    // 현재 위치와 경도와 위도 찾기
    private void showLocationService(){
        LocationManager manager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

        // 메니페스트에서 사용허가를 받아야 한다.
        if(ActivityCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            return ;
        }

        // 현재 위치와 경도와 위도 찾기 방법 2가지 : 네트워크,  GPS
        Location location = manager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        // Location location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        if(location != null){
            double lat = location.getLatitude();
            double lon = location.getLongitude();
            msg = "내 위치 => 위도 : "+ lat + "\n경도 : "+ lon +"\n" + getAddress(lat,lon);
            textView1.setText(msg);
        } else {
            textView1.setText("안보여?");
        }
    }

    private String getAddress(double lat, double lon){
        String addr = "";
        Geocoder geocoder = new Geocoder(this);
        List<Address> list = null;
        try {
            list = geocoder.getFromLocation(lat,lon,1);
            if(list.size() >0){
                Address address = list.get(0);
                addr = address.getAddressLine(0);
            }
        }catch (Exception e){
        }
        return addr;
    }
}
