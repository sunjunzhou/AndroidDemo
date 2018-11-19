package e.sunjunzhou.mydbcrud;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private EditText et_name;
    private EditText et_age;
    private Map<String,Object> item;
    private ArrayList<Map<String,Object>>data;
    private DbHelper dbHelper;
    private SQLiteDatabase db;
    private Cursor cursor;
    private SimpleAdapter listAdapter;
    private ListView listView;
    private Button addBtn,updBtn,delBtn;
    private String selId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_name=findViewById(R.id.et_name);
        et_age=findViewById(R.id.et_age);
        listView=findViewById(R.id.listView);
        addBtn=findViewById(R.id.bt_add);
        updBtn=findViewById(R.id.bt_modift);
        delBtn=findViewById(R.id.bt_del);
        addBtn.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                dbAdd();
                dbFindAll();
            }
        });
        updBtn.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                dbUpdate();
                dbFindAll();
            }
        });
        delBtn.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                dbDel();
                dbFindAll();
            }
        });
        dbHelper=new DbHelper(this,DbHelper.DATABASE_NAME,null,1);
        db=dbHelper.getWritableDatabase();
        data=new ArrayList<Map<String,Object>>();
        dbFindAll();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Map<String,Object> listItem=(Map<String, Object>)listView.getItemAtPosition(position);
                et_name.setText((String)listItem.get("name"));
                et_age.setText((String)listItem.get("age"));
                selId=(String)listItem.get("_id");
                Toast.makeText(getApplicationContext(),"选择的id是："+selId,Toast.LENGTH_SHORT).show();
            }
        });

    }
    protected void dbDel(){
        String where="_id="+selId;
        int i=db.delete(DbHelper.TABLE_NAME,where,null);
        if (i>0)
            Toast.makeText(getApplicationContext(),"数据删除成功！",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getApplicationContext(),"数据删除失败！",Toast.LENGTH_SHORT).show();
    }
    private void showList(){
        listAdapter=new SimpleAdapter(this,data,
                R.layout.list_item,
                new String[]{"_id","name","age"},
                new int[]{R.id.tv_id,R.id.tvname,R.id.tvage});
        listView.setAdapter(listAdapter);
    }
    protected void dbUpdate(){
        ContentValues values=new ContentValues();
        values.put("name",et_name.getText().toString().trim());
        values.put("age",et_age.getText().toString().trim());
        String where="_id="+selId;
        int i=db.update(DbHelper.TABLE_NAME,values,where,null);
        if(i>0)
            Toast.makeText(getApplicationContext(),"数据更新成功！",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getApplicationContext(),"数据更新失败！",Toast.LENGTH_SHORT).show();
    }
    protected void dbAdd(){
        ContentValues values=new ContentValues();
        values.put("name",et_name.getText().toString().trim());
        values.put("age",et_age.getText().toString().trim());
        long rowid=db.insert(DbHelper.TABLE_NAME,null,values);
        if (rowid==-1)
            Toast.makeText(getApplicationContext(),"数据插入失败！",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getApplicationContext(),"数据插入成功！",Toast.LENGTH_SHORT).show();
    }
    protected void dbFindAll(){
        data.clear();
        cursor=db.rawQuery("select *from friends order by _id ASC",null);
        item=new HashMap<String,Object>();
        item.put("_id","序号");item.put("name","姓名");
        item.put("age","年龄");data.add(item);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            String id=cursor.getString(0);
            String name=cursor.getString(1);
            String age=cursor.getString(2);
            item=new HashMap<String, Object>();
            item.put("_id",id);
            item.put("name",name);
            item.put("age",age);
            data.add(item);
            cursor.moveToNext();
        }
        showList();
    }
}
