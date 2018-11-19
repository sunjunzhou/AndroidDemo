package com.example.sunjunzhou.userlogin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText et1;
    EditText et2;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1=(EditText)this.findViewById(R.id.edit1);
        et2=(EditText)this.findViewById(R.id.edit2);
        tv=(TextView) this.findViewById(R.id.text1);
    }
    public void click1(View view){
        String s1=et1.getText().toString().trim();
        String s2=et1.getText().toString().trim();
        tv.setText("输入的用户名为："+s1+"，输入的密码为："+s2);
    }
    public void click2(View view){
        et1.setText("");
        et2.setText("");
        tv.setText("");
        Toast.makeText(MainActivity.this,"取消登录",Toast.LENGTH_SHORT).show();
    }

}
