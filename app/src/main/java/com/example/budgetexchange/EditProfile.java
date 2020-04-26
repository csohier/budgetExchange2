package com.example.budgetexchange;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.budgetexchange.DataBase.AppDatabase;
import com.example.budgetexchange.DataBase.Student.AsyncTaskStudentDelegate;
import com.example.budgetexchange.DataBase.Student.Student;
import com.example.budgetexchange.DataBase.Student.UpdateStudentByZIDAsyncTask;
import com.google.android.material.snackbar.Snackbar;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class EditProfile extends AppCompatActivity implements AsyncTaskStudentDelegate {
    private static final String TAG = "EditProfile Activity";
    private EditText fName,lName, zID, email,startDate,weeklyIncome, goalAmount, goalStart, goalEnd;
    private Students user;
    private Button saveBtn;
    private Goal goal;
    private Spinner university;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        fName = findViewById(R.id.fNameE);
        lName = findViewById(R.id.lNameE);
        zID = findViewById(R.id.zIDE);
        email = findViewById(R.id.emailE);
        university = findViewById(R.id.universityE);
        startDate = findViewById(R.id.startDateE);
        weeklyIncome = findViewById(R.id.wkIncomeE);
        saveBtn = findViewById(R.id.tvSave);
        goalAmount = findViewById(R.id.goalsAmountE);
        goalStart = findViewById(R.id.goalStartE);
        goalEnd = findViewById(R.id.goalEndE);

        System.out.println(Students.currUser);
        System.out.println(Students.searchStudents(Students.currUser));

        user = Students.searchStudents(Students.currUser);
        System.out.print(user.toString());
        System.out.print(user.getfName());

        fName.setText(user.getfName());
        lName.setText(user.getlName());
        zID.setText(user.getzID());
        email.setText(user.getEmail());
        ArrayAdapter<String> myAdapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.university_name));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        university.setAdapter(myAdapter);
        startDate.setText(user.getStDateString());
        weeklyIncome.setText("$" + String.valueOf(user.getWkIncome()));

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String i = extras.getString("indicator");
        String amt = extras.getString("amount");
        String start = extras.getString("startDate");
        String end = extras.getString("endDate");
        goalAmount.setHint(amt);
        goalStart.setHint(start);
        goalEnd.setHint(end);

        for(Goal a: Students.goals){
            if(a.getzID().equals(Students.currUser)){
                goal = user.searchGoals(user.getzID());
                goalAmount.setText(String.valueOf(goal.getGoal()));
                goalEnd.setText(goal.getGoalEndDate());
                goalStart.setText(goal.getGoalStartDate());
            }
        }

        saveBtn.setOnClickListener(v -> {

            //Checks if fields are empty
            if (checkFieldsEmpty(fName) || checkFieldsEmpty(lName) ||
                    checkFieldsEmpty(zID) || checkFieldsEmpty(email) ||
                    checkFieldsEmpty(startDate) || checkFieldsEmpty(weeklyIncome) ||
                    university.getSelectedItem().equals(" ") || checkFieldsEmpty(goalStart) ||
                    checkFieldsEmpty(goalAmount) || checkFieldsEmpty(goalEnd)) {

                //Display error message
                incompleteEdit(v);

            } else {

                AppDatabase db = AppDatabase.getInstance(EditProfile.this);

                //Grab all the usernames to check if the same username doesn't already exist
                UpdateStudentByZIDAsyncTask updateStudentByZIDAsyncTask = new UpdateStudentByZIDAsyncTask();
                updateStudentByZIDAsyncTask.setDatabase(db);
                updateStudentByZIDAsyncTask.setDelegate(EditProfile.this);
                updateStudentByZIDAsyncTask.execute();
            }
        });
    }


    private void saveProfile() {
        Intent intent = new Intent (this, ProfileActivity.class);
        startActivity(intent);
    }

    public void incompleteEdit (View v) {
        Snackbar snackbar = Snackbar.make(v, "Please fill out all parts of the page", Snackbar.LENGTH_LONG);
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(getResources().getColor(R.color.red));
        snackbar.show();
    }

    public boolean checkFieldsEmpty(EditText editText) {
        return TextUtils.isEmpty(editText.getText());
    }

    //Checks the date format
    public Boolean checkDateFormat(String date){
        if (date == null || !date.matches("^(1[0-9]|0[1-9]|3[0-1]|2[1-9])/(0[1-9]|1[0-2])/[0-9]{4}$"))
            return false;
        SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");
        try {
            format.parse(date);
            return true;
        }catch (ParseException e){
            return false;
        }
    }

    //Updates the Student in the database upon success
    @Override
    public void handleUpdateStudentByZID(String result) {
        //checks whether all fields are filled
        if (!checkDateFormat(startDate.getText().toString()) || !checkDateFormat(goalStart.getText().toString()) || !checkDateFormat(goalEnd.getText().toString())) {
            invalidDates();
        } else if (Integer.parseInt(String.valueOf(goalStart)) > Integer.parseInt(String.valueOf(goalEnd))){
            invalidDates();
        } else {

            AppDatabase db = AppDatabase.getInstance(EditProfile.this);
            //Update this student to database
            UpdateStudentByZIDAsyncTask updateStudentByZIDAsyncTask = new UpdateStudentByZIDAsyncTask();
            updateStudentByZIDAsyncTask.setDatabase(db);
            updateStudentByZIDAsyncTask.setDelegate(this);
            updateStudentByZIDAsyncTask.execute();

            saveProfile();
        }
    }

    public void invalidDates() { Toast.makeText(EditProfile.this, "Invalid Dates", Toast.LENGTH_SHORT).show(); }

    @Override
    public void handleInsertStudentResult(String result) { }

    @Override
    public void handleGetAllStudentsResult(List<Student> student) { }

    @Override
    public void handleGetZIDResult(List<String> zID) { }

    @Override
    public void handleGetStudentByZID(Student zID) { }
}
