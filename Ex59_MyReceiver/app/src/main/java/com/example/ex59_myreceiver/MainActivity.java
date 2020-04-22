package com.example.ex59_myreceiver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

public class MainActivity extends AppCompatActivity implements AutoPermissionsListener {
    //Recevier를 등록해 놓은 상황이 발생하면 바로 실행
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //위험권한 자동 부여 하는 방법
        // Gradle Script - build.gradle(Module.app)에 해당 내용 추가
        //1. implementation 'com.github.pedroSG94:AutoPermission:1.0.3'
        //2. allprojects {
        //    repositories {
        //        maven {url 'https://jitpack.io'}
        //    }
        //}
        //3. implements AutoPermissionsListener  후 Overriding
        //4. 모든 위험 권한을 자동으로 부여하는 메소드 호출

        AutoPermissions.Companion.loadAllPermissions(this, 101);

    }

    // 5. 권한 요청 Override
    // 6. AndroidManifest.xml에서 사용자 권한 호출(지금은 SMS)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        AutoPermissions.Companion.parsePermissions(this, requestCode, permissions, this);
    }

    @Override
    public void onDenied(int i, String[] strings) {
        Toast.makeText(this, "권한거부", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGranted(int i, String[] strings) {
        Toast.makeText(this, "권한허가", Toast.LENGTH_SHORT).show();
    }
}
