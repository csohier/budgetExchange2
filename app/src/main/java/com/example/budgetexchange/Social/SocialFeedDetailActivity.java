package com.example.budgetexchange.Social;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.budgetexchange.R;
import com.example.budgetexchange.Students;

import java.util.ArrayList;
import java.util.Calendar;

public class SocialFeedDetailActivity extends AppCompatActivity {
    private TextView title,post;
    private EditText commentEditText;
    private Button sendBtn;
    private RecyclerView recyclerView;
    private CommentsAdapter mAdapter;
    private Calendar cal;
    private int position;
    private ProgressBar progressBar;
    private ProgressBar cmtProgressBar;
    SocialFeed md;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_feed_detail);

        title = findViewById(R.id.titleText);
        post = findViewById(R.id.postText);
        progressBar = findViewById(R.id.progressBar);
        cmtProgressBar = findViewById(R.id.commentsProgressBar);
        commentEditText = findViewById(R.id.commentEditText);
        sendBtn = findViewById(R.id.sendBtnn);
        cal = Calendar.getInstance();

        Intent intent = getIntent();
        int position = intent.getIntExtra(SocialFeedActivity.EXTRA_MESSAGE, 0);
        md = SocialFeed.socialFeed.get(position);
        title.setText(md.getTitle());
        loadPostContent(md.getContent());


        recyclerView = (RecyclerView)findViewById(R.id.commentsRecyclerView);


        recyclerView.setHasFixedSize(true);

            CommentsAdapter.RecyclerViewClickListener listener = new CommentsAdapter.RecyclerViewClickListener() {
                @Override
                public void onClick(View view, int position) {

                }
            };

            mAdapter = new CommentsAdapter(Comments.commentsList, listener);
            commentsInput();

            recyclerView.setAdapter(mAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }


    public ArrayList<Comments> commentsInput(){
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!commentEditText.getText().toString().equals(null)) {
                    Comments comments = new Comments();
                    comments.setFirstName(Students.searchStudents(Students.currUser).getfName());
                    comments.setLastName(Students.searchStudents(Students.currUser).getlName());
                    comments.setzID(Students.currUser);
                    comments.setPostDate(SocialFeedActivity.format(cal));
                    comments.setContent(commentEditText.getText().toString());

                    //comments that are shown need to equal the postID

                    comments.setPostID(SocialFeed.searchPosts(position).getPostID());
                    Comments.commentsList.add(comments);
                    Toast.makeText(SocialFeedDetailActivity.this,"Added", Toast.LENGTH_SHORT).show();
                    mAdapter.addItem(comments,mAdapter.getItemCount());
                    //mAdapter.notifyItemInserted(Comments.commentsList.size()-1);
                    for(Comments a: Comments.commentsList){
                        System.out.println(a.getContent());

                    }
                }else{
                    Toast.makeText(SocialFeedDetailActivity.this,"Error", Toast.LENGTH_SHORT).show();

                }
            }
        });
        return Comments.commentsList;

    }

    public void loadPostContent(String content){
        post.setText(content);
        progressBar.setVisibility(View.GONE);
    }

    public void loadComments(boolean load){
        cmtProgressBar.setVisibility(load ? View.VISIBLE : View.GONE);
    }


}
