package com.auditoria.sqlitecrud.views.person.person;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.auditoria.sqlitecrud.R;
import com.auditoria.sqlitecrud.dao.PersonDataSource;
import com.auditoria.sqlitecrud.models.Person;

import java.sql.SQLException;

public class EditPersonActivity extends Activity {

    //data
    private boolean gender;
    private int day, month, year;
    private static final int DATE_DIALOG_ID = 999;
    private Person person;
    private PersonDataSource dataSource;

    // Views
    private EditText editTextFirstName;
    private EditText editTextLastName;
    private EditText editTextEmail;
    private RadioGroup rdoGrpGender;
    private Button btnSetBirthDate;
    private TextView txtBirthdate;
    private DatePickerDialog datePickerDialog;
    private Button btnEdit;
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

        editTextFirstName = (EditText)findViewById(R.id.editTextNombre);
        editTextLastName = (EditText)findViewById(R.id.editTextApellido);
        editTextEmail = (EditText)findViewById(R.id.editTextEmail);
        rdoGrpGender = (RadioGroup)findViewById(R.id.radioGrpGender);
        txtBirthdate = (TextView)findViewById(R.id.txtBirthdate);
        btnSetBirthDate = (Button)findViewById(R.id.btnSetBirthdate);
        btnEdit = (Button)findViewById(R.id.btnAction);
        tvMasterTitle = (TextView)findViewById(R.id.textViewMasterTitle);

        tvMasterTitle.setText("Editar Persona");
        btnEdit.setText("Actualizar");

        rdoGrpGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rdoMale:
                        gender = false;
                        break;
                    case R.id.rdoFemale:
                        gender = true;
                        break;
                    default:
                        gender = false;
                }
            }
        });

        btnSetBirthDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });

        datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        txtBirthdate.setText(dayOfMonth + "-"
                                + (monthOfYear + 1) + "-" + year);

                    }
                }, year, month, day);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                person.setNombre(editTextFirstName.getText().toString());
                person.setApellido(editTextLastName.getText().toString());
                person.setGender(gender);
                person.setBirthdate(txtBirthdate.getText().toString());
                person.setEmail(editTextEmail.getText().toString());
                dataSource.updatePerson(person);
                setResult(Activity.RESULT_OK);
                finish();
            }
        });

        person = dataSource.findPerson(getIntent().getLongExtra(PersonActivity.PERSON_ID, 0));
        if(person != null)
            this.setDataForm();


    }

    private void setDataForm(){
        editTextFirstName.setText(person.getNombre());
        editTextLastName.setText(person.getApellido());
        editTextEmail.setText(person.getEmail());
        if(person.isGender())
            ((RadioButton)findViewById(R.id.rdoFemale)).setChecked(true);
        else
            ((RadioButton)findViewById(R.id.rdoMale)).setChecked(true);
        txtBirthdate.setText(person.getBirthdate());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.edit_person, menu);
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
