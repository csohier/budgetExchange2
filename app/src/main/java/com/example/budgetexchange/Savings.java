package com.example.budgetexchange;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class Savings extends AppCompatActivity {
HorizontalBarChart chart;
private TextView ttlSaved;
private TextView leftover;
private TextView savingGoal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_savings);

        savingGoal = findViewById(R.id.savingGoal);
        leftover = findViewById(R.id.leftover);
        ttlSaved = findViewById(R.id.ttlSaved);
        for(Goal a : Students.goals){
            if(a.getzID().equals(Students.currUser)){
                savingGoal.setText("$" + Integer.toString(a.getGoal()));
                int totalSaved = (a.getIncome()*a.getWeeksIntoGoal())-Expense.getSumOfExpenses(Expense.expenses);
                System.out.println("testttttt"+ a.getWeeksIntoGoal());
                ttlSaved.setText("$" +Integer.toString(totalSaved));
                leftover.setText("$" + Integer.toString(a.getGoal()-totalSaved));

            }
            //leftover = overall goal - total saved
            //ttlSaved = income to date - total sum of expenses
        }


        //Weeks to go, Saved so far, Saved this week
        chart = findViewById(R.id.chart1);
        setData(5, 50000);
    }

    private void setData(int count, int range){
        Date currentDate = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(currentDate);
        int currentWeek = calendar.get(Calendar.WEEK_OF_YEAR);
        System.out.println("current week is " + currentWeek);

        //create a counter for each category
        float[] categoryValues = new float[5];
       for(int a = 0; a<5;a++){
           categoryValues[a]=0;
       }

        //expenses.add(new BarEntry(0,190));
        //BarDataSet data = new BarDataSet(expenses,"Max Spend");
        //ArrayList<BarEntry> expenses2 = new ArrayList<>();
        //expenses2.add(new BarEntry(1,150));
        //BarDataSet data4 = new BarDataSet(expenses2,"Total Spend");

        List<List<BarEntry>> expenses = new ArrayList<>();
        List<BarDataSet> dataSet = new ArrayList<>();
        ArrayList<BarEntry> yValues = new ArrayList<>();
        String[] categories = {"food","utilities","transport","miscellaneous","personal"};
        float barWidth = 9f;
        float spaceForBar=10f;
        for(int i=0;i<5;i++){
            //5 different series

            //float val = (float)(Math.random()*range);
            //create a bar for each category
            //calculation for expense in a week
            for(int b = 0; b<Expense.expenses.size();b++){
                System.out.println(Expense.expenses.get(b).getWeek());
                if(Expense.expenses.get(b).getType().toLowerCase().equals(categories[i]) && Expense.expenses.get(b).getWeek()==currentWeek){

                    categoryValues[i] = categoryValues[i] + (float)Expense.expenses.get(b).getAmount();
                    System.out.println("category " + categoryValues[i]);


                }

            }

            BarEntry barD = new BarEntry(i*spaceForBar,categoryValues[i]);
            yValues.clear();
            yValues.add(barD);
            dataSet.add(new BarDataSet(yValues,categories[i]));
            System.out.println("yvalues AT "  + i + yValues);


            //name bar based on category
            //yValues.add(bar);

        }

        BarData data2 = new BarData(dataSet.get(0),dataSet.get(1),dataSet.get(2),dataSet.get(3),dataSet.get(4));
        dataSet.get(0).setColor(R.color.green);
        dataSet.get(1).setColor(R.color.yellow);
        dataSet.get(2).setColor(R.color.red);
        dataSet.get(3).setColor(R.color.medium);
        dataSet.get(4).setColor(R.color.medium);



        data2.setBarWidth(barWidth);
        chart.setData(data2);
        chart.getLegend().setOrientation(Legend.LegendOrientation.VERTICAL);
        chart.getLegend().setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        chart.getLegend().setWordWrapEnabled(true);
        chart.setDrawBorders(false);
        YAxis yAxis = chart.getAxisLeft();
        yAxis.setAxisMinimum(0);
        //yAxis.setDrawLabels(false);
        YAxis yAxis2 = chart.getAxisRight();
        yAxis2.setDrawGridLines(false);
        yAxis2.setDrawLabels(true);
        XAxis xAxis = chart.getXAxis();
        xAxis.setDrawLabels(false);
        xAxis.setDrawGridLines(false);
        yAxis.setDrawGridLines(false);
        yAxis.disableAxisLineDashedLine();
        yAxis.setTextColor(Color.WHITE);
        chart.setDrawBarShadow(false);
        chart.setDrawValueAboveBar(true);
        chart.setDrawGridBackground(true);






    }
}
