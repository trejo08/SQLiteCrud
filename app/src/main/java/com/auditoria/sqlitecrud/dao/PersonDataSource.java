package com.auditoria.sqlitecrud.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.auditoria.sqlitecrud.helpers.PersonSQLiteHelper;
import com.auditoria.sqlitecrud.models.Person;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by trejo on 11/6/14.
 */
public class PersonDataSource {
    // Database fields
    private SQLiteDatabase database;
    private PersonSQLiteHelper dbHelper;
    private String[] allColumns  = {PersonSQLiteHelper.COLUMN_ID,
            PersonSQLiteHelper.COLUMN_FIRST_NAME, PersonSQLiteHelper.COLUMN_LAST_NAME,
            PersonSQLiteHelper.COLUMN_GENDER, PersonSQLiteHelper.COLUMN_BIRTHDATE,
            PersonSQLiteHelper.COLUMN_EMAIL
    };

    public PersonDataSource(Context context){
        dbHelper = new PersonSQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Person createPerson(String nombre, String apellido, boolean gender, String birthdate, String email){
        ContentValues values = new ContentValues();
        values.put(PersonSQLiteHelper.COLUMN_FIRST_NAME, nombre);
        values.put(PersonSQLiteHelper.COLUMN_LAST_NAME, apellido);
        values.put(PersonSQLiteHelper.COLUMN_GENDER, gender);
        values.put(PersonSQLiteHelper.COLUMN_BIRTHDATE, birthdate);
        values.put(PersonSQLiteHelper.COLUMN_EMAIL, email);

        long insertId = database.insert(PersonSQLiteHelper.TABLE, null, values);
        Cursor cursor = database.query(PersonSQLiteHelper.TABLE, allColumns,
                PersonSQLiteHelper.COLUMN_ID + "=" + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Person newPerson = cursorToPerson(cursor);
        cursor.close();
        return newPerson;
    }

    public Person findPerson(long userId){
        Person c = null;
        String query = "SELECT * FROM " + PersonSQLiteHelper.TABLE + " WHERE _id = " + userId;
        Cursor cursor = dbHelper.getReadableDatabase().rawQuery(query, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            c = cursorToPerson(cursor);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return c;
    }

    public List<Person> getAllUsers(){
        List<Person> users = new ArrayList<Person>();
        Cursor cursor = database.query(PersonSQLiteHelper.TABLE,
                allColumns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            users.add(cursorToPerson(cursor));
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return users;
    }

    public void deleteUser(long userId) {
        database.delete(PersonSQLiteHelper.TABLE, PersonSQLiteHelper.COLUMN_ID
                + " = " + userId, null);
    }

    public Person updatePerson(Person person){
        ContentValues values = new ContentValues();
        values.put(PersonSQLiteHelper.COLUMN_FIRST_NAME, person.getNombre());
        values.put(PersonSQLiteHelper.COLUMN_LAST_NAME, person.getApellido());
        values.put(PersonSQLiteHelper.COLUMN_GENDER, person.isGender());
        values.put(PersonSQLiteHelper.COLUMN_BIRTHDATE, person.getBirthdate());
        values.put(PersonSQLiteHelper.COLUMN_EMAIL, person.getEmail());
        long insertId = database.update(PersonSQLiteHelper.TABLE, values, PersonSQLiteHelper.COLUMN_ID + " = " + person.getId(), null);
        return findPerson(insertId);
    }

    private Person cursorToPerson(Cursor cursor) {
        return new Person(cursor.getLong(0), cursor.getString(1), cursor.getString(2),
                (cursor.getInt(4) > 0 ? true : false), cursor.getString(3),
                cursor.getString(5));
    }
}
