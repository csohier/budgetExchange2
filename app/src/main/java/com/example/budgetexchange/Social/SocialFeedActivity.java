package com.example.budgetexchange.Social;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.budgetexchange.R;
import com.example.budgetexchange.Reddit.DataDetail;
import com.example.budgetexchange.RedditService;
import com.example.budgetexchange.Students;

import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SocialFeedActivity extends AppCompatActivity implements  Button.OnClickListener {
    private RecyclerView recyclerView;
    private SocialFeedAdapter mAdapter;
    private Button postBtn;
    private EditText subEditName;
    private EditText subEditText;
    private Students user;
    private Calendar cal;

    public static final String EXTRA_MESSAGE = "position of clicked item";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_feed);
        //Setting up recyclerView:
        //ArrayList<SocialFeed> SocialFeedArrayList =
        SocialFeed.getSocialFeed();
        recyclerView = (RecyclerView) findViewById(R.id.rView);
        recyclerView.setHasFixedSize(true);
        cal = Calendar.getInstance();
        postBtn = (Button) findViewById(R.id.createPost);
        postBtn.setOnClickListener(this);


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

        postBtn = findViewById(R.id.createPost);

    }

    //Passing position of clicked itemView to the DetailActivtiy through an intent:
    public void onNoteClick(int position) {
        Intent intent = new Intent(this, SocialFeedDetailActivity.class);
        intent.putExtra(EXTRA_MESSAGE, position);
        startActivity(intent);
    }

    private void openDialog(){
        LayoutInflater inflater = LayoutInflater.from(SocialFeedActivity.this);
        View subView = inflater.inflate(R.layout.dialog_social, null);
        subEditText = (EditText)subView.findViewById(R.id.dialogEditText);
        subEditName = (EditText)subView.findViewById(R.id.dialogNameText);



        /*final ImageView subImageView = (ImageView)subView.findViewById(R.id.image);
        Drawable drawable = getResources().getDrawable(R.mipmap.ic_launcher);
        subImageView.setImageDrawable(drawable); */

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Social Feed");
        builder.setMessage("New Feed");
        builder.setView(subView);

        //Build the AlertDialog.
        AlertDialog alertDialog = builder.create();

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SocialFeed post = new SocialFeed();
                user = Students.searchStudents(Students.currUser);

                post.setContent(subEditText.getText().toString());
                post.setFirstName(user.getfName());
                post.setLastName(user.getlName());
                post.setTitle(subEditName.getText().toString());
                post.setzID(user.getzID());
                post.setPostDate(cal.toString());

                //Add data to the list
                SocialFeed.socialFeed.add(post);
                //Notify the Adapter so that you can see the changes.
                mAdapter.notifyDataSetChanged();
                //Scroll the RecyclerView to the bottom.
                recyclerView.smoothScrollToPosition(mAdapter.getItemCount());

            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //This will close the Dialog
            }
        });

        builder.show();
    }

    @Override
    //Method for when you click on the post feed button
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.createPost:
                openDialog();
        }
    }
}