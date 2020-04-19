package com.example.budgetexchange;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class regPgOne extends AppCompatActivity {

    private static final String TAG = "Student Reg Status";
    EditText fName, lName, password, conPassword, zID, email, discipline, stDate, wkIncome;
    AutoCompleteTextView university;
    Button insertStudent;
    public final static String NEW_USERNAME ="zID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.one_pg_reg);
        List<String> universityList = new ArrayList<>();
        universityList.add(new University().getName());

        university = (AutoCompleteTextView) findViewById(R.id.university);

        fName = (EditText) findViewById(R.id.fName);
        lName = (EditText) findViewById(R.id.lName);
        password = (EditText) findViewById(R.id.password);
        conPassword = (EditText) findViewById(R.id.conPassword);
        zID = (EditText) findViewById(R.id.zID);
        email = (EditText) findViewById(R.id.email);
        discipline = (EditText) findViewById(R.id.discipline);
        stDate = (EditText) findViewById(R.id.stDate);

        wkIncome = (EditText) findViewById(R.id.wkIncome);

        //Adapter for holding the data view
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line,
                universityList);

        university.setAdapter(adapter);

        insertStudent = (Button) findViewById(R.id.insertStudent);

        insertStudent.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
            Students.addStudents(
                fName.getText().toString(),
                lName.getText().toString(),
                password.getText().toString(),
                zID.getText().toString(),
                email.getText().toString(),
                discipline.getText().toString(),
                university.getText().toString(),
                startDate(zID.getText().toString()),
                Float.parseFloat(String.valueOf(wkIncome.getText()))
            );

            Log.d(TAG, "open validation methods");
            iDCheck();


            }
        })
    ;}

    public void iDCheck () {
        //Error checking, making sure fields are correct

        if (zID.getText().toString().equals("")) {
            Toast.makeText(this, "Please fill out the username field", Toast.LENGTH_SHORT).show();

            Log.d(TAG, "missing username");

        } else if (password.getText().toString().equals("") || conPassword.getText().toString().equals("")) {
            Toast.makeText(this, "Please fill out both password fields", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "missing password");

        } else if (!password.getText().toString().equals(conPassword.getText().toString())) {
            Toast.makeText(this, "Passwords did not match", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "missing confirm pw");

        } else {
            // Correct credentials, passes username and password to Home Activity
            System.out.println(String.format("LOGIN DETAILS PASSED \nUsername: %s \nPassword: %s \nPassword: %s",
                    zID.getText(), password.getText(), conPassword.getText()));

            Log.d(TAG, "check zID");

            for(int i = 0; i < Students.getStudents().size(); i++) {

                if (Students.getStudents().get(i).getzID().equals(String.valueOf(zID))){
                    Log.d(TAG, "ID has been taken");
                }

                else {
                    Log.d(TAG, "Passed validation");
                    // enterDataToDatabase;

                    universityCheck();


                }
            }

        }
    }

    public void universityCheck() {
        for(int j = 0; j < University.getUniversities().size(); j++) {

            if (University.getUniversities().get(j).getName().equals(String.valueOf(university.getText()))){
                Log.d(TAG, "University is in the Arraylist");
                openLoginActivity();

            }

            else {
                Log.d(TAG, "University is not on the list");
                Toast.makeText(this, "University is not on the list", Toast.LENGTH_SHORT).show();
                // enterDataToDatabase;
            }
        }
    }


    public void enterDataToDatabase() {

    }

    private String startDate (String date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = date;

        try {
            Date startDate = formatter.parse(dateString);
            startDate = formatter.parse(stDate.getText().toString());

        } catch (ParseException e){
            e.printStackTrace();
        }
        return date;
    }

    private void openLoginActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("Username", zID.getText().toString());
        startActivity(intent);
    }
}
