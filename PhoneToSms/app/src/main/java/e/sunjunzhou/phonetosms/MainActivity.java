package e.sunjunzhou.phonetosms;

import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Environment;
import android.print.PrinterId;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.http.util.EncodingUtils;
public class MainActivity extends AppCompatActivity {
    private boolean flag=false;
    private TextView tv;
    private String message;
    private EditText et;
    private Button btnOK;
    private String fileName="test.txt";
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            tv=(TextView)findViewById(R.id.textView1);
            if (flag)
                tv.setText(R.string.app_name);
            else
                tv.setText(R.string.app_name/*auto_start*/);
            intent=new Intent(Constant.ACTION_MYSERVICE);
            startService(intent);
            et=(EditText)findViewById(R.id.editText1);
            message=readFile(fileName);
            et.setText(readFile(fileName));
            btnOK=(Button)findViewById(R.id.button1);
            btnOK.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    if ("".equals(et.getText().toString()))
                        Toast.makeText(getApplicationContext(),"回复为空，请重设！",Toast.LENGTH_SHORT).show();
                    else {
                        message=et.getText().toString();
                        try{
                            writeFile(fileName,message);
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                }
            });
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (flag){
                        flag=false;
                        tv.setText(R.string.app_name/*auto_start*/);
                        tv.setTextColor(Color.parseColor("#000000"));
                        Intent broadcast=new Intent();
                        broadcast.putExtra("message",message);
                        broadcast.setAction(Constant.ACTION_PAUSE);
                        sendBroadcast(broadcast);
                    }else
                    {
                        flag=true;
                        tv.setText(R.string.app_name/*auto_stop*/);
                        tv.setTextColor(Color.parseColor("#ffcccc"));
                        Intent broadcast=new Intent();
                        broadcast.putExtra("message",message);
                        broadcast.setAction(Constant.ACTION_PLAY);
                        sendBroadcast(broadcast);
                    }

                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public String readFile(String fileName)throws IOException{
        String res="";
        try{
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
                {
                String SDPATH=Environment.getExternalStorageDirectory().getPath()+"//";
                File file=new File(SDPATH+"//"+fileName);
                if (!file.exists())
                   {
                    file.createNewFile();
                    res="亲，有事忙无法接电话，请稍后联系！";
                    writeFile(fileName,res);
                   }
                else {
                    FileInputStream fis=new FileInputStream(file);
                    int length=fis.available();
                    byte[] buffer=new byte[length];
                    fis.read(buffer);
                    res=EncodingUtils.getString(buffer,"UTF-8");
                    fis.close();}
                }
                else
                    Toast.makeText(this,"sd卡不存在，读取失败",Toast.LENGTH_SHORT).show();

        }catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }
    public void writeFile(String fileName,String write_str)throws IOException{
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
        {
          String SDPATH=Environment.getExternalStorageDirectory().getPath()+"//";
          File file=new File(SDPATH+"/"+fileName);
            FileOutputStream fos=new FileOutputStream(file);
            byte[] bytes=write_str.getBytes();
            fos.write(bytes);
            fos.close();
            Toast.makeText(this,"写入成功",Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(this,"sd卡不存在，写入失败",Toast.LENGTH_SHORT).show();

    }
}
