package com.example.budgetexchange;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.budgetexchange.DataBase.AppDatabase;
import com.example.budgetexchange.DataBase.Student.AsyncTaskStudentDelegate;
import com.example.budgetexchange.DataBase.Student.GetAllZIDAsyncTask;
import com.example.budgetexchange.DataBase.Student.GetStudentByZIDAsyncTask;
import com.example.budgetexchange.DataBase.Student.Student;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class MainActivity extends AppCompatActivity implements AsyncTaskStudentDelegate{

    private static final String TAG = "Main Activity";
    private TextInputEditText userNameInput;
    private TextInputEditText passwordInput;
    private Button loginBtn;
    private Button signUpBtn;
    private List <String> existingStudents;
    private Student currUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        userNameInput = findViewById(R.id.userNameInput);
        passwordInput = findViewById(R.id.passwordInput);
        loginBtn = findViewById(R.id.loginBtn);
        signUpBtn = findViewById(R.id.signUpBtn);

        //Clicking SignUp button
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClickSignUp();
            }
        });

        //Clicking Login button
        ClickLogin();
    }

    //This is method for doing operation of check login
    private void ClickLogin() {

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AppDatabase db = AppDatabase.getInstance(MainActivity.this);

                //Check if zID exists by getting all existing zIDs
                //Get all the zIDs to check whether the same zID exists
                GetAllZIDAsyncTask getAllZIDAsyncTask = new GetAllZIDAsyncTask();
                getAllZIDAsyncTask.setDatabase(db);
                getAllZIDAsyncTask.setDelegate(MainActivity.this);
                getAllZIDAsyncTask.execute();

                if (userNameInput.getText().toString().trim().isEmpty()) {
                    Snackbar snackbar = Snackbar.make(view, "Please fill out these fields", Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    snackbarView.setBackgroundColor(getResources().getColor(R.color.red));
                    snackbar.show();
                    userNameInput.setError("Username should not be empty");
                }

                if (passwordInput.getText().toString().trim().isEmpty()) {
                    Snackbar snackbar = Snackbar.make(view, "Please fill out these fields", Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    snackbarView.setBackgroundColor(getResources().getColor(R.color.red));
                    snackbar.show();
                    passwordInput.setError("Password should not be empty");
                }

                /*
                Log.d(TAG, userNameInput.getText().toString());
                Log.d(TAG, passwordInput.getText().toString());

                System.out.println(userNameInput.getText().toString());
                System.out.println(passwordInput.getText().toString());

                List<Student> student = studentDao.getStudent(userNameInput.getText().toString(), passwordInput.getText().toString());

                if (student != null) {
                    String username = String.valueOf(userNameInput.getText());
                    Students.currUser=username;
                    Students.goals = Goal.getGoals();
                    Expense.getExpenses();
                    Students.getStudents();
                    SocialFeed.getSocialFeed();
                    Comments.getComments();
                    Achievements.getAchievements();

                    openHomeActivity();

                } else {
                    Snackbar snackbar = Snackbar.make(view, "zID or Password incorrect", Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    snackbarView.setBackgroundColor(getResources().getColor(R.color.red));
                    snackbar.show();
                    userNameInput.setError("Check fields");
                    passwordInput.setError("Check fields");
                }

                 */
            }

        });
    }

    //Switch Activity to SignUp Activity
    private void ClickSignUp() {
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
    }

    @Override
    public void handleInsertStudentResult(String result) {

    }

    @Override
    public void handleGetStudentResult(Student student) {

    }

    @Override
    public void handleGetAllStudentsResult(List<Student> student) {

    }

    public void handleGetZIDResult(List<String> zID) {
        existingStudents = zID;
        if (existingStudents.contains(userNameInput.getText().toString())) {

            //Gets the student if it exists already
            AppDatabase db = AppDatabase.getInstance(MainActivity.this);

            //Gets the specific student object
            GetStudentByZIDAsyncTask getStudentByZIDAsyncTask = new GetStudentByZIDAsyncTask();
            getStudentByZIDAsyncTask.setDatabase(db);
            getStudentByZIDAsyncTask.setDelegate(MainActivity.this);
            getStudentByZIDAsyncTask.execute(userNameInput.getText().toString());

        } else {
            zIDNotExist();
        }
    }

    @Override
    public void handleGetStudentByZID (Student student) {
        currUser = student;
        //Now that we have a correct ZID, let's check if the password is correct

        //Check if password matches:
        if(currUser.getPassword().equals(passwordInput.getText().toString())) {
            //Switch pages
            openHomeActivity();

        } else {
            //display toast
            passwordIncorrect();
        };
    }

    public void zIDNotExist() {
        //Displays error messages
        Toast.makeText(MainActivity.this, "zID does not exist.", Toast.LENGTH_SHORT).show();
        userNameInput.setError("Check fields");
    }

    public void passwordIncorrect() {
        //Displays error messages
        Toast.makeText(MainActivity.this, "Password incorrect.", Toast.LENGTH_SHORT).show();
        passwordInput.setError("Check fields");
    }

    //Switch Activity to Home Activity
    private void openHomeActivity() {
        Intent intent = new Intent (this, HomeActivity.class);
        intent.putExtra("Username", userNameInput.getText().toString());
        startActivity(intent);
    }
}
