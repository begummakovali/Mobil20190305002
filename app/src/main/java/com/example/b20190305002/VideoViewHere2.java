package com.example.b20190305002;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.b20190305002.R;

public class VideoViewHere2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);setContentView(R.layout.activity_video_view_here2);

        VideoView videoView2 = (VideoView) findViewById(R.id.videoView2);
        videoView2.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.video2));

        MediaController mediaController = new MediaController(this);
        videoView2.setMediaController(mediaController);
        videoView2.start();
    }
}