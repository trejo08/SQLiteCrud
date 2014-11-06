package com.auditoria.sqlitecrud.views.person.person;

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
import com.auditoria.sqlitecrud.dao.PersonDataSource;

import java.sql.SQLException;
import java.util.Calendar;


public class CreatePerson extends Activity {

    private PersonDataSource dataSource;
    private long personId;
    public static final String NEW_PERSON = "newPerson";
    private int day, month, year;
    private static final int DATE_DIALOG_ID = 999;
    private boolean gender = false;

    // Views
    private EditText editTextNombre;
    private EditText editTextApellido;
    private EditText editTextEmail;
    private RadioGroup rdoGrpGender;
    private Button btnSetBirthDate;
    private TextView txtBirthdate;
    private DatePickerDialog datePickerDialog;
    private Button btnSave;
    private TextView tvMasterTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_person);

        dataSource = new PersonDataSource(this);
        try {
            dataSource.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        editTextNombre = (EditText)findViewById(R.id.editTextNombre);
        editTextApellido = (EditText)findViewById(R.id.editTextApellido);
        editTextEmail = (EditText)findViewById(R.id.editTextEmail);
        rdoGrpGender = (RadioGroup)findViewById(R.id.radioGrpGender);
        txtBirthdate = (TextView)findViewById(R.id.txtBirthdate);
        btnSetBirthDate = (Button)findViewById(R.id.btnSetBirthdate);
        btnSave = (Button)findViewById(R.id.btnAction);
        tvMasterTitle = (TextView)findViewById(R.id.textViewMasterTitle);

        tvMasterTitle.setText("Crear Persona");
        btnSave.setText("Guardar");

        rdoGrpGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rdoMale:
                        gender = false;
                        break;
                    case R.id.rdoFemale:
                        gender = true;
                        break;
                    default:
                        gender = false;
                        break;
                }
            }
        });

        btnSetBirthDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });

        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        txtBirthdate.setText(dayOfMonth + "-"
                                + (monthOfYear + 1) + "-" + year);

                    }
                }, year, month, day);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                personId = dataSource.createPerson(
                        editTextNombre.getText().toString(),
                        editTextApellido.getText().toString(),
                        gender,
                        txtBirthdate.getText().toString(),
                        editTextEmail.getText().toString()
                ).getId();
                try {
                    setResult(Activity.RESULT_OK, new Intent().putExtra(NEW_PERSON, personId));
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
