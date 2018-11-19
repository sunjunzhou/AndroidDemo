package e.sunjunzhou.mybroadcaststatic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button myButton=(Button)findViewById(R.id.send);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent("com.example.broadcast.MY_BROADCAST");
                Bundle bundle=new Bundle();
                bundle.putString("data1","自定义广播与接收案例");
                bundle.putString("data2","\n  Writed by SJZ");
                bundle.putInt("year",2018);
                intent.putExtras(bundle);
                sendBroadcast(intent);
            }
        });
    }
}
