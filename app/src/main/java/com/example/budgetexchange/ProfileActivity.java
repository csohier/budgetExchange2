package com.example.budgetexchange;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    private TextView fName;
    private TextView lName;
    private TextView zID;
    private TextView email;
    private TextView university;
    private TextView discipline;
    private TextView startDate;
    private TextView weeklyIncome;
    private Students user;

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
        System.out.println(Students.currUser);
        System.out.println(Students.searchStudents(Students.currUser).toString());

        user = Students.searchStudents(Students.currUser);

        fName.setText(user.getfName());
        lName.setText(user.getlName());
        zID.setText(user.getzID());
        email.setText(user.getEmail());
        university.setText(user.getUniversity());
        discipline.setText(user.getDiscipline());
        startDate.setText(user.getStDateString());
        weeklyIncome.setText("$" + String.valueOf(user.getWkIncome()));


    }
}
