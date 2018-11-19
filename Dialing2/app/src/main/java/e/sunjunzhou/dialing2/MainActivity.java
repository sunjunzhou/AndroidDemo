package e.sunjunzhou.dialing2;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText numET;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        numET=(EditText)findViewById(R.id.numET);
        Button callBT=(Button)findViewById(R.id.callBT);
        callBT.setOnClickListener(new MyOnClickListener());
    }
    private class MyOnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View arg0) {
            String num=numET.getText().toString();
            Intent intent=new Intent();
            intent.setAction(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:"+num));
            startActivity(intent);
        }
    }
}
