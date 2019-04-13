package com.example.assignment2;

import android.content.res.ColorStateList;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.assignment2.Models.Astro;
import com.example.assignment2.Models.Weather;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

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


    private TextView topDateDay;
    private TextView topDateHour;
    private ImageView topGrad;
    private ImageView dayIndicatorIcon;
    private ImageView drop;
    private ImageView drops;
    private ImageView flag;

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
        dayIndicatorIcon = (ImageView) findViewById(R.id.imageView2);
        drop = (ImageView) findViewById(R.id.imageView3);
        drops = (ImageView) findViewById(R.id.imageView4);
        flag = (ImageView) findViewById(R.id.imageView5);
        topGrad = (ImageView) findViewById(R.id.imageView);

        topDateDay = (TextView) findViewById(R.id.dayInfo);
        topDateHour = (TextView) findViewById(R.id.timeInfo);


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

        api.listRepos(country, "1b4fcf51354d41179f595933190704", 10).enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(@NonNull Call<Weather> call, @NonNull Response<Weather> response) {

                if (response.isSuccessful()) {
                    assert response.body() != null;
                    fillData(response.body());
                }

            }

            @Override
            public void onFailure(@NonNull Call<Weather> call, @NonNull Throwable t) {
                Log.d("response_TAG", t.getMessage());
            }
        });
    }


    private void initializeRetrofit() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd")
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://api.apixu.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        api = retrofit.create(WeatherApi.class);
    }

    private void fillData(Weather data) {

        setupTheme(data.getCurrent().isDay());

        String city = data.getLocation().getCity();
        String perceived = data.getCurrent().getPerceived();
        String temperature = data.getCurrent().getTemp();
        String humidity = data.getCurrent().getHumidity();
        String wind = data.getCurrent().getMaxWind();
        Astro astro = data.getForecast().getForecast().get(0).getAstro();
        String sunRiseAndSet = astro.getSunrise() + " " + astro.getSunset();
        String precip = data.getCurrent().getPrecip();

        setTopTime(data.getLocation().getLocalTime());


        cityTextView.setText(city);
        perceivedTextView.setText(perceived);
        temperatureTextView.setText(temperature);
        humidityTextView.setText(humidity);
        windSpeedInfoTextView.setText(wind);
        dayAndNightTextView.setText(sunRiseAndSet);
        precipitationTextView.setText(precip);

        mAdapter = new RecyclerViewAdapter(data.getForecast().getForecast());

        recyclerView.setAdapter(mAdapter);


    }


    private void setupTheme(boolean isDay) {


        if (isDay) {
            flag.setImageTintList(null);
            drop.setImageTintList(null);
            drops.setImageTintList(null);
            dayIndicatorIcon.setImageResource(R.drawable.ic_sun);
            topGrad.setImageResource(R.drawable.top_gradiental_shape_day);

        } else {
            int colorInt = ContextCompat.getColor(flag.getContext(), R.color.colorDarkMode);
            flag.setImageTintList(ColorStateList.valueOf(colorInt));
            drop.setImageTintList(ColorStateList.valueOf(colorInt));
            drops.setImageTintList(ColorStateList.valueOf(colorInt));
            topGrad.setImageResource(R.drawable.top_gradiental_shape_night);
            dayIndicatorIcon.setImageResource(R.drawable.ic_moon);
        }

    }

    private void setTopTime(String tm) {
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.US).parse(tm);
            String topDate = new SimpleDateFormat("EEEE dd", Locale.US).format(date);
            String topTime = new SimpleDateFormat("hh:mm a", Locale.US).format(date);
            topDateDay.setText(topDate);
            topDateHour.setText(topTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
