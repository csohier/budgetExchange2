package com.example.budgetexchange.Social;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.budgetexchange.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class SocialFeedAdapter extends RecyclerView.Adapter<SocialFeedAdapter.MyViewHolder> {
    private RecyclerViewClickListener mListener;
    private  ArrayList<SocialFeed> socialFeed = SocialFeed.socialFeed;

    public SocialFeedAdapter(ArrayList<SocialFeed> myDataset, RecyclerViewClickListener listener) {
        socialFeed = myDataset;
        this.mListener = listener;
    }

    public interface RecyclerViewClickListener {
        void onClick(View view, int position);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView firstName, lastName, content, date, title;
        private RecyclerViewClickListener mListener;

        public MyViewHolder(View v,RecyclerViewClickListener listener) {
            super(v);
            mListener = listener;
            v.setOnClickListener(this);
            firstName = v.findViewById(R.id.firstName);
            lastName = v.findViewById(R.id.surName);
            content = v.findViewById(R.id.feedText);
            date = v.findViewById(R.id.date);
            title = v.findViewById(R.id.feedName);
        }

        @Override
        public void onClick(View view){
            mListener.onClick(view,getAdapterPosition());
        }
    }

    @NonNull
    @Override
    public SocialFeedAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list, parent, false);
        MyViewHolder SocialFeedViewHolder = new MyViewHolder(view, mListener);
        return SocialFeedViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        SocialFeed currentFeed = SocialFeed.socialFeed.get(position);
        holder.title.setText(currentFeed.getTitle());
        holder.firstName.setText(currentFeed.getFirstName());
        holder.lastName.setText(currentFeed.getLastName());
        holder.date.setText(currentFeed.getPostDate());
        holder.content.setText(currentFeed.getContent());

    }

    @Override
    public int getItemCount() {
        return SocialFeed.socialFeed.size();
    }

    public static String format (GregorianCalendar calendar) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
        dateFormat.setCalendar(calendar);
        return dateFormat.format(calendar.getTime());
    }



}
