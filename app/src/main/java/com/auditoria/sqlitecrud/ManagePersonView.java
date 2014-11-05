package com.auditoria.sqlitecrud;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.sql.SQLClientInfoException;


public class ManagePersonView extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_person_view);

        Button addPerson = (Button) findViewById(R.id.btn_add_person);
        addPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent showAddPersonView = new Intent(getApplicationContext(), AddPersonView.class);
                finish();
                startActivity(showAddPersonView);
            }
        });

        ListView persons = (ListView) findViewById(R.id.persons_list);
        Cursor cursor = getPersons();
        startManagingCursor(cursor);

        String[] 


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.manage_person_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public Cursor getPersons(){
        DatabaseManager databaseManager = new DatabaseManager(getBaseContext());
        SQLiteDatabase db = databaseManager.getReadableDatabase();
        return db.rawQuery("Select * from persons;", null);
    }
}
