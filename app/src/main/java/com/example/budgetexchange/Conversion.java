package com.example.budgetexchange;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.budgetexchange.CurrencyConverter.Currency;

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

        exchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exAmount.setText("getting amount");
                Log.d(TAG, "getting amount" );
                setRate();
            }
        });

        searchRates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search();
            }
        });
    }

    public void setRate(){
        //Retrofit is used to get the quote online from the API
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://exchangeratesapi.io/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        CurrencyService service = retrofit.create(CurrencyService.class);
        Call<CurrencyService> call = service.getBase(String.valueOf(baseSpinner));

        //Implement Currency Conversion Methods here


        call.enqueue(new Callback<CurrencyService>() {
            @Override
            public void onResponse(Call<CurrencyService> call, Response<CurrencyService> response) {



                CurrencyService exRate = response.body();
                //Activates toast to indicate the request updated
                Toast msg = Toast.makeText(Conversion.this, R.string.converted, Toast.LENGTH_SHORT);
                msg.show();

            }

            @Override
            public void onFailure(Call<CurrencyService> call, Throwable t) {
                Toast msg = Toast.makeText(Conversion.this, R.string.failure, Toast.LENGTH_SHORT);
                msg.show();
            }
        });
    }

    private void search() {
        String url = "https://exchangeratesapi.io/";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }
}

