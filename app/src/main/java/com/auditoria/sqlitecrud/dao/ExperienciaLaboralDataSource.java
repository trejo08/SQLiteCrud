package com.auditoria.sqlitecrud.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.auditoria.sqlitecrud.helpers.ExperienciaLaboralSQLiteHelper;
import com.auditoria.sqlitecrud.models.ExperienciaLaboral;

import java.sql.SQLException;

/**
 * Created by trejo on 11/10/14.
 */
public class ExperienciaLaboralDataSource {
    private SQLiteDatabase database;
    private ExperienciaLaboralSQLiteHelper dbHelper;
    private String[] allColumns  = {
            ExperienciaLaboralSQLiteHelper.COLUMN_ID,
            ExperienciaLaboralSQLiteHelper.COLUMN_FECHA_INICIO,
            ExperienciaLaboralSQLiteHelper.COLUMN_FECHA_FINALIZACION,
            ExperienciaLaboralSQLiteHelper.COLUMN_DESCRIPCION
    };

    public ExperienciaLaboralDataSource(Context context){
        dbHelper = new ExperienciaLaboralSQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public ExperienciaLaboral createExperienciaLaboral(String fecha_inicio, String fecha_finalizacion, String descripcion){
        ExperienciaLaboral newExperienciaLaboral = null;
        return newExperienciaLaboral;
    }

    private ExperienciaLaboral cursorToExperienciaLaboral(Cursor cursor){
        return new ExperienciaLaboral(
                cursor.getLong(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3)
        );
    }
}
