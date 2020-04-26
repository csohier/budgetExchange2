package com.example.budgetexchange;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.budgetexchange.Expenses.ExpenseFeed;
import com.example.budgetexchange.Social.SocialFeedActivity;

public class HomeActivity extends AppCompatActivity {
    private static final String TAG = "Home Activity";

    private ImageView expBtn, svgBtn, sclBtn,prfBtn;
    private ImageView masteryBtn;
    private ImageView conversionBtn;
    private TextView zID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        masteryBtn = findViewById(R.id.masterBtn);
        zID = findViewById(R.id.zID);
        expBtn = findViewById(R.id.expenseBtn);
        svgBtn = findViewById(R.id.savingBtn);
        sclBtn = findViewById(R.id.socialBtn);
        prfBtn = findViewById(R.id.profileBtn);
        conversionBtn = findViewById(R.id.convertBtn);

        Intent intent = getIntent();
        zID.setText(intent.getStringExtra("Username"));

        //OnClick Listeners to allow Student to go to other activities
        expBtn.setOnClickListener(view -> openExpenseActivity());
        sclBtn.setOnClickListener(view -> openSocialActivity());
        prfBtn.setOnClickListener(view -> openProfileActivity());
        svgBtn.setOnClickListener(view -> openSavingActivity());
        conversionBtn.setOnClickListener(v -> openConversionActivity());
        masteryBtn.setOnClickListener(v -> openMasterActivity());
    }

    //Opens the Profile Activity for the Student
    private void openProfileActivity() {
        Intent intent = new Intent (this, ProfileActivity.class);
        Log.d(TAG, "open Profile Activity");
        startActivity(intent);
    }

    //Opens the Expense Activity for the Student
    private void openExpenseActivity() {
        Intent intent = new Intent (this, ExpenseFeed.class);
        Log.d(TAG, "open Expense Activity");
        startActivity(intent);
    }

    //Opens the Savings Activity for the Student
    private void openSavingActivity() {
    Intent intent = new Intent (this, Savings.class);
        Log.d(TAG, "open Saving Activity");
        startActivity(intent);
    }

    //Opens the Social Activity for the Student
    private void openSocialActivity() {
        Intent intent = new Intent (this, SocialFeedActivity.class);
        Log.d(TAG, "open Social Activity");
        startActivity(intent);
    }

    //Opens the Conversion API for the Student
    private void openConversionActivity() {
        Intent intent = new Intent (this, Conversion.class);
        Log.d(TAG, "open Conversion Activity");
        startActivity(intent);
    }

    //Opens the Mastery Activity for the Student
    private void openMasterActivity() {
        Intent intent = new Intent (this, Mastery.class);
        Log.d(TAG, "open Mastery Activity");
        startActivity(intent);
    }
}
