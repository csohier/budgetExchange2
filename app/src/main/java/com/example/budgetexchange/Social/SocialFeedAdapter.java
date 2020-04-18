package com.example.budgetexchange.Social;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.budgetexchange.R;

import java.util.ArrayList;

public class SocialFeedAdapter extends RecyclerView.Adapter<SocialFeedAdapter.MyViewHolder> {
    private RecyclerViewClickListener mListener;

    public SocialFeedAdapter(ArrayList<SocialFeed> myDataset, RecyclerViewClickListener listener) {
        SocialFeed.socialFeed = myDataset;
        this.mListener = listener;
    }

    public interface RecyclerViewClickListener {
        void onClick(View view, int position);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView text;
        private RecyclerViewClickListener mListener;


        public MyViewHolder(View v,RecyclerViewClickListener listener) {
            super(v);
            mListener = listener;
            v.setOnClickListener(this);
            text = v.findViewById(R.id.textView8);
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
        holder.text.setText(currentFeed.getTitle());
    }
    @Override
    public int getItemCount() {
        return SocialFeed.socialFeed.size();
    }


}
