package com.example.ex85_video;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoView = findViewById(R.id.videoView);

        MediaController controller = new MediaController(this);
        controller.setAnchorView(videoView);
       /*
       // Device File Explore 탭에서 data/data 해당 패키지를 찾아서 file upload해서 수행 가능
        videoView.setVideoURI(
                Uri.parse(Environment.getDataDirectory()+ "/data/" + getPackageName() + "/video.mp4"));*/
       // raw 폴더를 project의 res에 하위 폴더로 만들고 복사해서 raw 폴더에 동영상을 붙여넣기 하는 방법
       videoView.setVideoURI(Uri.parse("android.resources://" + getPackageName() + "/" + R.raw.video));
        videoView.requestFocus();
        videoView.start();
    }
}
