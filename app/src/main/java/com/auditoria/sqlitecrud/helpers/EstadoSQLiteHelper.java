package com.auditoria.sqlitecrud.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by trejo on 11/6/14.
 */
public class EstadoSQLiteHelper extends SQLiteOpenHelper {
    public static final String TABLE = "persons";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NOMBRE = "nombre";
    public static final String COLUMN_DESCRIPCION = "descripcion";
    public static final String DATABASE_NAME = "estados.db";
    public static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "CREATE TABLE " +
            TABLE + "(" + COLUMN_ID + " integer primary key autoincrement," +
            COLUMN_NOMBRE + " text not null, " +
            COLUMN_DESCRIPCION + " text not null);";

    public EstadoSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(sqLiteDatabase);
    }
}
