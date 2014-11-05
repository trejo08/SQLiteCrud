package com.auditoria.sqlitecrud;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class Main extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
}
