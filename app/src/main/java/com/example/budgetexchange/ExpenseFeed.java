package com.example.budgetexchange;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ExpenseFeed extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ExpenseAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private Button button;
    //private PieChart pieChart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_feed);
        recyclerView = (RecyclerView)findViewById(R.id.rvList);
        recyclerView.setHasFixedSize(true);
        //launchDetailActivity(position);

        /*MyAdaptor.RecyclerViewClickListener listener = new MyAdaptor.RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                counter++;
                launchDetailActivity(position);
            }
        };
        mAdapter = new ExpenseAdapter(Expense.getExpenses(),listener);
        */
        recyclerView.setAdapter(mAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClickAddExpense();
            }
        });

    }




    /*public void launchDetailActivity(int position){
        Intent intent = new Intent(this,DetaillActivity.class);
        intent.putExtra("message",position);
        startActivity(intent);
    } */


    private void ClickAddExpense() {
        Intent intent = new Intent(this, AddExpense.class);
        startActivity(intent);
    }
}

