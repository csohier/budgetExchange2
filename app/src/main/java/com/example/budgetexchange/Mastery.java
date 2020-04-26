package com.example.budgetexchange;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
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
        //populating the percentage saved progress bar
        loadFragment();

        //iterates through the achievements list which contains 5 mastery levels
        //the iteration checks whether the user is eligible for a mastery based on the
        // percentage towards of their savings goal has been achieved
        //if a mastery percent trigger matches the percent saved, it will populate the
        //relevant mastery in a recycler view
        int total  = Goal.calculateTotalSaved(Students.currUser);
        for(int i=0; i<Achievements.achievementsList.size();i++){
            if(Achievements.achievementsList.get(i).getPercentTrigger()<=Goal.percentageSaved(total)){
                achievementsList.add(Achievements.achievementsList.get(i));
                if(achievementsList.get(i).getImageResource()==0){
                    int percent = achievementsList.get(i).getPercentTrigger();
                    System.out.println("percentage CHECK" + percent);
                    //set the image for the mastery
                    achievementsList.get(i).setImageResource(imageSetterMethod(percent));
                }
            }
        }



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
        transaction.commit();

    }

    public int imageSetterMethod(int masteryID){
        int res1 = getResources().getIdentifier("mas_" + masteryID, "drawable", getPackageName());
        return res1;
    }
}
