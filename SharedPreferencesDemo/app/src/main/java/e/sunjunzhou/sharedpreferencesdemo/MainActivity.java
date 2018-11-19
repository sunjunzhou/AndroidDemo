package e.sunjunzhou.sharedpreferencesdemo;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences sp;
    private EditText phoneText,cityText;
    private String phone,city;
    public static final String PHONE="PHONE";
    public static final String CITY="CITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        phoneText=(EditText)findViewById(R.id.phone_text);
        cityText=(EditText)findViewById(R.id.city_text);
        sp=this.getPreferences(Activity.MODE_PRIVATE);
        phone=sp.getString(PHONE,null);
        city=sp.getString(CITY,null);
        phoneText.setText(phone);
        cityText.setText(city);
    }
    protected void onStop(){
        sp.edit()
                .putString(PHONE,phoneText.getText().toString())
                .putString(CITY,cityText.getText().toString())
                .commit();
        super.onStop();
    }
}
