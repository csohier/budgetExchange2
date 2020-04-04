package com.example.budgetexchange;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SocialFeedDetailActivity extends AppCompatActivity {
    private SocialFeed mDataset;
    private TextView title;
    private TextView content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_feed_detail);
        title = findViewById(R.id.textView5);
        content = findViewById(R.id.textView6);

        Intent intent = getIntent();
        int position = intent.getIntExtra(SocialFeedActivity.EXTRA_MESSAGE, 0);
        SocialFeed mDataSet = SocialFeed.getSocialFeed().get(position);
        title.setText(mDataset.getTitle());
        content.setText(mDataset.getContent());
    }
}
