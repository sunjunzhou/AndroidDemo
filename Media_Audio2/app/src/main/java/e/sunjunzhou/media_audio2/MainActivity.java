package e.sunjunzhou.media_audio2;

import android.icu.text.UnicodeSetSpanner;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.WithHint;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageButton st,stop;
    private MediaRecorder mRecorder;
    private File recordPath;
    private File recordFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        st=(ImageButton)findViewById(R.id.st);
        stop=(ImageButton)findViewById(R.id.stop);
        st.setOnClickListener(this);
        stop.setOnClickListener(this);

    }
    public  void onClick(View v){
        if (v==st){
            MainActivity.this.start();
            Toast.makeText(MainActivity.this,"开始录音",Toast.LENGTH_LONG).show();

        }
        if (v==stop){
            MainActivity.this.stop();
            Toast.makeText(MainActivity.this,"录音结束", Toast.LENGTH_SHORT).show();
        }

    }
    public void start(){
        if(checkSDCard()){
            recordPath= Environment.getExternalStorageDirectory();
            File path=new File(recordPath.getPath()+File.separator+"audioRecords");
            if (!path.mkdirs()){
                Toast.makeText(this,"目录创建失败",Toast.LENGTH_LONG);
            }
            recordPath=path;
        }else {
            Toast.makeText(MainActivity.this, "SDcard未链接", Toast.LENGTH_LONG).show();
            return;
        }
        try{
            recordFile=File.createTempFile(String.valueOf("myaudioRecod"),".amr",recordPath);
        }catch (IOException e){
            Toast.makeText(this,"文件创建失败",Toast.LENGTH_LONG).show();
        }
        mRecorder=new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
        mRecorder.setOutputFile(recordFile.getAbsolutePath());
        try {
            mRecorder.prepare();
            mRecorder.start();
        }
        catch (IllegalStateException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void stop(){
        try{
            if(mRecorder!=null) {
               mRecorder.stop();
               mRecorder.release();
               mRecorder=null;
            }
            }catch (IllegalStateException e){
            e.printStackTrace();
        }
        }
        private boolean checkSDCard(){
        if (android.os.Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
            return true;
        return false;
        }
    }

