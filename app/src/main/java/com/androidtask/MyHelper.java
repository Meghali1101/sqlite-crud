package com.androidtask;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyHelper extends SQLiteOpenHelper {

    private static final String dbname = "MyDbName";

    public MyHelper(Context context){
        super(context,dbname,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table userDetails " + "(id integer primary key autoincrement, name text,email text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS userDetails");
        onCreate(db);
    }

    public boolean insertDetails (String name, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("email", email);
        db.insert("userDetails", null, contentValues);
        return true;
    }

    public Cursor getDetails() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from userDetails",null );
        return res;
    }

    public Integer deleteDetail (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("userDetails", "id = ? ", new String[] { Integer.toString(id) });
    }

    public boolean updateDetails (Integer id, String name, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("email", email);
        db.update("userDetails", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

}
