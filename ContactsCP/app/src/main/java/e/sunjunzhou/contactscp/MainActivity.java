package e.sunjunzhou.contactscp;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private ContentResolver contentResolver;
    private SimpleAdapter listAdapter;
    Cursor cursor;
    private HashMap<String,String>item;
    private ArrayList<Map<String,String>>data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=(ListView)findViewById(R.id.listView);
        data=new ArrayList<Map<String, String>>();
        Uri uri= ContactsContract.Contacts.CONTENT_URI;
        contentResolver=this.getContentResolver();
        cursor=contentResolver.query(uri,null,null,null,null);
        TextView textView=(TextView)findViewById(R.id.textView);
        textView.setText("读取到"+String.valueOf(cursor.getCount()+"个联系人"));
        while (cursor.moveToNext()){
                int idFieldIndex=cursor.getColumnIndex(ContactsContract.Contacts._ID);
                int id=cursor.getInt(idFieldIndex);
                int nameFieldIndex=cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
                String name=cursor.getString(nameFieldIndex);
                int numCountFieldIndex=cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER);
                int numCount=cursor.getInt(numCountFieldIndex);
                String phoneNumber="";
                if (numCount>0){
                    Cursor phonecursor=getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,ContactsContract.CommonDataKinds.Phone.CONTACT_ID+"=?",
                            new String[]{Integer.toString(id)},null);
                    if (phonecursor.moveToNext()){
                        int numFieldIndex=phonecursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                        phoneNumber=phonecursor.getString(numFieldIndex);
                    }
                }
                item=new HashMap<String, String>();
                item.put("name",name);
                item.put("phoneNumber",phoneNumber);
                data.add(item);

        }
        listAdapter=new SimpleAdapter(this,data,android.R.id.simple_list_item_2,
                new String[]{"name","phoneNumber"},
                new int[]{android.R.id.text1,android.R.id.text2});
        listView.setAdapter(listAdapter);


    }
}
