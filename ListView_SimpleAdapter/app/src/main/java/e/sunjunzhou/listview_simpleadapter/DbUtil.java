package e.sunjunzhou.listview_simpleadapter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbUtil extends SQLiteOpenHelper {
    public DbUtil(Context context){
        super(context,"test.db",null,1);
    }
    public void onCreate(SQLiteDatabase db){
        String temp="CREATE TABLE person(_id INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR(20),age int)";
        db.execSQL(temp);
        db.execSQL("insert into person values(null,'wq',22)");
        db.execSQL("insert into person values(null,'tj',22)");
        db.execSQL("insert into person values(null,'zy',19)");
        db.execSQL("insert into person values(null,'孙俊洲',21)");
    }
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        db.execSQL("DROP TABLE IF EXISTS person");
    }
    public Cursor allQuery(SQLiteDatabase db){
        return db.query("person",null,null,null,null,null,"_id ASC");
    }
}
