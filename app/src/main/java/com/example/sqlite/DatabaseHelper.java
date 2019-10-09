package com.example.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME="User.db";
    public static String TABLE_NAME="User";
    public static String COL_ID="Id";
    public static int VERSION=1;
    public static String COL_NAME="Name";
    public static String COL_AGE="Age";
    public String create_table= "create table "+TABLE_NAME+"(Id integer primary key, Name text,Age text)";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(create_table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public  long insertData(String Name,String Age){
        ContentValues contentValues= new ContentValues();
        contentValues.put(COL_NAME,Name);
contentValues.put(COL_AGE,Age);
SQLiteDatabase sqLiteDatabase = getWritableDatabase();
long id= sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
sqLiteDatabase.close();
return id;


    }

    public  Cursor showdata(){
        String Showall = "SELECT * From "+TABLE_NAME;
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor= sqLiteDatabase.rawQuery(Showall,null);
        return  cursor;
    }
}
