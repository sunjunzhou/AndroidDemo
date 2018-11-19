package com.example.sunjunzhou.dialog;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btn1a,btn1b,btn2,btn3,btn4,btn5;
    private View.OnClickListener listener=new View.OnClickListener() {


        public void onClick(View arg0) {
            if (arg0 == btn1a)
                click1a();
            else if (arg0 == btn1b)
                click1b();
            else if (arg0 == btn2)
                click2();
            else if (arg0 == btn3)
                click3();
            else if (arg0 == btn4)
                click4();
            else if (arg0 == btn5)
                click5();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1a=(Button)findViewById(R.id.click1a);
        btn1b=(Button)findViewById(R.id.click1b);
        btn2=(Button)findViewById(R.id.click2);
        btn3=(Button)findViewById(R.id.click3);
        btn4=(Button)findViewById(R.id.click4);
        btn5=(Button)findViewById(R.id.click5);
        btn1a.setOnClickListener(listener);
        btn1b.setOnClickListener(listener);
        btn2.setOnClickListener(listener);
        btn3.setOnClickListener(listener);
        btn4.setOnClickListener(listener);
        btn5.setOnClickListener(listener);
    }
    public void click1a(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        //builder.setIcon(R.drawable.tb01);
        builder.setTitle("消息提醒");
        builder.setMessage("你单击了提醒对话框按钮");
        builder.setPositiveButton("确定",null);
        builder.show();
    }
    public void click1b(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.ic_launcher_background);
        builder.setTitle("对话框标题");
        builder.setMessage("是否升级应用程序？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                Toast.makeText(MainActivity.this,"确定被点击了",Toast.LENGTH_SHORT).show();
            }
        });
       builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface arg0, int arg1) {
               Toast.makeText(MainActivity.this,"取消被点击了",Toast.LENGTH_SHORT).show();

           }
       }) ;
       builder.show();
    }
    public void click2(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("单选对话框");
        final String[] items=new String[]{"条目1","条目2","条目3"};
        builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this,items[which]+"（序号为"+which+"）被点击了",Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {

            }
        });
        builder.show();
    }
    @SuppressLint("ShowToast")
    public void click3(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("多选对话框");
        final String[] items=new String[]{"条目1","条目2","条目3","条目4"};
        builder.setMultiChoiceItems(items,new boolean[]{true,false,true,false},new DialogInterface.OnMultiChoiceClickListener(){
            public void onClick(DialogInterface dialog,int which,boolean isChecked){
                Toast.makeText(MainActivity.this,items[which]+isChecked,Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {

            }
        });
        builder.show();
    }
    public void click4(){
        ProgressDialog pd =new ProgressDialog(this);
        pd.setTitle("文件加载");
        pd.setMessage("正在加载中.......");
        pd.setCanceledOnTouchOutside(true);
        pd.show();

    }
    public void click5(){
        final ProgressDialog pd=new ProgressDialog(this);
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setTitle("文件上传中.....");
        pd.setMax(100);
        pd.setCanceledOnTouchOutside(false);
        pd.show();
        new Thread(){
            public void run(){
                for (int i=0;i<100;i++){
                    pd.setProgress(i);
                    try {
                        Thread.sleep(50);
                    }
                    catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                pd.dismiss();
            };
        }.start();
        /*new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<100;i++){
                    pd.setProgress(i);
                    try {
                        Thread.sleep(50);
                    }
                    catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                pd.dismiss();
            }
        }).start();*/
    }
}
