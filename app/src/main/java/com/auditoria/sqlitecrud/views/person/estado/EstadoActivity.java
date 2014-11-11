package com.auditoria.sqlitecrud.views.person.estado;

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
import com.auditoria.sqlitecrud.adapters.EstadoListAdapter;
import com.auditoria.sqlitecrud.dao.EstadoDataSource;
import com.auditoria.sqlitecrud.models.Estado;
import com.auditoria.sqlitecrud.views.person.persona.EditPersonActivity;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by trejo on 11/6/14.
 */
public class EstadoActivity extends ListActivity {
    private EstadoDataSource dataSource;
    private Estado estado = null;
    public static final int NEW_ESTADO = 100;
    public static final int EDIT_ESTADO = 200;
    public static final String ESTADO_ID = "estado_id";
    private EstadoListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);

        dataSource = new EstadoDataSource(this);
        try {
            dataSource.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Estado> values = dataSource.getAllEstados();

        adapter = new EstadoListAdapter(this, R.layout.list_view_form, values);
        setListAdapter(adapter);

        ((Button)findViewById(R.id.add)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getApplicationContext(),CreateEstado.class), NEW_ESTADO);
            }
        });

        ((Button)findViewById(R.id.delete)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getListAdapter().getCount() > 0) {
                    estado = (Estado) getListAdapter().getItem(0);
                    dataSource.deleteEstado(estado.getId());
                    adapter.remove(estado);
                    adapter.notifyDataSetChanged();
                }
            }
        });
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Estado estado1 = (Estado)getListAdapter().getItem(i);
                Intent intent = new Intent(getApplicationContext(), EditPersonActivity.class);
                intent.putExtra(ESTADO_ID, estado1.getId());
                startActivityForResult(intent, EDIT_ESTADO);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case NEW_ESTADO:
                if(resultCode == Activity.RESULT_OK){
                    adapter.add(
                            dataSource.findEstado(data.getLongExtra(CreateEstado.NEW_ESTADO, 0)));
                    adapter.notifyDataSetChanged();
                }
                break;
            case EDIT_ESTADO:
                if(resultCode == Activity.RESULT_OK){
                    try {
                        dataSource.open();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    adapter = new EstadoListAdapter(this, R.layout.list_view_form, dataSource.getAllEstados());
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
