package com.auditoria.sqlitecrud.views;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.auditoria.sqlitecrud.R;
import com.auditoria.sqlitecrud.adapters.PersonListAdapter;
import com.auditoria.sqlitecrud.dao.PersonDataSource;
import com.auditoria.sqlitecrud.models.Person;

import java.sql.SQLException;
import java.util.List;


public class PersonActivity extends Activity {

    private PersonDataSource dataSource;
    private Person user = null;
    public static final int NEW_PERSON = 100;
    public static final int EDIT_PERSON = 200;
    public static final String PERSON_ID = "person_id";
    private PersonListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);

        dataSource = new PersonDataSource(this);
        try {
            dataSource.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Person> values = dataSource.getAllUsers();

        adapter = new PersonListAdapter(this, R.layout.person_list_view, values);
        setListAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.person, menu);
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
