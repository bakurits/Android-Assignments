package com.example.assignment2;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private Retrofit retrofit;
    private WeatherApi api;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    private TextView cityTextView;
    private TextView perceivedTextView;
    private TextView precipitationTextView;
    private TextView humidityTextView;
    private TextView windSpeedInfoTextView;
    private TextView dayAndNightTextView;
    private TextView temperatureTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        cityTextView = (TextView) findViewById(R.id.cityName);
        perceivedTextView = (TextView) findViewById(R.id.perceivedInfo);
        precipitationTextView = (TextView) findViewById(R.id.precipitationInfo);
        humidityTextView = (TextView) findViewById(R.id.humidityInfo);
        windSpeedInfoTextView = (TextView) findViewById(R.id.windSpeedInfo);
        dayAndNightTextView = (TextView) findViewById(R.id.dayAndNightInfo);
        temperatureTextView = (TextView) findViewById(R.id.temperatureInfo);


        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        initializeRetrofit();

        getForecast("Mozambique");

    }

    private void getForecast(String country) {

        api.listRepos(country, "1b4fcf51354d41179f595933190704", 10).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(@NonNull Call<JsonObject> call, @NonNull Response<JsonObject> response) {

                if (response.isSuccessful()) {
                    assert response.body() != null;
                    fillData(response.body());
                }

            }

            @Override
            public void onFailure(@NonNull Call<JsonObject> call, @NonNull Throwable t) {
                Log.d("response_TAG", "onFailure");
            }
        });
    }


    private void initializeRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://api.apixu.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(WeatherApi.class);
    }

    private void fillData(JsonObject data) {

        String city = data.get("location").getAsJsonObject().get("name").getAsString();
        String perceived = data.get("current").getAsJsonObject().get("feelslike_c").getAsString() + "%";
        String temperature = data.get("current").getAsJsonObject().get("temp_c").getAsString();
        String humidity = data.get("current").getAsJsonObject().get("humidity").getAsString() + "%";
        String wind = data.get("current").getAsJsonObject().get("wind_kph").getAsString() + " kph";
        String sunRiseAndSet = "";

        JsonArray arr = data.get("forecast").getAsJsonObject().get("forecastday").getAsJsonArray();
        ArrayList<WeatherByDay> lst = new ArrayList<>();


        for (int i = 0; i < arr.size(); i++) {
            JsonObject item = arr.get(i).getAsJsonObject();
            WeatherByDay day = new WeatherByDay(item.get("date").getAsString(),
                    item.get("day").getAsJsonObject().get("avgtemp_c").getAsDouble(),
                    item.get("day").getAsJsonObject().get("condition").getAsJsonObject().get("icon").getAsString());
            if (sunRiseAndSet.equals("")) {
                sunRiseAndSet = item.get("astro").getAsJsonObject().get("sunrise").getAsString() + " " + item.get("astro").getAsJsonObject().get("sunset").getAsString();
            }
            lst.add(day);
        }

        cityTextView.setText(city);
        perceivedTextView.setText(perceived);
        temperatureTextView.setText(temperature);
        humidityTextView.setText(humidity);
        windSpeedInfoTextView.setText(wind);
        dayAndNightTextView.setText(sunRiseAndSet);


        mAdapter = new RecyclerViewAdapter(lst);

        recyclerView.setAdapter(mAdapter);
    }
}
