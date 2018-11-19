package e.sunjunzhou.dbtransactiondemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    MyDbOpenHelper helper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper=new MyDbOpenHelper(this);
        db=helper.getWritableDatabase();
        setMit();
        db.close();
    }
    class MyDbOpenHelper extends SQLiteOpenHelper{
        public MyDbOpenHelper(Context context){
            super(context,"test.db",null,1);

        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE accounts(id char(6),name VARCHAR(6),balance double)");
            db.execSQL("insert into accounts values('111','tian',2000)");
            db.execSQL("insert into accounts values('222','liu',8000)");

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS accounts");
            onCreate(db);

        }
    }
    public void setMit(){
        Toast toast;
        db.beginTransaction();
        try {
            int num=500;
            String from="111";
            String to="222";
            db.execSQL("update accounts set balance=balance-? where id=?",new Object[]{num,from});
            int temp=10;
            db.execSQL("update accounts set balance=balance+? where id=?",new Object[]{num,to});
            db.setTransactionSuccessful();
            toast=Toast.makeText(MainActivity.this,"交易成功",Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();
        }catch (Exception e){
            e.printStackTrace();
            toast=Toast.makeText(MainActivity.this,"异常中断，交易未能成功",Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();

        }finally {
            db.endTransaction();
        }
    }
}
