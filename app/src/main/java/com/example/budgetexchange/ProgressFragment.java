package com.example.budgetexchange;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;



public class ProgressFragment extends Fragment {

    private ProgressBar progressBar;
    public ProgressFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_progress_fragment, container, false);
        progressBar = view.findViewById(R.id.progressBar);

        progressBar.setProgress(23);
        return view;

    }
}
