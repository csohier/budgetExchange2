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

import java.util.ArrayList;
import java.util.List;

public class EditProfile extends AppCompatActivity {

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
            public void onClick(View view) {

                //not correct change this
                if (goalAmount.getText().equals(null) || goalStart.getText().equals(null) || goalEnd.getText().equals(null)) {
                    Toast.makeText(EditProfile.this, "Cannot save. Fields Empty, try again.", Toast.LENGTH_SHORT).show();
                } else {
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
