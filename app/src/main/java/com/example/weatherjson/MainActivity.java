package com.example.weatherjson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weatherjson.model.Feed;
import com.example.weatherjson.model.Main;
import com.example.weatherjson.model.Weather;

import java.math.RoundingMode;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/";
    private static final String TAG = "MainActivity";

    TextView tvOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnGetData = findViewById(R.id.refreshbutton);
        tvOut = findViewById(R.id.tvOut);

        btnGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                OpenweatherAPI openweatherAPI = retrofit.create(OpenweatherAPI.class);

                Call<Feed> call = openweatherAPI.getMain();

                call.enqueue(new Callback<Feed>() {
                    @Override
                    public void onResponse(Call<Feed> call, Response<Feed> response) {
                        Log.d(TAG, "onResponse: Server Response: =" + response.toString());
                        Log.d(TAG, "onResponse: received information: =" + response.body().toString());

                        ArrayList<Weather> weatherList = response.body().getWeather();
                        Main mainData = response.body().getMain();

                        Log.d(TAG, "onResponse: \n" +
                                "-------------------------------" + "\n" +
                                "id: " + weatherList.get(0).getId() + "\n" +
                                "Main: " + weatherList.get(0).getMain() + "\n" +
                                "temperature: " + mainData.getTemp() + "\n" +
                                "-------------------------------" + "\n" + "\n" + "\n");
                        float temperatura = mainData.getTemp();
                        temperatura = Math.round(temperatura);

                        String text = "сейчас: " + Float.toString(temperatura - 273);
                        tvOut.setText(text);
                    }


                    @Override
                    public void onFailure(Call<Feed> call, Throwable t) {
                        Log.e(TAG, "onFailure: something went wrong" + t.getMessage());
                        Toast.makeText(MainActivity.this, "something went wrong", Toast.LENGTH_SHORT).show();

                    }
                });


            }
        });

    }
}
