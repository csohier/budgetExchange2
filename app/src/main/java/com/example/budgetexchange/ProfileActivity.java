package com.example.budgetexchange;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    private TextView fName;
    private TextView lName;
    private TextView zID;
    private TextView email;
    private TextView university;
    private TextView discipline;
    private TextView startDate;
    private TextView weeklyIncome,goalAmount,goalStart,goalEnd;
    private Button editBtn;
    private Students user;
    private Goal goal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        fName = findViewById(R.id.fName);
        lName = findViewById(R.id.lName);
        zID = findViewById(R.id.zID);
        email = findViewById(R.id.email);
        university = findViewById(R.id.university);
        discipline = findViewById(R.id.discipline);
        startDate = findViewById(R.id.stDate);
        weeklyIncome = findViewById(R.id.wkIncome);
        goalAmount = findViewById(R.id.goalsAmount);
        goalEnd = findViewById(R.id.goalEnd);
        goalStart = findViewById(R.id.goalStart);
        user = Students.searchStudents(Students.currUser);

        String indicator, amount, start, end;

        //Checks whether the Student has a goal in the system
        if(user.goalExists(user.getzID())==true) {
            goal = user.searchGoals(user.getzID());
            amount = String.valueOf(goal.getGoal());
            goalAmount.setText("$" + amount);
            start = goal.getGoalStartDate();
            goalStart.setText(start);
            end = goal.getGoalEndDate();
            goalEnd.setText(end);
            indicator = "A";

        } else {
            amount = "$NULL";
            start = "dd/MM/yyyy";
            end = "dd/MM/yyyy";
            indicator = "B";
        }

        editBtn = findViewById(R.id.tvEdit);
        fName.setText(user.getfName());
        lName.setText(user.getlName());
        zID.setText(user.getzID());
        email.setText(user.getEmail());
        university.setText(user.getUniversity());
        discipline.setText(user.getDiscipline());
        startDate.setText(user.getStDateString());
        weeklyIncome.setText("$" + String.valueOf(user.getWkIncome()));

        //Button to open Edit Profile Activity
        editBtn.setOnClickListener(view -> openEditProfileActivity(indicator,amount,start,end));
    }

    //Switch Activity to Edit Profile Activity
    private void openEditProfileActivity(String indicator,String goalAmt, String goalSt, String glEnd) {
        Intent intent = new Intent(this, EditProfile.class);
        intent.putExtra("i",indicator);
        intent.putExtra("amount",goalAmt);
        intent.putExtra("startDate",goalSt);
        intent.putExtra("endDate",glEnd);
        startActivity(intent);
    }
}
