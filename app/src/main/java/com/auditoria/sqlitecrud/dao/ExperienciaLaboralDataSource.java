package com.auditoria.sqlitecrud.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.auditoria.sqlitecrud.helpers.PersonSQLiteHelper;

/**
 * Created by trejo on 11/10/14.
 */
public class ExperienciaLaboralDataSource {
    private SQLiteDatabase database;
    private ExperienciaLaboralSQLiteHelper dbHelper;
    private String[] allColumns  = {
            PersonSQLiteHelper.COLUMN_ID,
            PersonSQLiteHelper.COLUMN_FIRST_NAME,
            PersonSQLiteHelper.COLUMN_LAST_NAME,
            PersonSQLiteHelper.COLUMN_GENDER,
            PersonSQLiteHelper.COLUMN_BIRTHDATE,
            PersonSQLiteHelper.COLUMN_EMAIL
    };

    public ExperienciaLaboralDataSource(Context context){
        dbHelper = new ExperienciaLaboralSQLiteHelper(context);
    }
}
