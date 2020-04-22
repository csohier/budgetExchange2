package com.example.budgetexchange;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.budgetexchange.Expenses.Expense;
import com.example.budgetexchange.Expenses.ExpenseAdapter;

import java.util.List;

public class AchievementsAdapter extends RecyclerView.Adapter<AchievementsAdapter.MyViewHolder>  {

    private static List<Achievements> models;
    private AchievementsAdapter.RecyclerViewClickListener mListener;


    public AchievementsAdapter(List<Achievements> models, AchievementsAdapter.RecyclerViewClickListener listener){
        mListener=listener;
        this.models = models;

    }

    public interface RecyclerViewClickListener {
        void onClick(View view, int position);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView description,name;
        private AchievementsAdapter.RecyclerViewClickListener mListener;

        public MyViewHolder(View v, AchievementsAdapter.RecyclerViewClickListener listener){
            super(v);
            mListener=listener;
            v.setOnClickListener(this);
            description = v.findViewById(R.id.masteryText);
            name = v.findViewById(R.id.masteryName);

        }

        @Override
        public void onClick(View view){
            mListener.onClick(view,getAdapterPosition());
        }
    }



    @Override
    public AchievementsAdapter.MyViewHolder onCreateViewHolder (ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.achievements_list,parent,false);
        AchievementsAdapter.MyViewHolder vh = new AchievementsAdapter.MyViewHolder(v,mListener);
        return vh;
    }

    @Override
    public void onBindViewHolder(AchievementsAdapter.MyViewHolder holder, int position) {

        Achievements achievements = models.get(position);
        holder.description.setText("Congratulations, you've made it " + String.valueOf(achievements.getPercentTrigger())+ "% closer to your goal!");
        holder.name.setText(achievements.getName());

    }

    @Override
    public int getItemCount(){
        return models.size();
    }

}

