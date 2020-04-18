package com.example.budgetexchange.Expenses;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.budgetexchange.Goal;
import com.example.budgetexchange.R;
import com.example.budgetexchange.Students;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import java.text.ParseException;
import java.util.ArrayList;

public class ExpenseFeed extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ExpenseAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private Button button;
    private static ArrayList<Expense> expenses;
    private BarChart barChart;
    static int counter;
    private int max;
    private int expenseTotal;
    private TextView spendInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_feed);
        recyclerView = (RecyclerView)findViewById(R.id.rvList);
        recyclerView.setHasFixedSize(true);
        spendInfo = findViewById(R.id.spendInfo);

        for(Goal a: Students.goals){
            System.out.println("WEEKS :" + a.getWeeks());
        }
        ExpenseAdapter.RecyclerViewClickListener listener = new ExpenseAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
            }
        };


        if(counter==0) {
            mAdapter = new ExpenseAdapter(Expense.getExpenses(), listener);
        } else {
            mAdapter = new ExpenseAdapter(Expense.expenses,listener);

        }


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
        button = findViewById(R.id.AddButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter++;
                ClickAddExpense();
            }
        });

        try {
            barChart();
            setText();
        } catch (ParseException e) {
            e.printStackTrace();
        }



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

    private BarChart barChart() throws ParseException {
        barChart = findViewById(R.id.barChart);
        ArrayList<BarEntry> maxSpend = new ArrayList<>();

        //max spend means maximum amount that can be spent in a week
        //calculation = weekly income - [ savings goal/(end date - start date in weeks) ]
        for(Goal a: Students.goals){
            if(a.getzID().equals(Students.currUser)){
                max=a.getIncome() - (a.getGoal()/(a.getWeeks()));
            }
        }
        maxSpend.add(new BarEntry(0,max));
        BarDataSet data = new BarDataSet(maxSpend,"Max Spend");
        ArrayList<BarEntry> actualSpend = new ArrayList<>();

        //actual spend means how much has been spent in total in the current week based on current expenses
        // requires a for loop to iterate through all expenses
        // check for condition returning expenses made in the current week
        // sum all expenses
        expenseTotal = 0;
        for(Expense a: Expense.expenses){
            System.out.println("Current Week :" + Expense.currentWeek());
            if(a.getzID().equals(Students.currUser) && Expense.currentWeek()==a.getWeek()){
                //sum all expenses
                expenseTotal = expenseTotal + (int)a.getAmount();

            }
        }

        actualSpend.add(new BarEntry(1,expenseTotal));
        BarDataSet data4 = new BarDataSet(actualSpend,"Total Spend");
        BarData data2 = new BarData(data,data4);
        data4.setColors(Color.YELLOW);
        data.setColors(Color.MAGENTA);
        barChart.setData(data2);
        barChart.getBarBounds(maxSpend.get(0));
        YAxis yAxis = barChart.getAxisLeft();
        yAxis.setAxisMinimum(0);
        //yAxis.setDrawLabels(false);
        YAxis yAxis2 = barChart.getAxisRight();
        yAxis2.setDrawGridLines(false);
        yAxis2.setDrawLabels(false);
        XAxis xAxis = barChart.getXAxis();
        xAxis.setDrawLabels(false);
        xAxis.setDrawGridLines(false);
        yAxis.setDrawGridLines(false);
        yAxis.disableAxisLineDashedLine();
        //yAxis.setTextColor(Color.WHITE);
        //barChart.setNoDataTextColor(Color.WHITE);
        barChart.setDrawBorders(false);
        barChart.getLegend().setOrientation(Legend.LegendOrientation.VERTICAL);

        barChart.setBorderColor(Color.TRANSPARENT);
        //barChart.getLegend().setTextColor(Color.WHITE);
        barChart.getLegend().setTextSize(10);

        data4.setValueTextColor(Color.WHITE);
        data.setValueTextColor(Color.WHITE);
        data4.setValueTextSize(20);
        data.setValueTextSize(20);
        barChart.setDrawBarShadow(false);
        barChart.setDrawValueAboveBar(true);
        barChart.setDrawGridBackground(true);

        yAxis.disableGridDashedLine();
        return barChart;
    }

   // private int weeks(String future, LocalDate current){

        //take in goal date and current date time value to derive weeks

    //}

    public void setText(){
        if(expenseTotal>max) {
            spendInfo.setText("You are over budget by $" + (max-expenseTotal));
        } else if (expenseTotal==max){
            spendInfo.setText("Spend any more and you'll be over budget.");

        }else {
            spendInfo.setText("You have $" + (max-expenseTotal) + " to spend until you're over budget.");
        }
    }


}

