package com.example.budgetexchange;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ProgressFragment extends Fragment {

    private ProgressBar progressBar;
    private TextView progressText;
    public ProgressFragment(){ }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_progress_fragment, container, false);
        progressBar = view.findViewById(R.id.progressBar);
        progressText = view.findViewById(R.id.progressID);
        progressText.setText(String.valueOf(Students.searchGoals(Students.currUser).percentageSaved()) + "% saved");

        progressBar.setProgress(Students.searchGoals(Students.currUser).percentageSaved());
        return view;
    }
}
