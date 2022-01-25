package com.example.notificationtutoria;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoViewClass extends AppCompatActivity {
VideoView videoView;
String videoUrl;
MediaController mediaController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view_class);
       videoView=findViewById(R.id.videoView2);
        videoUrl=getIntent().getStringExtra("videoUrl");
        mediaController=new MediaController(this);
        playVideo();
        //yrrre
    }

    private void playVideo(){
        mediaController.setAnchorView(videoView);
        mediaController.setMediaPlayer(videoView);
        videoView.setMediaController(mediaController);
        Uri uri=Uri.parse(videoUrl);
        videoView.setVideoURI(uri);
        videoView.canPause();
        videoView.canSeekBackward();
        videoView.canSeekForward();
        videoView.start();

    }

    @Override
    protected void onPause() {
        if(videoView.isPlaying()){
            videoView.stopPlayback();
            videoView.suspend();
        }
        super.onPause();
    }

    @Override
    protected void onStop() {
        if(videoView.isPlaying()){
            videoView.stopPlayback();
            videoView.suspend();
        }
        super.onStop();
    }


    @Override
    protected void onDestroy() {
        if(videoView.isPlaying()){
            videoView.stopPlayback();
            videoView.suspend();
        }
        super.onDestroy();
    }
}