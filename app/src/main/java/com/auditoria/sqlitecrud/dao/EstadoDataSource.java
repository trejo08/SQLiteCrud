package com.auditoria.sqlitecrud.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.auditoria.sqlitecrud.helpers.EstadoSQLiteHelper;
import com.auditoria.sqlitecrud.helpers.PersonSQLiteHelper;
import com.auditoria.sqlitecrud.models.Estado;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by trejo on 11/6/14.
 */
public class EstadoDataSource {
    // Database fields
    private SQLiteDatabase database;
    private EstadoSQLiteHelper dbHelper;
    private String[] allColumns  = {EstadoSQLiteHelper.COLUMN_ID,
            EstadoSQLiteHelper.COLUMN_NOMBRE, EstadoSQLiteHelper.COLUMN_DESCRIPCION
    };

    public EstadoDataSource(Context context){
        dbHelper = new EstadoSQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Estado createEstado(String nombre, String descripcion){
        ContentValues values = new ContentValues();
        values.put(EstadoSQLiteHelper.COLUMN_NOMBRE, nombre);
        values.put(EstadoSQLiteHelper.COLUMN_DESCRIPCION, descripcion);

        long insertId = database.insert(EstadoSQLiteHelper.TABLE, null, values);
        Cursor cursor = database.query(EstadoSQLiteHelper.TABLE, allColumns,
                EstadoSQLiteHelper.COLUMN_ID + "=" + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Estado newEstado = cursorToEstado(cursor);
        cursor.close();
        return newEstado;
    }

    public Estado findEstado(long estadoId){
        Estado c = null;
        String query = "SELECT * FROM " + EstadoSQLiteHelper.TABLE + " WHERE _id = " + estadoId;
        Cursor cursor = dbHelper.getReadableDatabase().rawQuery(query, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            c = cursorToEstado(cursor);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return c;
    }

    public List<Estado> getAllEstados(){
        List<Estado> estados = new ArrayList<Estado>();
        Cursor cursor = database.query(EstadoSQLiteHelper.TABLE,
                allColumns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            estados.add(cursorToEstado(cursor));
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return estados;
    }

    public void deleteEstado(long estadoId) {
        database.delete(EstadoSQLiteHelper.TABLE, EstadoSQLiteHelper.COLUMN_ID
                + " = " + estadoId, null);
    }

    public Estado updateEstado(Estado estado){
        ContentValues values = new ContentValues();
        values.put(EstadoSQLiteHelper.COLUMN_NOMBRE, estado.getNombre());
        values.put(EstadoSQLiteHelper.COLUMN_DESCRIPCION, estado.getDescripcion());
        long insertId = database.update(EstadoSQLiteHelper.TABLE, values, EstadoSQLiteHelper.COLUMN_ID + " = " + estado.getId(), null);
        return findEstado(insertId);
    }

    private Estado cursorToEstado(Cursor cursor) {
        return new Estado(cursor.getLong(0), cursor.getString(1), cursor.getString(2));
    }
}
