package e.sunjunzhou.observermodel;

import android.app.ListActivity;
import android.os.Handler;
import android.os.Message;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ListActivity {
    private Button add;
    private EditText age,name,sex;
    private MyPerson observable;
    private int i=1;
    private Button change;
    private ListView lv;
    private List<MyObserver>myObservers;
    private Handler handler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            MyListAdapter myListAdapter=new MyListAdapter(MainActivity.this,myObservers);
            lv.setAdapter(myListAdapter);
            return false;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name=(EditText)findViewById(R.id.et1);
        age=(EditText)findViewById(R.id.et2);
        sex=(EditText)findViewById(R.id.et3);
        add=(Button)findViewById(R.id.add);
        observable=new MyPerson();
        myObservers=new ArrayList<MyObserver>();
        lv=getListView();
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyObserver myObserver=new MyObserver(i);
                i++;
                observable.addObserver(myObserver);
                handler.sendEmptyMessage(0);
            }
        });
        change=(Button)findViewById(R.id.change);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!age.getText().toString().equals(null))
                    observable.setAge(Integer.valueOf(age.getText().toString()));
                else
                    observable.setAge(10+i);
                if (!name.getText().toString().equals(null))
                    observable.setName(name.getText().toString());
                else
                    observable.setName("a"+i);
                if (!sex.getText().toString().equals(null))
                    observable.setSax(sex.getText().toString());
                else
                    observable.setSax("男"+i);
                handler.sendEmptyMessage(0);
            }
        });
    }
}
