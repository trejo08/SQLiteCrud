package com.auditoria.sqlitecrud;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by trejo on 11/5/14.
 */
public class DatabaseManager extends SQLiteOpenHelper {
//    private static String DB_PATH = "/sdcard/auditoria.com/databases/";
    private static String DB_NAME = "db_auditoria.db";
//    private static String DATABASE = DB_PATH + DB_NAME;
    private static String DATABASE = DB_NAME;
    private static int version = 1;

    //Contructor de la Base de Datos
    public DatabaseManager(Context context){
        super(context, DATABASE, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
//        if (db.isReadOnly()){
//            db = getWritableDatabase();
//        }
//        db.execSQL("");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
//        if (newVersion > oldVersion){
//            db = getWritableDatabase();
//        }
//        db.execSQL("");
    }

    //Public Method for insert values into a table
    public boolean insertValue(ContentValues contentValues, String table){
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.insert(table, null, contentValues);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    // Public Method for update register
    public boolean updateValue(ContentValues contentValues, String table, int id){
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.update(table, contentValues, " id = ?", new String[] { Integer.toString(id) });
            return true;
        }catch (Exception e){
            return false;
        }
    }

    //Public Method for delete a row
    public Integer deleteValue(String table, Integer id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(table, " id = ?", new String[] { Integer.toString(id)});
    }

    // Public Method for get all data from identifier
    public Cursor getData(String table, int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("select * from "+ table +" where id="+ id +"",null);
        return result;
    }

    // Public Method for get all data from specific table
    public ArrayList getAllData(String table, String column){
        ArrayList arrayList = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("select * from " + table, null);
        result.moveToFirst();
        while (result.isAfterLast() == false){
            arrayList.add(result.getString(result.getColumnIndex(column)));
            result.moveToNext();
        }
        result.close();
        return arrayList;
    }
}