package com.auditoria.sqlitecrud.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.auditoria.sqlitecrud.models.ExperienciaLaboral;

/**
 * Created by trejo on 11/10/14.
 */
public class ExperienciaLaboralSQLiteHelper extends SQLiteOpenHelper {
    public static final String TABLE = "experiencia_laboral";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_FECHA_INICIO = "fecha_inicio";
    public static final String COLUMN_FECHA_FINALIZACION = "fecha_finalizacion";
    public static final String COLUMN_DESCRIPCION = "descripcion";
    public static final String DATABASE_NAME = "experiencia_laboral.db";
    public static final int DATABASE_VERSION = 1;

    // Database creation sql statement
    private static final String DATABASE_CREATE = "CREATE TABLE " +
            TABLE + "(" + COLUMN_ID + " integer primary key autoincrement," +
            COLUMN_FECHA_INICIO + " text not null, " +
            COLUMN_FECHA_FINALIZACION + " text not null, " +
            COLUMN_DESCRIPCION + " text not null);";

    public ExperienciaLaboralSQLiteHelper(Context context){
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
