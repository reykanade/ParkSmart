package com.example.vicky.parksmart;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;

/**
 * Created by vicky on 08/04/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ParkSmartDB";
    private static final String TABLE_NAME = "user_table";
    private static final String COL_1 = "id";
    private static final String COL_2 = "user_key";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    public  void init(){
        SQLiteDatabase db = this.getWritableDatabase();
        String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+" (id INTEGER,user_key TEXT)";
        // String ALTER_TABLE = "ALTER TABLE MOSTPLAYEDSONG ADD COLUMN liked INTEGER";

        // create books table
        try {
            db.execSQL(CREATE_TABLE);
        } catch (Exception e) {

            Log.i("database", "failed" + e);
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static String getDBName(){
        return DATABASE_NAME;
    }

    public boolean insertData(String username){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_1,1);
        contentValues.put(COL_2,username);
        long  result=db.insert(TABLE_NAME,null,contentValues);
        if(result== -1)
            return false;
        else
            return true;
    }
    public void updateTbl(String user_n){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_2,user_n);
        int result=db.update(TABLE_NAME,contentValues,null,null);
        Log.d("result=","RESULT="+result);
    }

    public Cursor getAllData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from "+TABLE_NAME,null);
        return cursor;
    }
    public String getUserKey()
    {
        Cursor cursor=getAllData();
        cursor.moveToNext();
        String userKey=cursor.getString(1);
        return userKey;
    }
    public Boolean deleteSQLDatabase(){

        return App.context.deleteDatabase(DATABASE_NAME);
    }

    public static boolean doesDatabaseExist(Context context) {
        File dbFile = context.getDatabasePath(DATABASE_NAME);
        return dbFile.exists();
    }
/*    public static boolean doesTableExist(){

    }*/
}