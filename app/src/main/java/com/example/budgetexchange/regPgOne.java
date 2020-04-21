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

import com.example.budgetexchange.DataBase.Student.Student;
import com.google.android.material.snackbar.Snackbar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class regPgOne extends AppCompatActivity {
    private DateValidator dateValidator;
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
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, Collections.singletonList(com.example.budgetexchange.DataBase.University.University.class.getName()));

        university.setAdapter(adapter);


        insertStudent = (Button) findViewById(R.id.insertStudent);

        clickSignUp();

    }


    private void clickSignUp() {
        insertStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (fName.getText().toString().trim().isEmpty()) {
                    Snackbar snackbar = Snackbar.make(v, "Please fill out these fields", Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    snackbarView.setBackgroundColor(getResources().getColor(R.color.red));
                    snackbar.show();
                    fName.setError("First Name should not be empty");

                } else if (lName.getText().toString().trim().isEmpty()) {
                    Snackbar snackbar = Snackbar.make(v, "Please fill out these fields", Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    snackbarView.setBackgroundColor(getResources().getColor(R.color.red));
                    snackbar.show();
                    lName.setError("Last Name should not be empty");

                } else if (password.getText().toString().trim().isEmpty()) {
                    Snackbar snackbar = Snackbar.make(v, "Please fill out these fields", Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    snackbarView.setBackgroundColor(getResources().getColor(R.color.red));
                    snackbar.show();
                    password.setError("Password should not be empty");

                } else if (conPassword.getText().toString().trim().isEmpty()) {
                    Snackbar snackbar = Snackbar.make(v, "Please fill out these fields", Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    snackbarView.setBackgroundColor(getResources().getColor(R.color.red));
                    snackbar.show();
                    conPassword.setError("Confirm Password should not be empty");

                } else if (!password.getText().toString().equals(conPassword.getText().toString())) {
                    Snackbar snackbar = Snackbar.make(v, "Passwords do not match", Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    snackbarView.setBackgroundColor(getResources().getColor(R.color.red));
                    snackbar.show();
                    password.setError("Passwords do not match");
                    conPassword.setError("Passwords do not match");

                } else if (zID.getText().toString().trim().isEmpty()) {
                    Snackbar snackbar = Snackbar.make(v, "Please fill out these fields", Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    snackbarView.setBackgroundColor(getResources().getColor(R.color.red));
                    snackbar.show();
                    zID.setError("zID should not be empty");

                } else if (!zID.getText().toString().trim().isEmpty()) {

                    for(int i = 0; i < Students.getStudents().size(); i++) {
                        if (Students.getStudents().get(i).getzID().equals(String.valueOf(zID))){
                            Log.d(TAG, "ID has been taken");
                            Snackbar snackbar = Snackbar.make(v, "Please fill out these fields", Snackbar.LENGTH_LONG);
                            View snackbarView = snackbar.getView();
                            snackbarView.setBackgroundColor(getResources().getColor(R.color.red));
                            snackbar.show();
                            zID.setError("zID has been taken");
                        }

                        Log.d(TAG, "Passed zID validation");

                    }
                } else if (email.getText().toString().trim().isEmpty()) {
                    Snackbar snackbar = Snackbar.make(v, "Please fill out these fields", Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    snackbarView.setBackgroundColor(getResources().getColor(R.color.red));
                    snackbar.show();
                    email.setError("Email should not be empty");

                } else if (!email.getText().toString().trim().isEmpty()) {
                    for(int i = 0; i < Students.getStudents().size(); i++) {
                        if (Students.getStudents().get(i).getzID().equals(String.valueOf(email))){
                            Log.d(TAG, "ID has been taken");
                            Snackbar snackbar = Snackbar.make(v, "Please fill out these fields", Snackbar.LENGTH_LONG);
                            View snackbarView = snackbar.getView();
                            snackbarView.setBackgroundColor(getResources().getColor(R.color.red));
                            snackbar.show();
                            email.setError("Email has been taken");
                        }
                        else {
                            Log.d(TAG, "Passed validation");
                            // enterDataToDatabase;
                        }
                    }
                }

                else if (discipline.getText().toString().trim().isEmpty()) {
                    Snackbar snackbar = Snackbar.make(v, "Please fill out these fields", Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    snackbarView.setBackgroundColor(getResources().getColor(R.color.red));
                    snackbar.show();
                    discipline.setError("Discipline should not be empty");

                } else if (university.getText().toString().trim().isEmpty()) {
                    Snackbar snackbar = Snackbar.make(v, "Please fill out these fields", Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    snackbarView.setBackgroundColor(getResources().getColor(R.color.red));
                    snackbar.show();
                    university.setError("Exchange School should not be empty");

                } else if (!university.getText().toString().trim().isEmpty()){
                    for(int j = 0; j < University.getUniversities().size(); j++) {

                        if (University.getUniversities().get(j).getName().equals(String.valueOf(university.getText()))) {
                            Log.d(TAG, "University is in the Arraylist");

                        } else {
                            Snackbar snackbar = Snackbar.make(v, "Please fill out these fields", Snackbar.LENGTH_LONG);
                            View snackbarView = snackbar.getView();
                            snackbarView.setBackgroundColor(getResources().getColor(R.color.red));
                            snackbar.show();
                            university.setError("University is not in the list");
                            Log.d(TAG, "University is not in the Arraylist");
                        }
                    }
                } else if (stDate.getText().toString().trim().isEmpty()) {
                    Snackbar snackbar = Snackbar.make(v, "Please fill out these fields", Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    snackbarView.setBackgroundColor(getResources().getColor(R.color.red));
                    snackbar.show();
                    stDate.setError("Start Date should not be empty");

                } else if (!dateValidator.validate(stDate.getText().toString())) {
                    Snackbar snackbar = Snackbar.make(v, "Please fill out these fields", Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    snackbarView.setBackgroundColor(getResources().getColor(R.color.red));
                    snackbar.show();
                    stDate.setError("Invalid Start Date");

                } else if (wkIncome.getText().toString().trim().isEmpty()) {
                    Snackbar snackbar = Snackbar.make(v, "Please fill out these fields", Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    snackbarView.setBackgroundColor(getResources().getColor(R.color.red));
                    snackbar.show();
                    wkIncome.setError("Weekly Income should not be empty");

                } else {
                    Student student = new Student (
                        fName.getText().toString(),
                        lName.getText().toString(),
                        password.getText().toString(),
                        zID.getText().toString(),
                        email.getText().toString(),
                        discipline.getText().toString(),
                        university.getText().toString(),
                        stDate.getText().toString(),
                        Float.parseFloat(String.valueOf(wkIncome.getText()))
                    );

                    System.out.println(String.format("LOGIN DETAILS PASSED " +
                                                        "\nUsername: %s " +
                                                        "\nPassword: %s",
                                                        zID.getText(),
                                                        password.getText(),
                                                        conPassword.getText()));

                    openLoginActivity();
                    Toast.makeText(regPgOne.this, "Student saved", Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }

    private void openLoginActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("Username", zID.getText().toString());
        startActivity(intent);
    }
}
