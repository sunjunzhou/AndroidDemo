package e.sunjunzhou.audio_service1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent=new Intent(this,MyAudioService.class);
        startService(intent);
        Button button1=(Button)findViewById(R.id.button1);
        button1.setOnClickListener(new buttonListener());
    }
    class buttonListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            intent =new Intent(MainActivity.this,MyAudioService.class);
            stopService(intent);
            finish();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK==keyCode)
            return false;
        return super.onKeyDown(keyCode, event);
    }
}
