package com.example.budgetexchange;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SocialFeedAdapter extends RecyclerView.Adapter<SocialFeedAdapter.SocialFeedViewHolder> {
   private ArrayList<SocialFeed> mDataset;
    private OnNoteListener mOnNoteListener;

    public SocialFeedAdapter(ArrayList<SocialFeed> myDataset, OnNoteListener onNoteListener) {
        this.mDataset = myDataset;
        this.mOnNoteListener = onNoteListener;
    }
    @NonNull
    @Override
    public SocialFeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list, parent, false);
        SocialFeedViewHolder SocialFeedViewHolder = new SocialFeedViewHolder(view, mOnNoteListener);
        return SocialFeedViewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull SocialFeedViewHolder holder, int position) {
        SocialFeed currentFeed = mDataset.get(position);
        holder.textView.setText(currentFeed.getTitle());
    }
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class SocialFeedViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView textView;
        OnNoteListener onNoteListener;

        public SocialFeedViewHolder(@NonNull View itemView, OnNoteListener onNoteListener) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
            itemView.setOnClickListener(this);
            this.onNoteListener = onNoteListener;
        }

        @Override
        public void onClick(View v) {
            onNoteListener.onNoteClick(getAdapterPosition());
        }
    }
    public interface OnNoteListener {
        void onNoteClick(int position);
    }
}
