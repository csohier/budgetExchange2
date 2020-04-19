package com.example.budgetexchange;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.budgetexchange.Expenses.ExpenseFeed;
import com.example.budgetexchange.Social.SocialFeedActivity;

public class HomeActivity extends AppCompatActivity {
    private static final String TAG = "Home Activity";

    private ImageButton expBtn, svgBtn, sclBtn,prfBtn;
    private Button conversionBtn;
    private TextView zID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        zID = findViewById(R.id.zID);
        expBtn = findViewById(R.id.imageButton4);
        svgBtn = findViewById(R.id.imageButton5);
        sclBtn = findViewById(R.id.imageButton7);
        prfBtn = findViewById(R.id.imageButton6);
        conversionBtn = findViewById(R.id.conversionBtn);

        Intent i = getIntent();
        zID.setText(i.getStringExtra("Username"));
        expBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            openExpenseActivity();
        }
        });
        sclBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSocialActivity();
            }
        });
        prfBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openProfileActivity();
            }
        });
        svgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSavingActivity();
            }
        });
        conversionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openConversionActivity();
            }
        });



    }

    private void openProfileActivity() {
        Intent intent = new Intent (this, ProfileActivity.class);
        Log.d(TAG, "open Profile Activity");
        startActivity(intent);
    }
    private void openExpenseActivity() {
        Intent intent = new Intent (this, ExpenseFeed.class);
        Log.d(TAG, "open Expense Activity");
        startActivity(intent);
    }
    private void openSavingActivity() {
    Intent intent = new Intent (this, Savings.class);
        Log.d(TAG, "open Saving Activity");
        startActivity(intent);
    }
    private void openSocialActivity() {
        Intent intent = new Intent (this, SocialFeedActivity.class);
        Log.d(TAG, "open Social Activity");
        startActivity(intent);
    }

    private void openConversionActivity() {
        Intent intent = new Intent (this, Conversion.class);
        Log.d(TAG, "open Conversion Activity");
        startActivity(intent);
    }
}
