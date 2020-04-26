package com.example.budgetexchange;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

        //checks if a goal has been created by the user yet.
        //if not, the profile page will indicate that the values are empty
        String indicator, amount, start, end;
        if(user.goalExists(user.getzID())==true){
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

        //edit profile
        editBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            openEditProfileActivity(indicator,amount,start,end);
        }
        });
    }

    private void openEditProfileActivity(String indicator,String goalAmt, String goalSt, String glEnd) {
        Intent intent = new Intent(this, EditProfile.class);
        intent.putExtra("i",indicator);
        intent.putExtra("amount",goalAmt);
        intent.putExtra("startDate",goalSt);
        intent.putExtra("endDate",glEnd);
        startActivity(intent);

    }
}
