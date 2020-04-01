package com.example.budgetexchange;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;

import java.util.ArrayList;
import java.util.List;

public class regPgOne extends AppCompatActivity {

    EditText fName, lName, password, zID, email, discipline, stDate, wkIncome;
    AutoCompleteTextView university;
    Button insertStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.one_pg_reg);

        university = (AutoCompleteTextView) findViewById(R.id.university);

        ArrayAdapter<University> adapter = new ArrayAdapter<> (this, android.R.layout.simple_dropdown_item_1line, University.getUniversities());
        university.setAdapter(adapter);

        insertStudent = (Button) findViewById(R.id.insertStudent);

        insertStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fName = (EditText) findViewById(R.id.fName);
                lName = (EditText) findViewById(R.id.lName);
                password = (EditText) findViewById(R.id.password);
                zID = (EditText) findViewById(R.id.zID);
                email = (EditText) findViewById(R.id.email);
                discipline = (EditText) findViewById(R.id.discipline);
                stDate = (EditText) findViewById(R.id.stDate);
                wkIncome = (EditText) findViewById(R.id.wkIncome);


                Students.addStudents(
                    fName.getText().toString(),
                    lName.getText().toString(),
                    password.getText().toString(),
                    zID.getText().toString(),
                    email.getText().toString(),
                    discipline.getText().toString(),
                    university.getText().toString(),
                    stDate.getText().toString(),
                    Float.valueOf(wkIncome.getText().toString())
                );
            }
        })
    ;}
}
