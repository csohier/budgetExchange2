package com.example.budgetexchange;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.budgetexchange.Expenses.Expense;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.dataprovider.BarDataProvider;

import java.lang.reflect.Array;
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
private ScrollView savingBar;
private int totalSaved;
private int leftoverSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_savings);

        savingGoal = findViewById(R.id.savingGoal);
        leftover = findViewById(R.id.leftover);
        ttlSaved = findViewById(R.id.ttlSaved);
        savingBar = findViewById(R.id.savingProgressBar);

        //loads a progress bar which shows the percentage a user has saved to date.
        loadFragment();

        //set data in the top cardview such as total saved, amount to save, and savings goal
        //the following for loop calculates the total amount saved by the user and the amount left to save
        for(Goal a : Students.goals){
            if(a.getzID().equals(Students.currUser)){
                savingGoal.setText("$" + Integer.toString(a.getGoal()));
                totalSaved = (a.getIncome()*a.getWeeksIntoGoal())- Expense.getSumOfExpenses(Expense.expenses);
                a.setTotalSaved(totalSaved);
                System.out.println("Total Saved: " + a.getTotalSaved());
                System.out.println("Weeks into goal: "+ a.getWeeksIntoGoal());
                ttlSaved.setText("$" +Integer.toString(totalSaved));
                //ttlSaved = income to date - total sum of expenses
                leftoverSave= a.getGoal()-totalSaved;
                leftover.setText("$" + Integer.toString(leftoverSave));
                //leftover = overall goal - total saved
            }
        }


        chart = findViewById(R.id.chart1);

        //bar chart which displays a users expenses during the current week
        //BAR CHART SOURCE: https://www.youtube.com/watch?v=sXo2SkX7rGk
        setData(5, 50000);

    }

    private void setData(int count, int range){


        //find the int value of current week of the year
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

       //create an array of categories which will represent a bar on the bar chart
        String[] categories = {"food","utilities","transport","miscellaneous","personal"};

       //set bar width and spacing
        float barWidth = 9f;
        float spaceForBar=10f;

        //Instantiate 5 bar entry arraylists which will hold expense data
        ArrayList<ArrayList<BarEntry>> BarEntryCategories = new ArrayList<>();
        for(int i=0; i<5;i++){
            BarEntryCategories.add(new ArrayList<>());
        }


        ArrayList<BarDataSet> chartBars = new ArrayList<>();

        //create a for loop which populates bar graph data
        for(int i=0;i<5;i++){

            //create a bar for each category
            //calculation for expense in a week
            //Iterates through all expenses
            for(int b = 0; b<Expense.expenses.size();b++){
                System.out.println(Expense.expenses.get(b).getWeek());
                //if the expense type equals the category at i (food, utilities etc..) AND the expense falls within the current week
                if(Expense.expenses.get(b).getType().toLowerCase().equals(categories[i]) && Expense.expenses.get(b).getWeek()==currentWeek){

                    //Add the value of that expense to
                    categoryValues[i] = categoryValues[i] + (float)Expense.expenses.get(b).getAmount();
                    System.out.println("category " + categoryValues[i]);

                }
            }

            BarEntryCategories.get(i).add(new BarEntry(i*spaceForBar,categoryValues[i]));

            chartBars.add(new BarDataSet(BarEntryCategories.get(i),categories[i]));

        }

        for(BarDataSet b : chartBars){
            System.out.println(b.getLabel());
            System.out.println(b.getXMax());
        }

        //Bar Data contains Bar Data Sets, in this instance a Bar Data Set will contain a single value such as x=Miscellaneous Expense, y= $amount
        BarData data2 = new BarData(chartBars.get(0),chartBars.get(1),chartBars.get(2),chartBars.get(3),chartBars.get(4));

        //bar data customizations
        data2.setHighlightEnabled(false);
        data2.setBarWidth(barWidth);

        //set the text size of colour of each respective bar in the graph
        chartBars.get(0).setColor(getResources().getColor(R.color.green));
        chartBars.get(0).setValueTextSize(20);
        chartBars.get(1).setColor(getResources().getColor(R.color.yellow));
        chartBars.get(1).setValueTextSize(20);
        chartBars.get(2).setColor(getResources().getColor(R.color.red));
        chartBars.get(2).setValueTextSize(20);
        chartBars.get(3).setColor(getResources().getColor(R.color.colorPrimaryDark));
        chartBars.get(3).setValueTextSize(20);
        chartBars.get(4).setColor(getResources().getColor(R.color.colorAccent));
        chartBars.get(4).setValueTextSize(20);


        //Populating the chart with Bar Data
        chart.setData(data2);

        //chart customizations
        chart.setFitBars(true);
        chart.setScaleEnabled(false);
        chart.getLegend().setOrientation(Legend.LegendOrientation.VERTICAL);
        chart.getLegend().setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        chart.getLegend().setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        chart.getLegend().setWordWrapEnabled(true);
        chart.setDrawBorders(false);
        YAxis yAxis = chart.getAxisLeft();
        yAxis.setAxisMinimum(0);
        YAxis yAxis2 = chart.getAxisRight();
        yAxis2.setDrawGridLines(false);
        yAxis2.setDrawLabels(true);
        XAxis xAxis = chart.getXAxis();
        xAxis.setDrawLabels(false);
        xAxis.setDrawGridLines(false);
        yAxis.setDrawGridLines(false);
        yAxis.disableAxisLineDashedLine();
        yAxis.setTextColor(Color.WHITE);
        yAxis2.setTextColor(Color.WHITE);
        chart.setDrawBarShadow(false);
        chart.setDrawValueAboveBar(true);
        chart.setDrawGridBackground(true);
        chart.getLegend().setTextColor(Color.WHITE);
        chart.getLegend().setTextSize(15);
        chart.getDescription().setEnabled(false);


    }

    private void loadFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Fragment fragment = new ProgressFragment();
        transaction.replace(R.id.savingProgressBar,fragment);
        transaction.commit();

    }
}
