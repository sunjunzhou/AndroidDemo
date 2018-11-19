package com.example.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.dao.DbHelper;

public class MyDAO {
    private SQLiteDatabase myDb;
    private DbHelper dbHelper;
    public MyDAO(Context context){
        dbHelper=new DbHelper(context,"test.db",null,1);

    }
    public Cursor allQuery(){
        myDb=dbHelper.getReadableDatabase();
        return myDb.rawQuery("select *from friend",null);
    }
    public void insertInfo(String name,int age){
        myDb=dbHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("name",name);
        values.put("age",age);
        long rowid=myDb.insert(DbHelper.TABLE_NAME,null,values);
        if (rowid==-1)
            Log.i("myDbDemo","数据插入失败！");
        else
            Log.i("myDbDemo","数据插入成功！"+rowid);
    }
    public void deleteInfo(String selId){
        String where="_id="+selId;
        int i=myDb.delete(DbHelper.TABLE_NAME,where,null);
        if (i>0)
            Log.i("myDbDemo","数据删除成功！");
        else
            Log.i("myDbDemo","数据未删除！");

    }
    public void updateInfo(String name,int age,String selId){
        ContentValues values=new ContentValues();
        values.put("name",name);
        values.put("age",age);
        String where="_id="+selId;
        int i=myDb.update(DbHelper.TABLE_NAME,values,where,null);
        if (i>0)
            Log.i("myDbDemo","数据更新成功");
        else
            Log.i("myDbDemo","数据未更新");
    }
}
