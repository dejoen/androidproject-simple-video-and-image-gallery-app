package com.example.notificationtutoria;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;

public class VideoViewClass extends AppCompatActivity {
VideoView videoView;
String videoUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view_class);
       // videoView=findViewById(R.id.myVideoView);
        videoUrl=getIntent().getStringExtra("videoUrl");
        playVideo();
    }

    private void playVideo(){
        Uri uri=Uri.parse(videoUrl);
        videoView.setVideoURI(uri);
        videoView.canPause();
        videoView.canSeekBackward();
        videoView.canSeekForward();

    }
}