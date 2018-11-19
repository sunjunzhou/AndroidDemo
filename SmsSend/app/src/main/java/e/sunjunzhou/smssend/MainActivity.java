package e.sunjunzhou.smssend;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText numET;
    private EditText contentET;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        numET=(EditText)findViewById(R.id.numET);
        contentET=(EditText)findViewById(R.id.contentET);

    }
    public void onClick(View view){
        String num=numET.getText().toString();
        String content=contentET.getText().toString();
        SmsManager smsManager=SmsManager.getDefault();
        ArrayList<String>list=smsManager.divideMessage(content);
        for (String sms:list)
            smsManager.sendTextMessage(num,null,sms,null,null);
        Toast.makeText(getApplicationContext(),"发送成功",Toast.LENGTH_SHORT).show();
    }
}
