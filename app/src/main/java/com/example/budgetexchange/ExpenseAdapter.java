package com.example.budgetexchange;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.MyViewHolder>  {

    private static List<Expense> models;
    private RecyclerViewClickListener mListener;

    public ExpenseAdapter(List<Expense> models, RecyclerViewClickListener listener){
        mListener=listener;
        this.models = models;

    }

    public interface RecyclerViewClickListener {
        void onClick(View view, int position);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView amount,week,date, type;
        private RecyclerViewClickListener mListener;

        public MyViewHolder(View v,RecyclerViewClickListener listener){
            super(v);
            mListener=listener;
            v.setOnClickListener(this);
            amount = v.findViewById(R.id.amount);
            week = v.findViewById(R.id.week);
            date = v.findViewById(R.id.date);
            type = v.findViewById(R.id.type);

        }

        @Override
        public void onClick(View view){
            mListener.onClick(view,getAdapterPosition());
        }
    }



    @Override
    public ExpenseAdapter.MyViewHolder onCreateViewHolder (ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_expenses,parent,false);
        MyViewHolder vh = new MyViewHolder(v,mListener);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Expense expense = models.get(position);
        holder.amount.setText("$" + String.valueOf(expense.getAmount()));
        holder.date.setText(expense.getDate());
        holder.week.setText(String.valueOf(expense.getWeek()));
        holder.type.setText(expense.getType());

    }

    @Override
    public int getItemCount(){
        return models.size();
    }

}


