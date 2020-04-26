package com.example.budgetexchange;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;


public class ProgressFragment extends Fragment {

    private ProgressBar progressBar;
    private TextView progressText;
    public ProgressFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_progress_fragment, container, false);
        progressBar = view.findViewById(R.id.progressBar);
        progressText = view.findViewById(R.id.progressID);

        //creates and populates progress bar with label
        //SOURCE: https://www.youtube.com/watch?v=2qkgqgeC5r4&t=322s
        progressText.setText(String.valueOf(Students.searchGoals(Students.currUser).percentageSaved()) + "% saved");


        progressBar.setProgress(23);
        return view;

    }
}
