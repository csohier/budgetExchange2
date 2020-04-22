package com.example.budgetexchange;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.ScrollView;

public class Mastery extends AppCompatActivity {
    private ScrollView wideView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mastery);
        wideView = findViewById(R.id.wideView);

        loadFragment();

    }


    private void loadFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment fragment = new ProgressFragment();
        transaction.replace(R.id.wideView,fragment);
        transaction.commit(); // save the changes

        /*Bundle arguments = new Bundle();
        fragment.setArguments(arguments);
        transaction.replace(R.id.wideView, fragment);
        transaction.commit();*/

    }
}
