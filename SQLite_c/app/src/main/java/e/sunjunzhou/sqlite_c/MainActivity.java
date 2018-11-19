package e.sunjunzhou.sqlite_c;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyDbOpenHelper helper=new MyDbOpenHelper(this);
        helper.getWritableDatabase();
    }
    class MyDbOpenHelper extends SQLiteOpenHelper{
        public MyDbOpenHelper(Context context){
            super(context,"test.db",null,1);

        }
        public void onCreate(SQLiteDatabase db){
            db.execSQL("CREATE TABLE person(id INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR(20))");
            db.execSQL("insert into person values(null,'WU')");
            db.execSQL("insert into person values(null,'GUAN')");
        }
        public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
            db.execSQL("ALTER TABLE person ADD tel INTEGER");
            db.execSQL("ALTER TABLE person ADD tel INTEGER");
            db.execSQL("update person set tel=15527643858 where name='WU'");
            db.execSQL("update person set tel=13308627500 where name='GUAN'");
        }
    }
}
