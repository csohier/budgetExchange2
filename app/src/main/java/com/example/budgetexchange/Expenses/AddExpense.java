package com.example.budgetexchange.Expenses;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.example.budgetexchange.R;

import net.yslibrary.android.keyboardvisibilityevent.util.UIUtil;

public class AddExpense extends AppCompatActivity {
    private EditText expAmt;
    private EditText expType;
    private EditText expDate;
    private Button expEnter;
    private EditText description;
    private Spinner spinner;

    AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);

        expAmt = findViewById(R.id.exAmount);
        //expType = findViewById(R.id.exType);
        expDate = findViewById(R.id.exDate);
        description = findViewById(R.id.description);

        spinner = (Spinner)findViewById(R.id.spinner);
        ArrayAdapter<String>myAdapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.category));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(myAdapter);

        expEnter= findViewById(R.id.enter);

        description.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                boolean set = false;
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    UIUtil.hideKeyboard(AddExpense.this);
                    set = true;
                    return true;

                } else {
                    UIUtil.showKeyboard(AddExpense.this,description);
                }
                UIUtil.hideKeyboard(AddExpense.this);
                return false;
            }
        });
        expEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                double amount = Double.valueOf(expAmt.getText().toString());

                String type = spinner.getSelectedItem().toString();
                String date = String.valueOf(expDate.getText());

                if(!description.getText().toString().equals(null)) {
                    String desc = String.valueOf(description.getText());
                    Expense.expenses.add(new Expense(amount, type, date, desc));
                }else{
                    Expense.expenses.add(new Expense(amount, type, date));

                }

                System.out.println(Expense.expenses);
                Intent intent = new Intent(AddExpense.this, ExpenseFeed.class);
                startActivity(intent);

                //
                //awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);


            }
    });


}
}