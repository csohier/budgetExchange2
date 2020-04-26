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
import com.example.budgetexchange.HomeActivity;
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
    private Button backBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_feed);
        recyclerView = (RecyclerView)findViewById(R.id.rvList);
        recyclerView.setHasFixedSize(true);
        spendInfo = findViewById(R.id.spendInfo);
        backBtn = findViewById(R.id.backBtn);

        ExpenseAdapter.RecyclerViewClickListener listener = new ExpenseAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
            }
        };


        mAdapter = new ExpenseAdapter(Expense.expenses,listener);

        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Button to add expense
        button = findViewById(R.id.AddButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter++;
                clickAddExpense();
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickHome();
            }
        });
        try {
            //populate a bar chart which shows the sum of expenses this week and max spend based on your savings goal
            //income, and weeks
            barChart();

            //shows the appropriate feedback to the user based on whether they are under, over, or at budget
            setText();

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    private void clickAddExpense() {
        Intent intent = new Intent(this, AddExpense.class);
        startActivity(intent);
    }

    private void clickHome() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    private BarChart barChart() throws ParseException {
        barChart = findViewById(R.id.barChart);

        //max spend means maximum amount that can be spent in a week
        //max spend = weekly income - [ savings goal/(end date - start date in weeks) ]
        ArrayList<BarEntry> maxSpend = new ArrayList<>();
        for(Goal a: Students.goals){
            if(a.getzID().equals(Students.currUser)){
                max=(a.getGoal()/(a.getWeeks()))-a.getIncome();
                System.out.println("INCOME IS: " + a.getIncome());
                System.out.println("GOAL IS: " + a.getGoal());
                System.out.println("WEEKS ARE: " + a.getWeeks());
            }
        }

        //creating a bar for the bar graph that shows the max spend/budget
        maxSpend.add(new BarEntry(0,max));
        BarDataSet data = new BarDataSet(maxSpend,"Max Spend");


        //actual spend means how much has been spent in total in the current week based on current expenses
        ArrayList<BarEntry> actualSpend = new ArrayList<>();
        expenseTotal = 0;
        for(Expense a: Expense.expenses){
            System.out.println("Current Week :" + Expense.currentWeek());
            if(a.getzID().equals(Students.currUser) && Expense.currentWeek()==a.getWeek()){
                //sum all expenses
                expenseTotal = expenseTotal + (int)a.getAmount();

            }
        }

        //creating a bar for the bar graph that actual spend
        actualSpend.add(new BarEntry(1,expenseTotal));
        BarDataSet data4 = new BarDataSet(actualSpend,"Total Spend");

        //populate the bar graph with max spend, and total spend
        BarData data2 = new BarData(data,data4);

        //bar chart customizations
        data2.setHighlightEnabled(false);
        data4.setColors(Color.YELLOW);
        data.setColors(Color.MAGENTA);
        barChart.setData(data2);
        barChart.getBarBounds(maxSpend.get(0));
        YAxis yAxis = barChart.getAxisLeft();
        yAxis.setAxisMinimum(0);
        YAxis yAxis2 = barChart.getAxisRight();
        yAxis2.setDrawGridLines(false);
        yAxis2.setDrawLabels(false);
        XAxis xAxis = barChart.getXAxis();
        xAxis.setDrawLabels(false);
        xAxis.setDrawGridLines(false);
        yAxis.setDrawGridLines(false);
        yAxis.disableAxisLineDashedLine();
        yAxis.setTextColor(Color.WHITE);
        barChart.setNoDataTextColor(Color.WHITE);
        barChart.setDrawBorders(false);
        barChart.getLegend().setOrientation(Legend.LegendOrientation.VERTICAL);
        barChart.getLegend().setTextColor(Color.WHITE);
        barChart.getLegend().setTextSize(10);
        data4.setValueTextSize(20);
        data.setValueTextSize(20);
        barChart.setDrawBarShadow(false);
        barChart.setDrawValueAboveBar(true);
        barChart.setDrawGridBackground(true);
        yAxis.disableGridDashedLine();
        barChart.getDescription().setEnabled(false);
        barChart.setFitBars(true);
        barChart.setScaleEnabled(false);


        return barChart;
    }


    public void setText(){
        if(expenseTotal>max) {
            spendInfo.setText("Based on your current spending and budget for this week, you are over budget by $" + (Math.abs((max-expenseTotal))));
        } else if (expenseTotal==max){
            spendInfo.setText("Based on your current spending and budget for this week, if you spend any more you'll be over budget.");
        }else {
            spendInfo.setText("You have $" + (max-expenseTotal) + " to spend until you're over budget this week.");
        }
    }


}

