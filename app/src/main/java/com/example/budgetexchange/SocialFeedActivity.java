package com.example.budgetexchange;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.budgetexchange.Reddit.DataDetail;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

        Retrofit.Builder builder = new Retrofit.Builder().baseUrl("https://www.reddit.com").addConverterFactory(GsonConverterFactory.create());
        Retrofit retrofit = builder.build();
        RedditService service = retrofit.create(RedditService.class);
        Call<List<DataDetail>> call = service.getFood();

        call.enqueue(new Callback<List<DataDetail>>() {
            @Override
            public void onResponse(Call<List<DataDetail>> call, Response<List<DataDetail>>  response) {
                if(!response.isSuccessful()){
                    System.out.println(" PRINT THIS CHECK : " + response.code());
                    return;
                }

                List<DataDetail> detail = response.body();
                for (DataDetail d: detail){
                    String content ="";
                    content += "CHECK" + d.getAuthorFullname();

                    System.out.println(content);
                }


            }

            @Override
            public void onFailure(Call<List<DataDetail>>  call, Throwable t) {

                Log.d("Main Activity", "Failed to get quote");

            }
        });

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