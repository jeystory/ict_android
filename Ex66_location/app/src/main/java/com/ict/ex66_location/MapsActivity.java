package com.ict.ex66_location;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback , AutoPermissionsListener {
    private GoogleMap mMap;
    double lat ;    // 위도
    double log ;    // 경도
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // 위도 경도 구하기
        // 위험 권한 자동 부여
        AutoPermissions.Companion.loadAllPermissions(this,100);
        LocationManager manager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        if(ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            return;
        }
        try {
            Location location = manager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            // Location location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            lat = location.getLatitude();
            log = location.getLongitude();

        }catch (Exception e){
        }

        // layout - acitivity-maps.xml의 fragment 사용하기 위한 코딩
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        // 구글맵이 준비 되었으면 프레그먼트에 리턴한다.
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // 위도와 경도 지정
       final LatLng location = new LatLng(lat, log);
        mMap.addMarker(new MarkerOptions().position(location).title("7층 ict인재개발원"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(location));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15)); // 1-19

        // 두번째 지정
        final LatLng location2 = new LatLng(lat+0.0001, log+0.0001);
        mMap.addMarker(new MarkerOptions().position(location2).title("지하 에슐리"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(location2));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15)); // 1-19

        // 마커의 타이틀를 클릭하면 바로 전화 하기
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                if(marker.getPosition().equals(location)) {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel: (☎)02.739.7235"));
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                    }
                }else if(marker.getPosition().equals(location2)) {
                    CameraPosition cameraPosition = new CameraPosition.Builder()
                            .target(location2)
                            .zoom(19)
                            .bearing(45)  // 각도
                            .tilt(30)     // 깊이
                            .build();
                    mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                }
            }
        });

    }

    @Override
    public void onDenied(int i, String[] strings) { }
    @Override
    public void onGranted(int i, String[] strings) { }
}
