package e.sunjunzhou.videorecordemo;

import android.graphics.Camera;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

public class VideoRecorderDemoActivity extends AppCompatActivity {
    private Button opbtn;
    private Button playbtn;
    private Button clobtn;
    private Camera camera;
    private SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;
    private MediaRecorder videoRecorder;
    private String myVideofilepath="sdcard/myVideo.3gp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_recorder_demo);
        opbtn=(Button)findViewById(R.id.button1);
        playbtn=(Button)findViewById(R.id.button2);
        clobtn=(Button)findViewById(R.id.button3);
        videoRecorder=new MediaRecorder();
        surfaceView=(SurfaceView)this.findViewById(R.id.surfaceView1);
        surfaceHolder=surfaceView.getHolder();
        opbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCamera();
            }
        });
        playbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(VideoRecorderDemoActivity.this,"开始录像。。。",Toast.LENGTH_SHORT).show();
                benginRecording();
            }
        });
        clobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopRecording();
                finish();
            }
        });
    }
    protected void onPause(){
        super.onPause();
        stopRecording();
        releaseCamera();
    }
    protected void stopRecording(){
        if (videoRecorder!=null){
            videoRecorder.stop();
            videoRecorder.reset();
            videoRecorder.release();
            videoRecorder=null;
            camera.lock();
        }
    }
    private void releaseCamera(){
        if (camera!=null){
            camera.stopPreview();
            camera.release();
            camera=null;
        }
    }
    protected void benginRecording(){
        camera.unlock();
        videoRecorder.setCamera(camera);
        videoRecorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);
        videoRecorder.setAudioSource(MediaRecorder.VideoSource.CAMERA);
        videoRecorder.setAudioSource(MediaRecorder.OutputFormat.THREE_GPP);
        videoRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
        videoRecorder.setAudioEncoder(MediaRecorder.VideoEncoder.H264);
        videoRecorder.setVideoSize(176,144);
        videoRecorder.setVideoFrameRate(20);
        if (!checkSDCard()){
            Log.e("videoRecorder","未找到SD卡！");
            return;
        }
        videoRecorder.setOutputFile(myVideofilepath);
        videoRecorder.setPreviewDisplay(surfaceHolder.getSurface());
        try{
            videoRecorder.prepare();
        }catch (IllegalStateException e){
            e.printStackTrace();
        }
        videoRecorder.start();
    }
    private void openCamera(){
        Log.i("videoRecorder","openCamera.");
        try {
            camera=Camera.open();
        }catch (Exception e){
            Log.e("camera","open camera error!");
            e.printStackTrace();
            return;
        }
        try {
            camera.setPreviewDisplay(surfaceHolder);
        }catch (IOException e){
            Log.e("camera","preview failed.");
            e.printStackTrace();
        }
        camera.startPreview();
    }
    private boolean checkSDCard(){
        if (android.os.Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            return true;
        }else{
            return false;
        }
    }
}
