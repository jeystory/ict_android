package com.ict.ex80_camera;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

// AVD는 기종에 따라 안될 수 도 있음, 실제포은 가능
// 실제 폰은 가능하나 가상 폰은 앱 정보에서 열기 해야 됨
// https://developer.android.com/training/camera/photobasics?hl=ko#java 카메라 관련 안드로이드 정보
public class MainActivity extends AppCompatActivity {
    static final int IMAGE_CAPTURE_REQUEST = 100 ;
    Button button;
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        imageView = findViewById(R.id.imageView);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                disp();
            }
        });
    }

    private void disp(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivityForResult(intent,IMAGE_CAPTURE_REQUEST);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
       super.onActivityResult(requestCode, resultCode, data);
       if(requestCode == IMAGE_CAPTURE_REQUEST && resultCode == RESULT_OK){
           Bundle extras = data.getExtras();
           Bitmap imageBitmap = (Bitmap) extras.get("data");
           imageView.setImageBitmap(imageBitmap);
       }
    }
}

