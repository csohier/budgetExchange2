package com.example.budgetexchange;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CurrencyService {

    @GET ("latest")
    Call<CurrencyService> getBase(@Query("base") String base);

}
