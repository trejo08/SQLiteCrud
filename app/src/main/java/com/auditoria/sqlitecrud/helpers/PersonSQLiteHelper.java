package com.auditoria.sqlitecrud.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by trejo on 11/6/14.
 */
public class ExperienciaLaboralSQLiteHelper extends SQLiteOpenHelper {
    public static final String TABLE = "persons";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_FIRST_NAME = "nombre";
    public static final String COLUMN_LAST_NAME = "apellido";
    public static final String COLUMN_BIRTHDATE = "birthdate";
    public static final String COLUMN_GENDER = "genero";
    public static final String COLUMN_EMAIL = "email";
    public static final String DATABASE_NAME = "persons.db";
    public static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "CREATE TABLE " +
            TABLE + "(" + COLUMN_ID + " integer primary key autoincrement," +
            COLUMN_FIRST_NAME + " text not null, " +
            COLUMN_LAST_NAME + " text not null, " +
            COLUMN_BIRTHDATE + " text not null, " +
            COLUMN_GENDER + " integer not null, " +
            COLUMN_EMAIL + " text not null);";

    public PersonSQLiteHelper(Context context){
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
