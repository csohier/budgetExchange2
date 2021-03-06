package com.example.budgetexchange.CurrencyConverter;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/*Model class for the Currency API, where it takes in the base rate
  and returns the real-time conversion rate for all currencies*/

public class Currency {

    @SerializedName("rates")
    @Expose
    private Rates rates;
    @SerializedName("base")
    @Expose
    private String base;
    @SerializedName("date")
    @Expose
    private String date;

    //getters and setters
    public Rates getRates() {
        return rates;
    }

    public void setRates(Rates rates) {
        this.rates = rates;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}