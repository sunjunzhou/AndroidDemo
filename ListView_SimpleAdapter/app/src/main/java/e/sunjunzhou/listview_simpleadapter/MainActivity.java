package e.sunjunzhou.listview_simpleadapter;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private Cursor cursor;
    ListView listView;
    private Map<String,Object>listItem;
    private List<Map<String,Object>>listData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DbUtil dbUtil=new DbUtil(this);
        SQLiteDatabase db=dbUtil.getWritableDatabase();
        cursor=dbUtil.allQuery(db);
        listView=(ListView)findViewById(R.id.listView);
        listData=new ArrayList<Map<String, Object>>();
        while (cursor.moveToNext()){
            long id=cursor.getInt(0);
            String name=cursor.getString(1);
            int age=cursor.getInt(cursor.getColumnIndex("age"));
            listItem=new HashMap<String,Object>();
            listItem.put("_id",id);
            listItem.put("name",name);
            listItem.put("age",age);
            listData.add(listItem);

        }
        SimpleAdapter listAdapter=new SimpleAdapter(this,listData,R.layout.list_item,new String[]{"_id","name","age"},
                new int[]{R.id.tv_id,R.id.tvname,R.id.tvage});
        listView.setAdapter(listAdapter);
    }
}
