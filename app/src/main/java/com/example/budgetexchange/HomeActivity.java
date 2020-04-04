package com.example.budgetexchange;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class HomeActivity extends AppCompatActivity {
    private ImageButton expBtn, svgBtn, sclBtn,prfBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        expBtn = findViewById(R.id.imageButton4);
        svgBtn = findViewById(R.id.imageButton5);
        sclBtn = findViewById(R.id.imageButton7);
        prfBtn = findViewById(R.id.imageButton6);

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



    }

        private void openProfileActivity() {
            Intent intent = new Intent (this, ProfileActivity.class);
            startActivity(intent);
        }
        private void openExpenseActivity() {
            Intent intent = new Intent (this, ExpenseFeed.class);
            startActivity(intent);
        }
        private void openSavingActivity() {
        // implementation to be completed
        Intent intent = new Intent (this, HomeActivity.class);
            startActivity(intent);
        }
        private void openSocialActivity() {
            Intent intent = new Intent (this, SocialFeedActivity.class);
            startActivity(intent);
        }
}
