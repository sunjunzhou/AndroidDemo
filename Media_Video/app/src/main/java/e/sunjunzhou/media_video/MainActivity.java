package e.sunjunzhou.media_video;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import java.nio.file.Path;

public class MainActivity extends AppCompatActivity {
    private VideoView videoView1;
    private MediaController mc;
    private String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        videoView1=(VideoView)findViewById(R.id.videoView1);
        //path= Environment.getExternalStorageDirectory().getPath();
        path+="android.resource://"+getPackageName()+"/"+R.raw.test;
        videoView1.setVideoPath(path);
        //path+="/Movies/test.3gp";
        //videoView1.setVideoURI(Uri.parse(path));
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
