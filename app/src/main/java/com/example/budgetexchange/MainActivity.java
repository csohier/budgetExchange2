package com.example.budgetexchange;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText userNameInput;
    private TextInputEditText passwordInput;
    private CheckBox rememberMe;
    private Button loginBtn;
    private Button signUpBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userNameInput = findViewById(R.id.userNameInput);
        passwordInput = findViewById(R.id.passwordInput);
        loginBtn = findViewById(R.id.loginBtn);
        signUpBtn = findViewById(R.id.signUpBtn);
        rememberMe = findViewById(R.id.rememberMe);
        List<Students> students;



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

                String username = String.valueOf(userNameInput.getText());
                Students.currUser=username;
                Students.goals = Goal.getGoals();
                String password = String.valueOf(passwordInput.getText());

                for (int i = 0; i < Students.getStudents().size(); i++) {

                    if (Students.getStudents().get(i).getzID().equals(username) && Students.getStudents().get(i).getPassword().equals(password)) {
                        openHomeActivity();
                    } else{
                        // authentication failed
                    }
                }

            }

        });

    }

    private void ClickSignUp() {
        Intent intent = new Intent(this, regPgOne.class);
        startActivity(intent);
    }

    private void openHomeActivity() {
        Intent intent = new Intent (this, HomeActivity.class);
        startActivity(intent);
    }
}
