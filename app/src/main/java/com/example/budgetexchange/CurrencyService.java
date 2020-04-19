package com.example.budgetexchange;

import com.example.budgetexchange.CurrencyConverter.Currency;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CurrencyService {

    @GET ("/api/latest")
    Call<Currency> getExchangeRates(@Query("base") String base, @Query("symbols") String symbols);
}
