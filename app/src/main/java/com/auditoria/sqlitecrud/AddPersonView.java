package com.auditoria.sqlitecrud;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.w3c.dom.Text;


public class AddPersonView extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person_view);

        Button createPerson = (Button) findViewById(R.id.btn_create_person);
        createPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Dio click al boton");
                DatabaseManager databaseManager = new DatabaseManager(getBaseContext());
                SQLiteDatabase db = databaseManager.getWritableDatabase();

                EditText txtName = (EditText) findViewById(R.id.txt_name);
                EditText txtLastName = (EditText) findViewById(R.id.txt_last_name);
                EditText txtAge = (EditText) findViewById(R.id.txt_age);

                String name = txtName.getText().toString();
                String lastName = txtLastName.getText().toString();
                String age = txtAge.getText().toString();

                ContentValues data = new ContentValues();
                data.put("name", name);
                data.put("last_name", lastName);
                data.put("age", age);
//
                try {
                    System.out.println("entro al try catch");
                    db.insert("persons", null, data);
                    db.close();
                    Intent returnManager = new Intent(getApplicationContext(), ManagePersonView.class);
                    finish();
                    Toast.makeText(getApplicationContext(), "The person has been added successfully", Toast.LENGTH_SHORT ).show();
                    startActivity(returnManager);
                }catch (Exception e){
                    db.close();
                    Toast.makeText(getApplicationContext(), "The person could not be added, please check your personal information", Toast.LENGTH_SHORT ).show();
                    System.out.println(Log.e("Fallo al insertar: ", e.getMessage()));
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add_person_view, menu);
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
}