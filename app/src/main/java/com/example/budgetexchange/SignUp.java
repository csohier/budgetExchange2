package com.example.budgetexchange;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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
        insertStudent = (Button) findViewById(R.id.insertStudent);

        //Setting up spinner for universities
        ArrayAdapter<String>myAdapter= new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.university_name));

        //Spinner uses the adapter here
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(myAdapter);
        clickSignUp();
    }

    //Need to make sure they enter all the necessary fields
    //Need to make sure password and confirmed password are the same
    private void clickSignUp() {
        insertStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //check contents
                System.out.println("ZID");
                System.out.println("FIRST NAME : ");
                System.out.println("LAST NAME: " + String.valueOf(lName.getText()));
                System.out.println("PASSWORD ");
                System.out.println("EMAIL ");
                System.out.println("ZID DOES NOT EXIST");
                System.out.println("ZID DOES NOT EXIST");
                System.out.println("ZID DOES NOT EXIST");

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

    //Method triggered when parts of the SignUp is incomplete, such as incorrect format or empty
    public void incompleteSignUp(View v) {
        //Display Error Message
        Snackbar snackbar = Snackbar.make(v, "Please fill out all parts of the page", Snackbar.LENGTH_LONG);
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(getResources().getColor(R.color.red));
        snackbar.show();
    }

    //Method triggered if there is an existing Student using the same zID
    public void zIDTaken() {
        //Display Error Message
        Toast.makeText(SignUp.this, "Sorry! User has already been registered", Toast.LENGTH_SHORT).show();
        zID.setError("Sorry! User has already been registered");
    }

    //Method triggered when the "password" and "Confirm Password" do not match
    public void pwNotMatch() {
        //Display Error Message
        Toast.makeText(SignUp.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
        password.setError("Passwords do not match");
        conPassword.setError("Passwords do not match");
    }

    //Method triggered when the date filled in is in the incorrect format
    public void invalidStDate() {
        //Display Error Message
        Toast.makeText(SignUp.this, "Invalid Start Date", Toast.LENGTH_SHORT).show();
        stDate.setError("Invalid Start Date");
    }

    //Method triggered if all fields of the Sign Up is completed properly
    public void completeSignUp() {
        //Displays the success message
        Toast.makeText(SignUp.this, "SignUp Completed!", Toast.LENGTH_SHORT).show();
    }

    //Method triggered to detect any empty fields in Sign Up Activity
    public boolean checkFieldsEmpty(EditText editText) {
        return TextUtils.isEmpty(editText.getText());
    }

    //Opens the Login Activity for the Student
    private void openLoginActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("Username", zID.getText().toString());
        startActivity(intent);
    }

    //This method checks whether the Dates are in the correct format
    public Boolean checkDateFormat(String date){
        if (date == null ||
                !date.matches("^(1[0-9]|0[1-9]|3[0-1]|2[1-9])/(0[1-9]|1[0-2])/[0-9]{4}$"))
            return false;
        SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");

        try {format.parse(date); return true;}
        catch (ParseException e){ return false;}
    }

    @Override
    public void handleInsertStudentResult(String result) {
        //This is executed when the Student has been successfully added to the Student database
        completeSignUp();
        openLoginActivity();
    }

    @Override
    public void handleGetZIDResult(List<String> zIDs) {

        this.unavailableZIDs = zIDs;
        //If everything is filled in, check if zID is not taken
        if (unavailableZIDs.contains(zID.getText().toString())) { zIDTaken(); }

        else if (!checkDateFormat(stDate.getText().toString())) { invalidStDate();}

        else {
            //If zID is fine, check if both passwords are correct
            if (password.getText().toString().equals(conPassword.getText().toString())) {
                AppDatabase db = AppDatabase.getInstance(SignUp.this);
                //add this student to database
                InsertStudentAsyncTask insertStudentAsyncTask = new InsertStudentAsyncTask();
                insertStudentAsyncTask.setDatabase(db);
                insertStudentAsyncTask.setDelegate(this);
                insertStudentAsyncTask.execute(
                    new Student(
                            zID.getText().toString(),
                            fName.getText().toString(),
                            lName.getText().toString(),
                            password.getText().toString(),
                            email.getText().toString(),
                            spinner.getSelectedItem().toString(),
                            stDate.getText().toString(),
                            Float.parseFloat(String.valueOf(wkIncome.getText()
                            )
                        )
                    )
                );
            //Once this is done, handle insertStudentResult will now be triggered
            } else {
                //Display toast
                pwNotMatch();
                System.out.println("hello hello");

                System.out.println("this is the username: "
                        + zID.getText().toString());

                System.out.println("this is the passwords"
                        + conPassword.getText().toString()
                        + " " + password.getText().toString()
                );
            }
        }
    }

    @Override
    public void handleGetStudentByZID(Student zID) { }
    @Override
    public void handleUpdateStudentByZID(String result) { }
    @Override
    public void handleGetAllStudentsResult(List<Student> student) { }
}
