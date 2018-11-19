package e.sunjunzhou.receivemessagedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText text=(EditText)this.findViewById(R.id.editText1);
        text.setText("接收到短信广播后，将由自定义的广播接收者程序SmsReceiver进行处理，本界面可以按返回键关闭啦。");
    }
}
