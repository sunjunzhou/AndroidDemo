package e.sunjunzhou.callactivity;

import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private Button mybutton;
    private EditText myet1,myet2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mybutton=(Button)findViewById(R.id.logButton);
        myet1=(EditText)findViewById(R.id.edit1);
        myet2=(EditText)findViewById(R.id.edit2);
        mybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setComponent(new ComponentName(MainActivity.this,ShowActivity.class));
                Bundle b=new Bundle();
                b.putString("name",myet1.getText().toString());
                b.putString("password",myet2.getText().toString());
                intent.putExtra("data",b);
                startActivity(intent);
            }
        });
    }
}
