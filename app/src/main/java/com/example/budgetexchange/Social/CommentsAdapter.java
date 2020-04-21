package com.example.budgetexchange.Social;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.budgetexchange.R;


import java.util.ArrayList;


public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.MyViewHolder> {
    private RecyclerViewClickListener mListener;
    private ArrayList<Comments> commentsArrayList;

    public CommentsAdapter(ArrayList<Comments> myDataset, RecyclerViewClickListener listener) {
        commentsArrayList = myDataset;
        this.mListener = listener;
    }

    public interface RecyclerViewClickListener {
        void onClick(View view, int position);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView firstName, lastName, content, date;
        private RecyclerViewClickListener mListener;

        public MyViewHolder(View v,RecyclerViewClickListener listener) {
            super(v);
            mListener = listener;
            v.setOnClickListener(this);
            firstName = v.findViewById(R.id.firstNamec);
            lastName = v.findViewById(R.id.surNamec);
            content = v.findViewById(R.id.feedTextc);
            date = v.findViewById(R.id.datec);
        }

        @Override
        public void onClick(View view){
            mListener.onClick(view,getAdapterPosition());
        }


    }



    @NonNull
    @Override
    public CommentsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comments_list, parent, false);
        MyViewHolder CommentsViewHolder = new MyViewHolder(view, mListener);
        return CommentsViewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.firstName.setText(commentsArrayList.get(position).getFirstName());
        holder.lastName.setText(commentsArrayList.get(position).getLastName());
        holder.date.setText(commentsArrayList.get(position).getPostDate());
        holder.content.setText(commentsArrayList.get(position).getContent());

    }
    @Override
    public int getItemCount() {
        return SocialFeed.socialFeed.size();
    }

    public RecyclerViewClickListener getmListener() {
        return mListener;
    }

    public void setmListener(RecyclerViewClickListener mListener) {
        this.mListener = mListener;
    }

    public ArrayList<Comments> getCommentsArrayList() {
        return commentsArrayList;
    }

    public void setCommentsArrayList(ArrayList<Comments> commentsArrayList) {
        this.commentsArrayList = commentsArrayList;
    }

    void addItem(Comments dataObj, int index) {
        commentsArrayList.add(dataObj);
        notifyItemInserted(index);
    }
}
