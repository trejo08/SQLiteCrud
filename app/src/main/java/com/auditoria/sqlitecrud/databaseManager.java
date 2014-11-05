package com.auditoria.sqlitecrud;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

import java.io.File;

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
}