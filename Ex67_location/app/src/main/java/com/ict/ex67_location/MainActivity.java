package com.ict.ex67_location;

import androidx.annotation.NonNull;
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

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

public class MainActivity extends AppCompatActivity implements AutoPermissionsListener {
    Button button;
    SupportMapFragment mapFragment;
    GoogleMap map;
    double lat ;
    double log ;
    MarkerOptions myLocationMarker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;

            }
        });
        try {
            MapsInitializer.initialize(this);
        }catch (Exception e){
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 위도 경도 구하기
                startLocationService();

                // 10초 후 다시 읽기을 안했을 때 사용
                showCurentLocation(lat, log);
            }
        });

        AutoPermissions.Companion.loadAllPermissions(this,100);
    }
    public void startLocationService(){
        LocationManager manager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

        if(ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            return;
        }
        try{
            // Location location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
             Location location = manager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if(location!=null){
                 lat = location.getLatitude();
                 log = location.getLongitude();
            }
           //  10초 후 다시 읽기 (주기적으로 읽을 때는 사용)
           //  MyListener myListener = new MyListener();
           //  long minTime = 10000;
           //  float minDistance = 0 ;
           //  manager.requestLocationUpdates(LocationManager.GPS_PROVIDER,minTime,minDistance, myListener);
        }catch (Exception e){
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        AutoPermissions.Companion.parsePermissions(this, requestCode,permissions,this);
    }

    class MyListener implements LocationListener{
        @Override
        public void onLocationChanged(Location location) {
            lat = location.getLatitude();
            log = location.getLongitude();

            // 위도와 경도 지도 표시 하기
            showCurentLocation(lat, log);
        }
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {  }
        @Override
        public void onProviderEnabled(String provider) { }
        @Override
        public void onProviderDisabled(String provider) { }
    }
    @Override
    public void onDenied(int i, String[] strings) {}
    @Override
    public void onGranted(int i, String[] strings) {}

    public void showCurentLocation(double lat, double log){
        final LatLng location = new LatLng(lat, log);
        // map.addMarker(new MarkerOptions().position(location).title("7층 ict인재개발원"));

        // 마커표시
        myLocationMarker = new MarkerOptions();
        myLocationMarker.position(location);
        myLocationMarker.title("7층 인재개발원");
        map.addMarker(myLocationMarker);
        map.moveCamera(CameraUpdateFactory.newLatLng(location));
        map.animateCamera(CameraUpdateFactory.zoomTo(15)); // 1-19

        final LatLng location2 = new LatLng(lat+0.0002, log+0.0002);

         // 마커표시
        myLocationMarker.position(location2);
        myLocationMarker.title("지하 에슐리");
        map.addMarker(myLocationMarker);
        map.moveCamera(CameraUpdateFactory.newLatLng(location2));
        map.animateCamera(CameraUpdateFactory.zoomTo(15)); // 1-19

        map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {

                if(marker.getPosition().equals(location)) {
                    CameraPosition cameraPosition = new CameraPosition.Builder()
                            .target(location)
                            .zoom(19)
                            .bearing(45)  // 각도
                            .tilt(30)     // 깊이
                            .build();
                    map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                }else if(marker.getPosition().equals(location2)){
                    CameraPosition cameraPosition = new CameraPosition.Builder()
                            .target(location2)
                            .zoom(19)
                            .bearing(45)  // 각도
                            .tilt(30)     // 깊이
                            .build();
                    map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                }

            }
        });
    }

    // 액티비티가 화면에 보일 때 내위치 표시 활성
    @Override
    protected void onResume() {
        super.onResume();
        if(map != null) map.setMyLocationEnabled(true);
    }
    // 액티비티가 화면에 보이지 않을 때 내 표시 비활성
    @Override
    protected void onPause() {
        super.onPause();
        if(map != null) map.setMyLocationEnabled(false);
    }
}
