package e.sunjunzhou.medio2;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    private VideoView videoView1;
    private MediaController mc;
    private  String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        videoView1=findViewById(R.id.videoView1);
        path="android.resource://"+getPackageName()+"/"+R.raw.test;
        videoView1.setVideoPath(path);
        mc=new MediaController(this);
        videoView1.setMediaController(mc);
        videoView1.start();
        videoView1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                finish();
            }
        });

    }
}
