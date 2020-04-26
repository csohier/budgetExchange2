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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.budgetexchange.R;
import com.example.budgetexchange.Students;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;



public class SocialFeedActivity extends AppCompatActivity implements  Button.OnClickListener {
    private RecyclerView recyclerView;
    private SocialFeedAdapter mAdapter;
    private Button postBtn;
    private EditText subEditName;
    private EditText subEditText;
    private Students user;
    private Calendar cal;
    private Spinner spinner;
    private Button filterBtn;

    public static final String EXTRA_MESSAGE = "position of clicked item";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_feed);

        //Setting up recyclerView:
        //ArrayList<SocialFeed> SocialFeedArrayList =
        recyclerView = (RecyclerView) findViewById(R.id.rView);
        recyclerView.setHasFixedSize(true);
        cal = Calendar.getInstance();
        postBtn = (Button) findViewById(R.id.createPost);
        postBtn.setOnClickListener(this);
        filterBtn = findViewById(R.id.filterBtn);

        spinner = (Spinner)findViewById(R.id.categorySpinner);
        ArrayAdapter<String> myAdapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.budget_category));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(myAdapter);

        filterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //iterate through socialFeed array list to only return values that are category = budgeting tips or cheap eats
                String filterText = spinner.getSelectedItem().toString();

            }
        });

        SocialFeedAdapter.RecyclerViewClickListener listener = new SocialFeedAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                onNoteClick(position);
            }
        };

        initialiseFilter(listener);

        //mAdapter = new SocialFeedAdapter(SocialFeed.socialFeed, listener);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //recyclerView.setAdapter(mAdapter);

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
                post.setPostDate(format(cal));

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

    public static String format (Calendar calendar) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String date = sdf.format(calendar.getTime());
        return date;
    }

    private void initialiseFilter(SocialFeedAdapter.RecyclerViewClickListener listener){
        String[] categories = {"All","Cheap Eats", "Budgeting Tips"};
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long itemID) {
                if (position >= 0 && position < categories.length) {
                    String text = spinner.getSelectedItem().toString();
                    Toast.makeText(SocialFeedActivity.this, text, Toast.LENGTH_SHORT).show();

                    getCategories(text,listener);

                } else {
                    Toast.makeText(SocialFeedActivity.this, "Selected Category Does not Exist!", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void getCategories(String category, SocialFeedAdapter.RecyclerViewClickListener listener){
        ArrayList<SocialFeed> socialPosts = new ArrayList<>();
        if(category.equals(null))
        {
            mAdapter = new SocialFeedAdapter(SocialFeed.socialFeed, listener);
        }else{
            //filter by id
            for (SocialFeed post : SocialFeed.socialFeed) {
                if (post.getType().equals(category)) {
                    socialPosts.add(post);
                }
            }
            //instatiate adapter a
            mAdapter = new SocialFeedAdapter(socialPosts,listener);
        }
        //set the adapter to GridView
        recyclerView.setAdapter(mAdapter);
    }
}
