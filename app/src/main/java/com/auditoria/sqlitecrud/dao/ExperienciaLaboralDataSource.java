package com.auditoria.sqlitecrud.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;

import com.auditoria.sqlitecrud.helpers.ExperienciaLaboralSQLiteHelper;
import com.auditoria.sqlitecrud.models.ExperienciaLaboral;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

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
        ContentValues values = new ContentValues();
        values.put(ExperienciaLaboralSQLiteHelper.COLUMN_FECHA_INICIO, fecha_inicio);
        values.put(ExperienciaLaboralSQLiteHelper.COLUMN_FECHA_FINALIZACION, fecha_finalizacion);
        values.put(ExperienciaLaboralSQLiteHelper.COLUMN_DESCRIPCION, descripcion);

        long insertId = database.insert(ExperienciaLaboralSQLiteHelper.TABLE, null, values);
        Cursor cursor = database.query(
                ExperienciaLaboralSQLiteHelper.TABLE,
                allColumns,
                ExperienciaLaboralSQLiteHelper.COLUMN_ID + "=" + insertId,
                null, null, null, null);
        cursor.moveToFirst();

        ExperienciaLaboral newExperienciaLaboral = cursorToExperienciaLaboral(cursor);
        cursor.close();
        return newExperienciaLaboral;
    }

    public ExperienciaLaboral findExperiencia(long experienciaId){
        ExperienciaLaboral c = null;
        String query = "SELECT * FROM " + ExperienciaLaboralSQLiteHelper.TABLE + " WHERE _id = " + experienciaId;
        Cursor cursor = dbHelper.getReadableDatabase().rawQuery(query, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            c = cursorToExperienciaLaboral(cursor);
            cursor.moveToNext();
        }
        cursor.close();
        return c;
    }

    public List<ExperienciaLaboral> getALlExperiencias(){
        List<ExperienciaLaboral> experienciaLaborals = new ArrayList<ExperienciaLaboral>();
        Cursor cursor = database.query(
                ExperienciaLaboralSQLiteHelper.TABLE,
                allColumns,
                null,
                null,
                null,
                null,
                null
        );
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            experienciaLaborals.add(cursorToExperienciaLaboral(cursor));
            cursor.moveToNext();
        }
        cursor.close();
        return experienciaLaborals;
    }

    public void deleteExperiencia(long experienciaId){
        database.delete(
                ExperienciaLaboralSQLiteHelper.TABLE,
                ExperienciaLaboralSQLiteHelper.COLUMN_ID + " = " + experienciaId,
                null
        );
    }

    public ExperienciaLaboral updateExperiencia(ExperienciaLaboral experienciaLaboral){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ExperienciaLaboralSQLiteHelper.COLUMN_FECHA_INICIO, experienciaLaboral.getFecha_inicio());
        contentValues.put(ExperienciaLaboralSQLiteHelper.COLUMN_FECHA_FINALIZACION, experienciaLaboral.getFecha_finalizacion());
        contentValues.put(ExperienciaLaboralSQLiteHelper.COLUMN_DESCRIPCION, experienciaLaboral.getDescripcion());

        long insertId = database.update(
                ExperienciaLaboralSQLiteHelper.TABLE,
                contentValues,
                ExperienciaLaboralSQLiteHelper.COLUMN_ID + " = " + experienciaLaboral.getId(),
                null
        );
        return findExperiencia(insertId);
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
