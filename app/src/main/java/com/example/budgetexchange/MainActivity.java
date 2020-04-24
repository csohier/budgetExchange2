package com.example.budgetexchange;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputBinding;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.room.Room;

import com.example.budgetexchange.DataBase.Student.Student;
import com.example.budgetexchange.DataBase.Student.StudentDB;
import com.example.budgetexchange.DataBase.Student.StudentDao;
import com.example.budgetexchange.DataBase.Student.StudentRepository;
import com.example.budgetexchange.DataBase.University.UniversityDB;
import com.example.budgetexchange.Expenses.Expense;
import com.example.budgetexchange.Social.Comments;
import com.example.budgetexchange.Social.SocialFeed;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main Activity";
    private TextInputEditText userNameInput;
    private TextInputEditText passwordInput;
    private CheckBox rememberMe;
    private Button loginBtn;
    private Button signUpBtn;

    private StudentDao studentDao;
    private StudentDB studentDB;
    private StudentRepository studentRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userNameInput = findViewById(R.id.userNameInput);
        passwordInput = findViewById(R.id.passwordInput);
        loginBtn = findViewById(R.id.loginBtn);
        signUpBtn = findViewById(R.id.signUpBtn);
        rememberMe = findViewById(R.id.rememberMe);

        studentDB = Room.databaseBuilder(this, StudentDB.class, "student_database")
                .allowMainThreadQueries()
                .build();

        studentDao = studentDB.studentDao();


        //open register xml
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClickSignUp();
            }
        });

        ClickLogin();


    }

    //This is method for doing operation of check login
    private void ClickLogin() {

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (userNameInput.getText().toString().trim().isEmpty()) {

                    Snackbar snackbar = Snackbar.make(view, "Please fill out these fields", Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    snackbarView.setBackgroundColor(getResources().getColor(R.color.red));
                    snackbar.show();
                    userNameInput.setError("Username should not be empty");
                } else {
                    //Here you can write the codes for checking username


                }

                if (passwordInput.getText().toString().trim().isEmpty()) {
                    Snackbar snackbar = Snackbar.make(view, "Please fill out these fields", Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    snackbarView.setBackgroundColor(getResources().getColor(R.color.red));
                    snackbar.show();
                    passwordInput.setError("Password should not be empty");
                } else {
                    //Here you can write the codes for checking password
                }

                if (rememberMe.isChecked()) {
                    //Here you can write the codes if box is checked
                } else {
                    //Here you can write the codes if box is not checked
                }

                Log.d(TAG, userNameInput.getText().toString());
                Log.d(TAG, passwordInput.getText().toString());

                System.out.println(userNameInput.getText().toString());
                System.out.println(passwordInput.getText().toString());

                LiveData<Student> student = studentDao.getStudent(userNameInput.getText().toString(), passwordInput.getText().toString());

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


                /*  String username = String.valueOf(userNameInput.getText());
                    String password = String.valueOf(passwordInput.getText());

                    Students.currUser=username;
                    Students.goals = Goal.getGoals();
                    Expense.getExpenses();
                    Students.getStudents();
                    SocialFeed.getSocialFeed();
                    Comments.getComments();
                    Achievements.getAchievements();

                    for (int i = 0; i < Students.getStudents().size(); i++) {

                    if (Students.getStudents().get(i).getzID().equals(username) && Students.getStudents().get(i).getPassword().equals(password)) {
                        openHomeActivity();
                    } else{
                        Toast.makeText(MainActivity.this, "Failed to login", Toast.LENGTH_SHORT).show();
                    }
                }*/


            }

        });

    }

    private void ClickSignUp() {
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
    }

    private void openHomeActivity() {
        Intent intent = new Intent (this, HomeActivity.class);
        intent.putExtra("Username", userNameInput.getText().toString());
        startActivity(intent);
    }
}
