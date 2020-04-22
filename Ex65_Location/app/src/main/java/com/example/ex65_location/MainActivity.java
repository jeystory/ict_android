package com.example.ex65_location;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;

import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

public class MainActivity extends AppCompatActivity implements AutoPermissionsListener {
    double lat, lon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AutoPermissions.Companion.loadAllPermissions(this, 100);
        LocationManager manager = (LocationManager)getSystemService((Context.LOCATION_SERVICE));

        if(ActivityCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            return;
        }

        try{
            Location location = manager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            double lat = location.getLatitude();
            double lon = location.getLongitude();

            if(location != null){
                Uri uri = Uri.parse("geo" + lat + "," + lon + "?z=17");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                intent.setPackage("com.google.android.apps.maps");
                startActivity(intent);

            }
            MyListener myListener = new MyListener();
            long minTime = 5000;
            float minDistance = 0 ;
            manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, minTime,minDistance,myListener);

        } catch (Exception e){

        }
    }

    // 지도 띄우기
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        AutoPermissions.Companion.parsePermissions(this, requestCode,permissions,this);
    }

    class MyListener implements LocationListener{
        @Override
        public void onLocationChanged(Location location) {
            lat = location.getLatitude();
            lon = location.getLongitude();
        }
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) { }
        @Override
        public void onProviderEnabled(String provider) { }
        @Override
        public void onProviderDisabled(String provider) { }
    }
    @Override
    public void onDenied(int i, String[] strings) {}
    @Override
    public void onGranted(int i, String[] strings) {}
}
