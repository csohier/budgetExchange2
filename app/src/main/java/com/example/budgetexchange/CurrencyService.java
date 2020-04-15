package com.example.budgetexchange;
import com.example.budgetexchange.Entities.Currency;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CurrencyService {
    @GET("latest?base=AUD")
    Call<Currency> getBase ();
}
