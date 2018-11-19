package com.example.sunjunzhou.listview_baseadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView personLV;
    private List<Person>persons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        persons=new ArrayList<Person>();
        Person p1=new Person(1,"张三",5000);
        persons.add(p1);
        Person p2=new Person(2,"李四",5600);
        persons.add(p2);
        Person p3=new Person(3,"王五",5800);
        persons.add(p3);
        personLV=(ListView)findViewById(R.id.personLV);
        personLV.setAdapter(new MyBaseAdapter());
        class MyBaseAdapter extends BaseAdapter{
            public int getCount(){
                return persons.size();
            }
            public Object getItem(int position){
                return persons.get(position);
            }
            public long getItemId(int position){
                return position;
            }
            public View getView(int position, View convertView, ViewGroup parent){
                View item =View.inflate(getApplicationContext(),R.layout.item,null);
                TextView idTV=(TextView)item.findViewById(R.id.idTV);
                TextView nameTV=(TextView)item.findViewById(R.id.nameTV);
                TextView balanceTV=(TextView)item.findViewById(R.id.balanceTV);
                Person p=persons.get(position);
                idTV.setText(p.getID()+"");
                nameTV.setText(p.getName());
                balanceTV.setText(p.getBalance()+"");
                return item;
            }
        }
    }
}
