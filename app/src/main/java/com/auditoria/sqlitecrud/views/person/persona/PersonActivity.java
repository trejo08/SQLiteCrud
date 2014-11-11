package com.auditoria.sqlitecrud.views.person.persona;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;


import com.auditoria.sqlitecrud.R;
import com.auditoria.sqlitecrud.adapters.PersonListAdapter;
import com.auditoria.sqlitecrud.dao.PersonDataSource;
import com.auditoria.sqlitecrud.models.Person;

import java.sql.SQLException;
import java.util.List;


public class PersonActivity extends ListActivity {

    private PersonDataSource dataSource;
    private Person person = null;
    public static final int NEW_PERSON = 100;
    public static final int EDIT_PERSON = 200;
    public static final String PERSON_ID = "person_id";
    private PersonListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        if (getActionBar() != null) {
//            getActionBar().setDisplayHomeAsUpEnabled(true);
//        }

        setContentView(R.layout.activity_person);

        dataSource = new PersonDataSource(this);
        try {
            dataSource.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Person> values = dataSource.getAllUsers();

//        adapter = new PersonListAdapter(this, R.layout.person_list_view, values);
        adapter = new PersonListAdapter(this, R.layout.list_view_form, values);
        setListAdapter(adapter);

        ((Button)findViewById(R.id.add)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getApplicationContext(),CreatePerson.class),NEW_PERSON);
                /*String[] comments = new String[] { "Cool", "Very nice", "Hate it" };
                int nextInt = new Random().nextInt(3);
                adapter.add(dataSource.createComment(comments[nextInt]));
                adapter.notifyDataSetChanged();*/
            }
        });

        ((Button)findViewById(R.id.delete)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getListAdapter().getCount() > 0) {
                    person = (Person) getListAdapter().getItem(0);
                    dataSource.deleteUser(person.getId());
                    adapter.remove(person);
                    adapter.notifyDataSetChanged();
                }
            }
        });
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Person user = (Person)getListAdapter().getItem(i);
                Intent intent = new Intent(getApplicationContext(), EditPersonActivity.class);
                intent.putExtra(PERSON_ID, user.getId());
                startActivityForResult(intent, EDIT_PERSON);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case NEW_PERSON:
                if(resultCode == Activity.RESULT_OK){
                    adapter.add(
                            dataSource.findPerson(data.getLongExtra(CreatePerson.NEW_PERSON, 0)));
                    adapter.notifyDataSetChanged();
                }
                break;
            case EDIT_PERSON:
                if(resultCode == Activity.RESULT_OK){
                    try {
                        dataSource.open();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    adapter = new PersonListAdapter(this, R.layout.list_view_form, dataSource.getAllUsers());
                    setListAdapter(adapter);
                }
                break;
        }
    }

    @Override
    protected void onResume() {
        try {
            dataSource.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        super.onResume();
    }

    @Override
    protected void onPause() {
        dataSource.close();
        super.onPause();
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
