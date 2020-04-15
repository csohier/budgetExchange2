package com.example.budgetexchange;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class SocialFeedActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private SocialFeedAdapter mAdapter;
    public static final String EXTRA_MESSAGE = "position of clicked item";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_feed);
//        Setting up recyclerView:
        //ArrayList<SocialFeed> SocialFeedArrayList =
        SocialFeed.getSocialFeed();
        recyclerView = (RecyclerView) findViewById(R.id.rView);
        recyclerView.setHasFixedSize(true);

        SocialFeedAdapter.RecyclerViewClickListener listener = new SocialFeedAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                onNoteClick(position);
            }
        };

        mAdapter = new SocialFeedAdapter(SocialFeed.socialFeed, listener);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mAdapter);
    }

    //Passing position of clicked itemView to the DetailActivtiy through an intent:
    public void onNoteClick(int position) {
        Intent intent = new Intent(this, SocialFeedDetailActivity.class);
        intent.putExtra(EXTRA_MESSAGE, position);
        startActivity(intent);
    }
}