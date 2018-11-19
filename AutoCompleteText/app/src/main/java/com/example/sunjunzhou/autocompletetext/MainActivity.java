package com.example.sunjunzhou.autocompletetext;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class MainActivity extends AppCompatActivity {
    private AutoCompleteTextView textView;
    private static final String[] autotext={"China","Canada","India","Italy","Iran","Iraq"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=(AutoCompleteTextView)findViewById(R.id.autoCompleteTextView1);
        ArrayAdapter<String>adapter=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,autotext);
        textView.setAdapter(adapter);

    }
}
