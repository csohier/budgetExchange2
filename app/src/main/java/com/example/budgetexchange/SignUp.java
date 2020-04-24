package com.example.budgetexchange;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.budgetexchange.DataBase.AppDatabase;
import com.example.budgetexchange.DataBase.Student.Student;
import com.google.android.material.snackbar.Snackbar;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class SignUp extends AppCompatActivity {
    private DateValidator dateValidator;
    private static final String TAG = "Student Reg Status";
    EditText fName, lName, password, conPassword, zID, email, stDate, wkIncome;
    Spinner spinner;
    Button insertStudent;
    private AppDatabase mDb;
    public final static String NEW_USERNAME ="zID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        DateValidator dateValidator = new DateValidator();

        fName = (EditText) findViewById(R.id.fName);
        lName = (EditText) findViewById(R.id.lName);
        password = (EditText) findViewById(R.id.password);
        conPassword = (EditText) findViewById(R.id.conPassword);
        zID = (EditText) findViewById(R.id.zID);
        email = (EditText) findViewById(R.id.email);
        stDate = (EditText) findViewById(R.id.stDate);
        wkIncome = (EditText) findViewById(R.id.wkIncome);
        spinner = (Spinner) findViewById(R.id.university);

        mDb = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "coins-database")
                .allowMainThreadQueries()
                .build();

        /*List<com.example.budgetexchange.DataBase.University.University> university =
                UniversityDB.getInstance(this).universityDao().allUniversity();

        //Adapter for holding the data view
        ArrayAdapter<com.example.budgetexchange.DataBase.University.University>myAdapter=
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, university);
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(myAdapter);*/

        ArrayAdapter<String>myAdapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.university_name));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(myAdapter);

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

                }

                if (lName.getText().toString().trim().isEmpty()) {
                    Snackbar snackbar = Snackbar.make(v, "Please fill out these fields", Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    snackbarView.setBackgroundColor(getResources().getColor(R.color.red));
                    snackbar.show();
                    lName.setError("Last Name should not be empty");

                }

                if (password.getText().toString().trim().isEmpty()) {
                    Snackbar snackbar = Snackbar.make(v, "Please fill out these fields", Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    snackbarView.setBackgroundColor(getResources().getColor(R.color.red));
                    snackbar.show();
                    password.setError("Password should not be empty");

                }

                if (conPassword.getText().toString().trim().isEmpty()) {
                    Snackbar snackbar = Snackbar.make(v, "Please fill out these fields", Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    snackbarView.setBackgroundColor(getResources().getColor(R.color.red));
                    snackbar.show();
                    conPassword.setError("Confirm Password should not be empty");

                }

                if (!password.getText().toString().equals(conPassword.getText().toString())) {
                    Snackbar snackbar = Snackbar.make(v, "Passwords do not match", Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    snackbarView.setBackgroundColor(getResources().getColor(R.color.red));
                    snackbar.show();
                    password.setError("Passwords do not match");
                    conPassword.setError("Passwords do not match");

                }

                if (zID.getText().toString().trim().isEmpty()) {
                    Snackbar snackbar = Snackbar.make(v, "Please fill out these fields", Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    snackbarView.setBackgroundColor(getResources().getColor(R.color.red));
                    snackbar.show();
                    zID.setError("zID should not be empty");

                }

                if (!zID.getText().toString().trim().isEmpty()) {
                    for (int i = 0; i < Students.getStudents().size(); i++) {
                        if (Students.getStudents().get(i).getzID().equals(String.valueOf(zID))) {
                            Log.d(TAG, "ID has been taken");
                            Snackbar snackbar = Snackbar.make(v, "Please fill out these fields", Snackbar.LENGTH_LONG);
                            View snackbarView = snackbar.getView();
                            snackbarView.setBackgroundColor(getResources().getColor(R.color.red));
                            snackbar.show();
                            zID.setError("zID has been taken");

                        }
                    }
                }

                if (email.getText().toString().trim().isEmpty()) {
                    Snackbar snackbar = Snackbar.make(v, "Please fill out these fields", Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    snackbarView.setBackgroundColor(getResources().getColor(R.color.red));
                    snackbar.show();
                    email.setError("Email should not be empty");
                }

                if (!email.getText().toString().trim().isEmpty()) {
                    for (int j = 0; j < Students.getStudents().size(); j++) {
                        if (Students.getStudents().get(j).getEmail().equals(String.valueOf(email))) {
                            Log.d(TAG, "Email has been taken");
                            Snackbar snackbar = Snackbar.make(v, "Please fill out these fields", Snackbar.LENGTH_LONG);
                            View snackbarView = snackbar.getView();
                            snackbarView.setBackgroundColor(getResources().getColor(R.color.red));
                            snackbar.show();
                            email.setError("Email has been taken");
                        } else {
                            Log.d(TAG, "Passed validation");
                        }
                    }

                }

                if (spinner.getSelectedItem().toString().trim().isEmpty()) {
                    Snackbar snackbar = Snackbar.make(v, "Please fill out these fields", Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    snackbarView.setBackgroundColor(getResources().getColor(R.color.red));
                    snackbar.show();

                }

                if (!spinner.getSelectedItem().toString().trim().isEmpty()) {
                    for (int j = 0; j < University.getUniversities().size(); j++) {
                        if (University.getUniversities().get(j).getName().equals(String.valueOf(spinner.getSelectedItem()))) {
                            Log.d(TAG, "University is in the Arraylist");

                        } else {
                            Snackbar snackbar = Snackbar.make(v, "Please fill out these fields", Snackbar.LENGTH_LONG);
                            View snackbarView = snackbar.getView();
                            snackbarView.setBackgroundColor(getResources().getColor(R.color.red));
                            snackbar.show();
                            Log.d(TAG, "University is not in the Arraylist");
                        }
                    }

                }

                if (stDate.getText().toString().trim().isEmpty()) {
                    Snackbar snackbar = Snackbar.make(v, "Please fill out these fields", Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    snackbarView.setBackgroundColor(getResources().getColor(R.color.red));
                    snackbar.show();
                    stDate.setError("Start Date should not be empty");

                } else if /*(dateValidator.validate(stDate.getText().toString()) == false ) {
                    Snackbar snackbar = Snackbar.make(v, "Please fill out these fields", Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    snackbarView.setBackgroundColor(getResources().getColor(R.color.red));
                    snackbar.show();
                    stDate.setError("Invalid Start Date");

                }*/

                (checkDateFormat(stDate.getText().toString()) == false ) {
                    Snackbar snackbar = Snackbar.make(v, "Please fill out these fields", Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    snackbarView.setBackgroundColor(getResources().getColor(R.color.red));
                    snackbar.show();
                    stDate.setError("Invalid Start Date");
                }

                if (wkIncome.getText().toString().trim().isEmpty()) {
                    Snackbar snackbar = Snackbar.make(v, "Please fill out these fields", Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    snackbarView.setBackgroundColor(getResources().getColor(R.color.red));
                    snackbar.show();
                    wkIncome.setError("Weekly Income should not be empty");

                } else {
                    Student student = new Student(
                            fName.getText().toString(),
                            lName.getText().toString(),
                            password.getText().toString(),
                            zID.getText().toString(),
                            email.getText().toString(),
                            spinner.getSelectedItem().toString(),
                            stDate.getText().toString(),
                            Float.parseFloat(String.valueOf(wkIncome.getText()))
                    );

                    mDb.studentDao().insert(student);
                    mDb.studentDao().insertAll(student);

                    System.out.println(String.format("LOGIN DETAILS PASSED " +
                                    "\nUsername: %s " +
                                    "\nPassword: %s",
                            zID.getText(),
                            password.getText(),
                            conPassword.getText()));

                    openLoginActivity();
                    Toast.makeText(SignUp.this, "Student saved", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }


    private void openLoginActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("Username", zID.getText().toString());
        startActivity(intent);


    }

    /*public class getAllUniversity extends AsyncTask<Void, Void, List<com.example.budgetexchange.DataBase.University.University>> {

        @Override
        protected List<com.example.budgetexchange.DataBase.University.University> doInBackground(Void... voids) {
            mDb.universityDao().allUniversity();
            return mDb.universityDao().allUniversity();
        }

        @Override
        protected void onPostExecute(List<com.example.budgetexchange.DataBase.University.University> university) {
            spinner.setAdapter((SpinnerAdapter) mDb);
        }
    }*/

    public Boolean checkDateFormat(String date){
        if (date == null || !date.matches("^(1[0-9]|0[1-9]|3[0-1]|2[1-9])/(0[1-9]|1[0-2])/[0-9]{4}$"))
            return false;
        SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");
        try {
            format.parse(date);
            return true;
        }catch (ParseException e){
            return false;
        }
    }
}
