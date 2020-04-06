package com.example.budgetexchange;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ColorFormatter;

import java.time.LocalDate;
import java.util.ArrayList;

public class ExpenseFeed extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ExpenseAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private Button button;
    private static ArrayList<Expense> expenses;
    //private PieChart pieChart;
    private BarChart barChart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_feed);
        recyclerView = (RecyclerView)findViewById(R.id.rvList);
        recyclerView.setHasFixedSize(true);

        ExpenseAdapter.RecyclerViewClickListener listener = new ExpenseAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
            }
        };

        expenses = Expense.getExpenses();

        mAdapter = new ExpenseAdapter(expenses,listener);

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
                ClickAddExpense();
            }
        });

        barChart();

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

    private BarChart barChart(){
        barChart = findViewById(R.id.barChart);
        ArrayList<BarEntry> expenses = new ArrayList<>();
        expenses.add(new BarEntry(0,190));
        BarDataSet data = new BarDataSet(expenses,"Max Spend");
        ArrayList<BarEntry> expenses2 = new ArrayList<>();
        expenses2.add(new BarEntry(1,150));
        BarDataSet data4 = new BarDataSet(expenses2,"Total Spend");
        BarData data2 = new BarData(data,data4);
        data4.setColors(Color.YELLOW);
        data.setColors(Color.MAGENTA);
        barChart.setData(data2);
        barChart.getBarBounds(expenses.get(0));
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
        yAxis.setTextColor(Color.WHITE);
        //barChart.setNoDataTextColor(Color.WHITE);
        barChart.setDrawBorders(false);
        barChart.getLegend().setOrientation(Legend.LegendOrientation.VERTICAL);

        barChart.setBorderColor(Color.TRANSPARENT);
        barChart.getLegend().setTextColor(Color.WHITE);
        barChart.getLegend().setTextSize(10);

        data4.setValueTextColor(Color.WHITE);
        data.setValueTextColor(Color.WHITE);
        data4.setValueTextSize(20);
        data.setValueTextSize(20);

        yAxis.disableGridDashedLine();
        return barChart;
    }

   // private int weeks(String future, LocalDate current){

        //take in goal date and current date time value to derive weeks

    //}


}

