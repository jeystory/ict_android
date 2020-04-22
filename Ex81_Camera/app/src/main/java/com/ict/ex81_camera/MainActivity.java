package com.ict.ex81_camera;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements AutoPermissionsListener {
    Button button;
    ImageView imageView;
    File photoFile;
    static final int IMAGE_CAPTURE_REQUEST = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button =  findViewById(R.id.button);
        imageView = findViewById(R.id.imageView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                disp();
            }
        });

        AutoPermissions.Companion.loadAllPermissions(this, IMAGE_CAPTURE_REQUEST);
    }

    private void disp(){
        if(photoFile == null){
            photoFile = createImageFile();
        }

        Uri fileUri = FileProvider.getUriForFile(this, "cameratest", photoFile);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivityForResult(intent, IMAGE_CAPTURE_REQUEST);
        }
    }

    private File createImageFile(){
        String fileName = "capture.jpg";
        // Device File Explorer : AVD에서는 안보임,  실물폰 sdcard 에 있는 것을 볼수 있음
        File storageDir = Environment.getExternalStorageDirectory();
        File outFile = new File(storageDir, fileName);

       return outFile;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMAGE_CAPTURE_REQUEST && resultCode == RESULT_OK) {

             BitmapFactory.Options options = new BitmapFactory.Options();
             options.inSampleSize = 6 ;
             Bitmap bitmap = BitmapFactory.decodeFile(photoFile.getAbsolutePath(), options);

            imageView.setImageBitmap(bitmap);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        AutoPermissions.Companion.parsePermissions(this, requestCode, permissions, this);
    }

    @Override
    public void onDenied(int i, String[] strings) { }
    @Override
    public void onGranted(int i, String[] strings) { }
}
