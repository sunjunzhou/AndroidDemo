package e.sunjunzhou.btos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import e.sunjunzhou.service.MyAudioService;

public class MainActivity extends AppCompatActivity {
    private Button mButton;;
    Intent intent;
    private boolean isCast=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton=(Button)findViewById(R.id.mButton);
        Intent myintent=getIntent();
        isCast=myintent.getBooleanExtra("iscast",false);
        mButton.setEnabled(isCast);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(MainActivity.this, MyAudioService.class);
                MainActivity.this.stopService(intent);
                finish();
            }
        });
    }
}
