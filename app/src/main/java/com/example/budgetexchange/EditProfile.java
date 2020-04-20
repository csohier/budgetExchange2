package com.example.budgetexchange;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class EditProfile extends AppCompatActivity {
    private DateValidator dateValidator;
    private static final String TAG = "EditProfile Activity";
    private EditText fName,lName, zID, email,university, discipline,startDate,weeklyIncome, goalAmount, goalStart, goalEnd;
    private Students user;
    private Button saveBtn;
    private Goal goal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        fName = findViewById(R.id.fNameE);
        lName = findViewById(R.id.lNameE);
        zID = findViewById(R.id.zIDE);
        email = findViewById(R.id.emailE);
        university = findViewById(R.id.universityE);
        discipline = findViewById(R.id.disciplineE);
        startDate = findViewById(R.id.startDateE);
        weeklyIncome = findViewById(R.id.wkIncomeE);
        saveBtn = findViewById(R.id.tvSave);
        goalAmount = findViewById(R.id.goalsAmountE);
        goalStart = findViewById(R.id.goalStartE);
        goalEnd = findViewById(R.id.goalEndE);

        System.out.println(Students.currUser);
        System.out.println(Students.searchStudents(Students.currUser));

        user = Students.searchStudents(Students.currUser);
        System.out.print(user.toString());
        System.out.print(user.getfName());

        fName.setText(user.getfName());
        lName.setText(user.getlName());
        zID.setText(user.getzID());
        email.setText(user.getEmail());
        university.setText(user.getUniversity());
        discipline.setText(user.getDiscipline());
        startDate.setText(user.getStDateString());
        weeklyIncome.setText("$" + String.valueOf(user.getWkIncome()));

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String i = extras.getString("indicator");
        String amt = extras.getString("amount");
        String start = extras.getString("startDate");
        String end = extras.getString("endDate");
        goalAmount.setHint(amt);
        goalStart.setHint(start);
        goalEnd.setHint(end);

        for(Goal a: Students.goals){
            if(a.getzID().equals(Students.currUser)){
                goal = user.searchGoals(user.getzID());
                goalAmount.setText(String.valueOf(goal.getGoal()));
                goalEnd.setText(goal.getGoalEndDate());
                goalStart.setText(goal.getGoalStartDate());
            }
        }

        saveBtn.setOnClickListener(new View.OnClickListener() {
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

                } else if (zID.getText().toString().trim().isEmpty()) {
                    Snackbar snackbar = Snackbar.make(v, "Please fill out these fields", Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    snackbarView.setBackgroundColor(getResources().getColor(R.color.red));
                    snackbar.show();
                    zID.setError("zID should not be empty");

                } else if  (email.getText().toString().trim().isEmpty()) {
                    Snackbar snackbar = Snackbar.make(v, "Please fill out these fields", Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    snackbarView.setBackgroundColor(getResources().getColor(R.color.red));
                    snackbar.show();
                    email.setError("Email should not be empty");

                } else if (discipline.getText().toString().trim().isEmpty()) {
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

                } else if (startDate.getText().toString().trim().isEmpty()) {
                    Snackbar snackbar = Snackbar.make(v, "Please fill out these fields", Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    snackbarView.setBackgroundColor(getResources().getColor(R.color.red));
                    snackbar.show();
                    startDate.setError("Start Date should not be empty");

                } else if (!dateValidator.validate(startDate.getText().toString())) {
                    Snackbar snackbar = Snackbar.make(v, "Invalid Start Date", Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    snackbarView.setBackgroundColor(getResources().getColor(R.color.red));
                    snackbar.show();
                    startDate.setError("Invalid Start Date");

                } else if (weeklyIncome.getText().toString().trim().isEmpty()) {
                    Snackbar snackbar = Snackbar.make(v, "Please fill out these fields", Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    snackbarView.setBackgroundColor(getResources().getColor(R.color.red));
                    snackbar.show();
                    weeklyIncome.setError("Weekly Income should not be empty");

                } else if (goalAmount.getText().toString().trim().isEmpty()){
                    Snackbar snackbar = Snackbar.make(v, "Please fill out these fields", Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    snackbarView.setBackgroundColor(getResources().getColor(R.color.red));
                    snackbar.show();
                    goalAmount.setError("Weekly Income should not be empty");

                } else if (goalStart.getText().toString().trim().isEmpty()) {
                    Snackbar snackbar = Snackbar.make(v, "Please fill out these fields", Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    snackbarView.setBackgroundColor(getResources().getColor(R.color.red));
                    snackbar.show();
                    goalStart.setError("Start Date should not be empty");

                } else if (!dateValidator.validate(goalStart.getText().toString())) {
                    Snackbar snackbar = Snackbar.make(v, "Invalid Goal Start Date", Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    snackbarView.setBackgroundColor(getResources().getColor(R.color.red));
                    snackbar.show();
                    goalStart.setError("Invalid Goal Start Date");

                }  else if (goalEnd.getText().toString().trim().isEmpty()) {
                    Snackbar snackbar = Snackbar.make(v, "Please fill out these fields", Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    snackbarView.setBackgroundColor(getResources().getColor(R.color.red));
                    snackbar.show();
                    goalEnd.setError("End Date should not be empty");

                } else if (!dateValidator.validate(goalEnd.getText().toString())) {
                    Snackbar snackbar = Snackbar.make(v, "Invalid Goal End Date", Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    snackbarView.setBackgroundColor(getResources().getColor(R.color.red));
                    snackbar.show();
                    goalEnd.setError("Invalid Goal End Date");

                } else if (Integer.parseInt(String.valueOf(goalStart)) > Integer.parseInt(String.valueOf(goalEnd))) {
                    Snackbar snackbar = Snackbar.make(v, "Goal End Date ends before Goal Start Date", Snackbar.LENGTH_LONG);
                    View snackbarView = snackbar.getView();
                    snackbarView.setBackgroundColor(getResources().getColor(R.color.red));
                    snackbar.show();
                    goalEnd.setError("Goal End Date ends before Goal Start Date");
                }

                else {
                    saveProfile();
                }
            }
        });
    }

    private void saveProfile() {
        Intent intent = new Intent (this, ProfileActivity.class);
        startActivity(intent);
    }
}
