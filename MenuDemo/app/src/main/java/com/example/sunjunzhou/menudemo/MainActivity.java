package com.example.sunjunzhou.menudemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textview;
    private EditText edittext;
    private String str="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        textview=(TextView)findViewById(R.id.textView1);
        edittext=(EditText)findViewById(R.id.editText1);
        registerForContextMenu(textview);
        registerForContextMenu(edittext);

    }
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.mymenu,menu);
        SubMenu submenu=menu.addSubMenu("子菜单");
        submenu.add(0,1,0,"子菜单项一");
        submenu.add(0,2,0,"子菜单项二");
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case 1:
                textview.setText("选择了子菜单一");
                break;
            case 2:
                textview.setText("选择了子菜单二");
                break;
            case R.id.item1:
                textview.setText("item1 selected!");
                break;
            case R.id.item2:
                textview.setText("item2 selected!");
                break;
            case R.id.item3:
                textview.setText("item3 selected!");
                break;
            case R.id.item4:
                textview.setText("item4 selected!");
                break;
                default:
                    break;
        }
        return super.onOptionsItemSelected(item);
    }
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        if(v.getId()==R.id.textView1)
            menu.add(0,1,1,"复制");
        if(v.getId()==R.id.editText1)
            menu.add(0,2,0,"粘贴");
        super.onCreateContextMenu(menu,v,menuInfo);
    }
    public boolean onContextItemSelected(MenuItem item){
        switch (item.getItemId()){
            case 1:
                str=textview.getText().toString();
                break;
            case 2:
                edittext.setText(str);
                break;
                default:
                    break;
        }
        return super.onContextItemSelected(item);
    }
}
