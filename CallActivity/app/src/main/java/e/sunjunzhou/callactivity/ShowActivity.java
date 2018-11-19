package e.sunjunzhou.callactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ShowActivity extends AppCompatActivity {
    private TextView tv;
    private Intent intent;
    private Bundle b1=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        tv=(TextView)findViewById(R.id.et);
        intent=getIntent();
        b1=intent.getBundleExtra("data");
        if (b1 != null) {
            tv.setText("用户名："+b1.getString("name")+"\n密码："+b1.getString("password"));
        }
    }
}
