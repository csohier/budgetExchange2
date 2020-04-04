package com.example.budgetexchange;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.android.material.textfield.TextInputEditText;
import com.google.common.collect.Range;

public class AddExpense extends AppCompatActivity {
    private EditText expAmt;
    private EditText expType;
    private EditText expDate;
    private Button expEnter;
    private EditText expWeek;

    AwesomeValidation awesomeValidation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        expAmt = findViewById(R.id.exAmount);
        expType = findViewById(R.id.exType);
        expDate = findViewById(R.id.exDate);
        expWeek = findViewById(R.id.exWeek);

        expEnter= findViewById(R.id.enter);
        expEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                double amount = Double.valueOf(expAmt.getText().toString());
                String type = String.valueOf(expType.getText());
                String date = String.valueOf(expDate.getText());
                int week = Integer.valueOf(expWeek.getText().toString());

                //
                awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);


            }
    });
}
}