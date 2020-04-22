package com.example.budgetexchange;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.ScrollView;

import com.example.budgetexchange.Expenses.Expense;
import com.example.budgetexchange.Expenses.ExpenseAdapter;

import java.util.ArrayList;

public class Mastery extends AppCompatActivity {
    private ScrollView wideView;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private ArrayList<Achievements> achievementsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mastery);
        wideView = findViewById(R.id.wideView);

        achievementsList = new ArrayList<>();

        for(int i=0; i<Achievements.achievementsList.size();i++){
            if(Achievements.achievementsList.get(i).getPercentTrigger()>=percentageSaved()){
                achievementsList.add(Achievements.achievementsList.get(i));
                if(achievementsList.get(i).getImageResource()==0){
                    int percent = achievementsList.get(i).getPercentTrigger();
                    System.out.println("percentage CHECK" + percent);
                    achievementsList.get(i).setImageResource(imageSetterMethod(percent));
                }
            }
        }
        loadFragment();

        recyclerView = (RecyclerView)findViewById(R.id.rvList);
        recyclerView.setHasFixedSize(true);

        AchievementsAdapter.RecyclerViewClickListener listener = new AchievementsAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
            }
        };



        mAdapter = new AchievementsAdapter(achievementsList,listener);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));







    }


    private void loadFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment fragment = new ProgressFragment();
        transaction.replace(R.id.wideView,fragment);
        transaction.commit(); // save the changes


    }

    public int imageSetterMethod(int masteryID){
        int res1 = getResources().getIdentifier("mas_" + masteryID, "drawable", getPackageName());
        return res1;
    }

    private int percentageSaved(){
        Goal user = Students.searchGoals(Students.currUser);
        //user.getToSave();
        int percentage = (user.getTotalSaved()/user.getGoal())*100;
        return percentage;
    }

}
