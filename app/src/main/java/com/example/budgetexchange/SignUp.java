package com.example.budgetexchange;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.budgetexchange.DataBase.AppDatabase;
import com.example.budgetexchange.DataBase.Student.AsyncTaskStudentDelegate;
import com.example.budgetexchange.DataBase.Student.GetAllZIDAsyncTask;
import com.example.budgetexchange.DataBase.Student.InsertStudentAsyncTask;
import com.example.budgetexchange.DataBase.Student.Student;
import com.google.android.material.snackbar.Snackbar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class SignUp extends AppCompatActivity implements AsyncTaskStudentDelegate {
    EditText fName, lName, password, conPassword, zID, email, stDate, wkIncome;
    Spinner spinner;
    Button insertStudent;
    private AppDatabase mDb;
    private List<String> unavailableZIDs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        fName = (EditText) findViewById(R.id.fName);
        lName = (EditText) findViewById(R.id.lName);
        password = (EditText) findViewById(R.id.password);
        conPassword = (EditText) findViewById(R.id.conPassword);
        zID = (EditText) findViewById(R.id.zID);
        email = (EditText) findViewById(R.id.email);
        stDate = (EditText) findViewById(R.id.stDate);
        wkIncome = (EditText) findViewById(R.id.wkIncome);
        spinner = (Spinner) findViewById(R.id.university);

        /*List<com.example.budgetexchange.DataBase.University.University> university =
                UniversityDB.getInstance(this).universityDao().allUniversity();

        //Adapter for holding the data view
        ArrayAdapter<com.example.budgetexchange.DataBase.University.University>myAdapter=
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, university);
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(myAdapter);*/

        //Setting up spinner for universities
        ArrayAdapter<String>myAdapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.university_name));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(myAdapter);

        insertStudent = (Button) findViewById(R.id.insertStudent);

        clickSignUp();

    }

    //Need to make sure they enter all the necessary fields
    //Need to make sure password and confirmed password are the same

    private void clickSignUp() {
        insertStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Checks if fields are empty

                if (checkFieldsEmpty(fName) || checkFieldsEmpty(lName) ||
                    checkFieldsEmpty(password) || checkFieldsEmpty(conPassword) ||
                    checkFieldsEmpty(zID) || checkFieldsEmpty(email) ||
                    checkFieldsEmpty(stDate) || checkFieldsEmpty(wkIncome) ||
                    spinner.getSelectedItem().equals(" ")) {

                    //Display error message
                    incompleteSignUp(v);

                } else {

                    AppDatabase db = AppDatabase.getInstance(SignUp.this);

                    //Grab all the usernames to check if the same username doesn't already exist
                    GetAllZIDAsyncTask getAllZIDAsyncTask = new GetAllZIDAsyncTask();
                    getAllZIDAsyncTask.setDatabase(db);
                    getAllZIDAsyncTask.setDelegate(SignUp.this);
                    getAllZIDAsyncTask.execute();
                }
            }
        });
    }

    public void incompleteSignUp(View v) {
        Snackbar snackbar = Snackbar.make(v, "Please fill out all parts of the page", Snackbar.LENGTH_LONG);
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(getResources().getColor(R.color.red));
        snackbar.show();
    }

    public void zIDTaken() {
        Toast.makeText(SignUp.this, "Sorry! User has already been registered", Toast.LENGTH_SHORT).show();
        zID.setError("Sorry! User has already been registered");
    }

    public void pwNotMatch() {
        Toast.makeText(SignUp.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
        password.setError("Passwords do not match");
        conPassword.setError("Passwords do not match");
    }

    public void invalidStDate() {
        Toast.makeText(SignUp.this, "Invalid Start Date", Toast.LENGTH_SHORT).show();
        stDate.setError("Invalid Start Date");
    }

    public void completeSignUp() {
        Toast.makeText(SignUp.this, "SignUp Completed!", Toast.LENGTH_SHORT).show();
    }

    public boolean checkFieldsEmpty(EditText editText) {
        return TextUtils.isEmpty(editText.getText());
    }

    private void openLoginActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("Username", zID.getText().toString());
        startActivity(intent);


    }

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

    @Override
    public void handleInsertStudentResult(String result) {
        //This is executed when the Student has been successfully added to the Student datbase
        completeSignUp();
        openLoginActivity();
    }

    @Override
    public void handleGetStudentResult(Student student) {

    }

    @Override
    public void handleGetAllStudentsResult(List<Student> student) {

    }

    @Override
    public void handleGetZIDResult(List<String> zIDs) {

        this.unavailableZIDs = zIDs;
        //If everything is filled in, check if zID is not taken
        if (unavailableZIDs.contains(zID.getText().toString())) {
            zIDTaken();

        } else if (!checkDateFormat(stDate.getText().toString())) {
            invalidStDate();

        } else {

            //If zID is fine, check if both passwords are correct
            if (password.getText().toString().equals(conPassword.getText().toString())) {

                AppDatabase db = AppDatabase.getInstance(SignUp.this);

                //add this student to database
                InsertStudentAsyncTask insertStudentAsyncTask = new InsertStudentAsyncTask();
                insertStudentAsyncTask.setDatabase(db);
                insertStudentAsyncTask.setDelegate(this);
                insertStudentAsyncTask.execute(

                    new Student(
                        fName.getText().toString(),
                        lName.getText().toString(),
                        password.getText().toString(),
                        zID.getText().toString(),
                        email.getText().toString(),
                        spinner.getSelectedItem().toString(),
                        stDate.getText().toString(),
                        Float.parseFloat(String.valueOf(wkIncome.getText()))
                    )
                );

            //Once this is done, handle insertStudentResult will now be triggered

            } else {

                //Display toast
                pwNotMatch();
                System.out.println("hello hello");
                System.out.println("this is the username: " + zID.getText().toString());

                System.out.println("this is the passwords" + conPassword.getText().toString() + " " + password.getText().toString());
            }

        }
    }

    @Override
    public void handleGetStudentByZID(Student zID) {

    }

    @Override
    public void handleUpdateStudentByZID(String result) { }

    //Setup Mastery methods
}
