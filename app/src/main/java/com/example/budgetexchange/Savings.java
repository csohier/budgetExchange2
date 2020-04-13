package com.example.budgetexchange;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.mikephil.charting.charts.HorizontalBarChart;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.savings);
        //Weeks to go, Saved so far, Saved this week

        setData(5, 50000);
    }

    private void setData(int count, int range){
        //current week
        Date currentDate = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(currentDate);
        int currentWeek = calendar.get(Calendar.WEEK_OF_YEAR);

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
        BarDataSet[] dataSet = new BarDataSet[5];
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
                if(Expense.expenses.get(b).getType().equals(categories[i]) && Expense.expenses.get(b).getWeek()==currentWeek){

                    categoryValues[i] = categoryValues[i] + (float)Expense.expenses.get(b).getAmount();




                }
                BarEntry barD = new BarEntry(i,categoryValues[i]);
                yValues.clear();
                yValues.add(barD);
                dataSet[i] = new BarDataSet(yValues,categories[i]);

            }


            //name bar based on category
            //yValues.add(bar);

        }

        BarData data2 = new BarData(dataSet[0],dataSet[1],dataSet[2],dataSet[3],dataSet[4]);

        data2.setBarWidth(barWidth);
        chart.setData(data2);




    }
}
