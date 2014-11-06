package com.auditoria.sqlitecrud.views.person.estado;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.auditoria.sqlitecrud.R;
import com.auditoria.sqlitecrud.dao.EstadoDataSource;

import java.sql.SQLException;

/**
 * Created by trejo on 11/6/14.
 */
public class CreateEstado extends Activity {
    private EstadoDataSource dataSource;
    private long estadoId;
    public static final String NEW_ESTADO = "newEstado";

    // Views
    private EditText editTextNombre;
    private EditText editTextDescripcion;
    private Button btnSave;
    private TextView tvMasterTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_person);

        dataSource = new EstadoDataSource(this);
        try {
            dataSource.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        editTextNombre = (EditText)findViewById(R.id.editTextNombre);
        editTextDescripcion = (EditText)findViewById(R.id.editTextDescription);
        btnSave = (Button)findViewById(R.id.btnAction);
        tvMasterTitle = (TextView)findViewById(R.id.textViewMasterTitle);

        tvMasterTitle.setText("Crear Estado");
        btnSave.setText("Guardar");

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                estadoId = dataSource.createEstado(
                        editTextNombre.getText().toString(),
                        editTextDescripcion.getText().toString()
                ).getId();
                try {
                    setResult(Activity.RESULT_OK, new Intent().putExtra(NEW_ESTADO, estadoId));
                }catch (Exception e){
                    System.out.println(e.getMessage().toString());
                }

                finish();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.create_user, menu);
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
