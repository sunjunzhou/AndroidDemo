package e.sunjunzhou.camera2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Picture;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Policy;

public class MainActivity extends AppCompatActivity {
    private Button opbtn;
    private Button playbtn;
    private Button clobtn;
    private SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;
    private Camera camera;
    private int previewWidth=240;
    private int previewHeigth=320;
    private String filepath= Environment.getDownloadCacheDirectory().getPath()+"/mypicture.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        opbtn=(Button)findViewById(R.id.button1);
        playbtn=(Button)findViewById(R.id.button2);
        clobtn=(Button)findViewById(R.id.button3);
        surfaceView=(SurfaceView)findViewById(R.id.surfaceView1);
        surfaceHolder=surfaceView.getHolder();
        surfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {

            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                if (camera!=null){
                    camera.release();
                    camera=null;
                }

            }
        });
        clobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                openCamera();
            }
        });
        playbtn.setOnClickListener(new View.OnClickListener(){
        public void onClick(View v){
            takePicture();
        }

    });
        clobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeCamera();
                MainActivity.this.finish();
            }
        });
    }
    protected void closeCamera(){
        if (camera!=null){
            camera.stopPreview();
            camera.release();
            camera=null;
        }

    }
    protected void takePicture(){
        if (checkSDCard()){
            camera.takePicture(null,null,jpeg);
            Toast.makeText(MainActivity.this,"拍照成功",Toast.LENGTH_LONG).show();
            try {
                Thread.sleep(1000);

            }catch (InterruptedException e){
                e.printStackTrace();
            }
            camera.startPreview();
        }
        else{
            Log.e("camera","SD CARD not exist.");
            return;
        }
    }
    private void openCamera(){
        try{
            camera=Camera.open();
        }
        catch (Exception e){
            e.printStackTrace();
            return;
        }
        Policy.Parameters params=camera.getParameters();
        params.setPreviewSize(previewWidth,previewHeigth);
        params.setPictureSize(previewWidth,previewHeigth);
        camera.setParameters(params);
        try{
            camera.setPreviewDisplay(surfaceHolder);
        }catch (IOException e){
            e.printStackTrace();
        }
        camera.startPreview();
    }
    private android.hardware.Camera.PictureCallback jpeg=new android.hardware.Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, android.hardware.Camera camera) {

            Bitmap bitmap= BitmapFactory.decodeByteArray(data,0,data.length);
            File pictureFile=new File(filepath);
            try {
                FileOutputStream fos=new FileOutputStream(pictureFile);
                BufferedOutputStream bos=new BufferedOutputStream(fos);
                bitmap.compress(Bitmap.CompressFormat.JPEG,80,bos);
                bos.flush();
                bos.close();
                fos.close();
                Log.i("camera","jpg file saved.");
            }
            catch (FileNotFoundException e){
                Log.d("camera","File not found:"+e.getMessage());
            }
            catch (IOException e){
                Log.d("camera","Error acccessing file:"+e.getMessage());
            }
        }

    };
    private boolean checkSDCard(){
        if(android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)){
            return true;
        }
        else {
            return false;
        }
    }
}
