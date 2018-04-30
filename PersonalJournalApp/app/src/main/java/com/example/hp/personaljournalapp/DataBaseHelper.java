package com.example.hp.personaljournalapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by hp on 4/27/2018.
 */

public class DataBaseHelper extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "journal1.db";
    public static  final String TABLE_NAME = "journal1";

    public static final String COL_1 = "ID";
    public static final String COL_2 = "TITLE";
    public static final String COL_3 = "INFO";
    public static final String COL_4 = "DATE";




    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, TITLE TEXT, INFO TEXT, DATE TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
    public  boolean insertData(String title, String info,String date){
      SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_2,title);
        cv.put(COL_3,info);
        cv.put(COL_4,date);
        long result = db.insert(TABLE_NAME,null,cv);
        db.close();

        if(result == -1){
            return  false;
        }else {
            return  true;
        }
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("Select * from "+ TABLE_NAME,null);
        return res;
    }
}
