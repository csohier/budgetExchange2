package com.example.budgetexchange;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.budgetexchange.CurrencyConverter.Currency;
import com.example.budgetexchange.CurrencyConverter.Rates;

import java.text.NumberFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Conversion extends AppCompatActivity {

    private static final String TAG = "Conversion";
    EditText baseAmount, exAmount;
    Button exchange, searchRates;
    Spinner baseSpinner, exSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion);


        baseAmount = findViewById(R.id.baseAmount);
        exAmount = findViewById(R.id.exAmount);
        exchange = findViewById(R.id.exchange);
        searchRates = findViewById(R.id.searchRates);
        ArrayAdapter<String> myAdapter= new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.currency));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        baseSpinner.setAdapter(myAdapter);
        exSpinner.setAdapter(myAdapter);

        exchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exAmount.setText("getting amount");
                Log.d(TAG, "getting amount" );

                String baseCurrency = baseSpinner.getSelectedItem().toString();
                String exCurrency = exSpinner.getSelectedItem().toString();

                if (baseCurrency.equals(exCurrency)) {
                    Toast.makeText(Conversion.this, "Same Currency", Toast.LENGTH_SHORT).show();

                    return;
                }

                double amount;

                try {
                    amount = Double.parseDouble(baseAmount.getText().toString());
                } catch (NumberFormatException e) {
                    Toast.makeText(Conversion.this, "Wrong amount input", Toast.LENGTH_SHORT).show();

                    return;
                }

                convertAmount (baseCurrency, exCurrency, amount);
            }
        });

        searchRates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search();
            }
        });
    }

    public void convertAmount(final String baseCurrency, final String exCurrency, final double amount){
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://exchangeratesapi.io")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        CurrencyService service = retrofit.create(CurrencyService.class);
        service.getExchangeRates(baseCurrency, exCurrency).enqueue(new Callback<Currency>() {
            @Override
            public void onResponse(Call<Currency> call, Response<Currency> response) {
                Currency rate = response.body();
                double exchangeRate = rate.getRates().getRateFor(exCurrency);
                double convertedAmount = amount * exchangeRate;
                String msg = exCurrency + " " + convertedAmount;
                exAmount.setText(msg);
            }

            @Override
            public void onFailure(Call<Currency> call, Throwable t) {
                Toast.makeText(Conversion.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void search() {
        String url = "https://exchangeratesapi.io/";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }
}

