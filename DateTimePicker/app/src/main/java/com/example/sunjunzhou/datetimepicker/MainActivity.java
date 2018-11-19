package com.example.sunjunzhou.datetimepicker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private TextView textview;
    private TimePicker timepicker;
    private DatePicker datepicker;
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
        datepicker = findViewById(R.id.datepicker);
        timepicker = findViewById(R.id.timepicker);
        textview = (TextView)findViewById(R.id.timeview);
        textview.setText(new StringBuffer().append(year).append("/")
                .append(format(month + 1)).append("/")
                .append(format(day))
                .append(" ").append(format(hour)).append(":")
                .append(format(minute)));
        datepicker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                MainActivity.this.year=year;
                month=monthOfYear;
                day=dayOfMonth;
                textview.setText(new StringBuffer().append(year)
                        .append("/").append(format(month+1))
                        .append("/").append(format(day))
                        .append("  ")
                        .append(format(hour)).append(":")
                        .append(format(minute)));
            }
        });
        timepicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                hour=hourOfDay;
                MainActivity.this.minute=minute;
                textview.setText(new StringBuffer().append(year)
                        .append("/").append(format(month+1))
                        .append("/").append(format(day)).append("  ")
                        .append(format(hour)).append(":")
                        .append(format(minute)));
            }
        });
    }
    private String format(int dt){
        String str=""+dt;
        if(str.length()==1)
            str="0"+str;
        return str;
    }

}
