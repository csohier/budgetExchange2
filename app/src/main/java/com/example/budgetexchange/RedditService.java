package com.example.budgetexchange;

import com.example.budgetexchange.Reddit.DataDetail;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RedditService {
    @GET("/r/budgetfood/top.json?limit=30")
    Call<List<DataDetail>> getFood();


}
