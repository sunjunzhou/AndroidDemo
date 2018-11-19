package e.sunjunzhou.mydb_dao;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dao.MyDAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private List<Map<String,Object>>listData;
    private Map<String,Object>listItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyDAO myDAO=new MyDAO(this);
        myDAO.insertInfo("tian",20);
        myDAO.insertInfo("wang",40);
        listView=(ListView)findViewById(R.id.listView);
        listData=new ArrayList<Map<String, Object>>();
        Cursor cursor=myDAO.allQuery();
        while (cursor.moveToNext()){
            long id=cursor.getInt(0);
            String name=cursor.getString(1);
            int age=cursor.getInt(cursor.getColumnIndex("age"));
            listItem=new HashMap<String, Object>();
            listItem.put("_id",id);
            listItem.put("name",name);
            listItem.put("age",age);
            listData.add(listItem);
        }
        SimpleAdapter listAdapter=new SimpleAdapter(this,listData,R.layout.list_item,
                new String[]{"_id","name","age"},
                new int[]{R.id.tv_id,R.id.tvname,R.id.tvage});
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                TextView tv=(TextView)findViewById(R.id.tvname);
                Toast.makeText(MainActivity.this,tv.getText(),Toast.LENGTH_LONG).show();

            }
        });
    }
}
