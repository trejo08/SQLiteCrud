package com.auditoria.sqlitecrud;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class Main extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Creating tables if not exists
        createTables();

        /* setting buttons */
        Button managePerson = (Button) findViewById(R.id.btn_manage_person);
        Button manageStatus = (Button) findViewById(R.id.btn_manage_status);
        Button manageStudies = (Button) findViewById(R.id.btn_manage_studies);
        Button manageSpecialities = (Button) findViewById(R.id.btn_manage_speciality);
        Button manageLaboralSituations = (Button) findViewById(R.id.btn_manage_laboral_situation);

        /* showing next activities */
        managePerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToPerson = new Intent(getApplicationContext(),ManagePersonView.class);
                startActivity(goToPerson);
            }
        });



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    public void createTables(){
        DatabaseManager managerDB = new DatabaseManager(getBaseContext());
        SQLiteDatabase db = managerDB.getWritableDatabase();

        //if database has been opened successfully, we procedding to create tables
        if (db != null){
            String tPerson = "create table if not exists persons (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, last_name TEXT, age TEXT, address CHAR(50));";
//            String tStatus = "create table if not exists states (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT);";
            db.execSQL(tPerson);
            System.out.println("the table has been created successfully");
        }else{
            System.out.println("An error has occurred, please check the connection");
        }


    }
}
