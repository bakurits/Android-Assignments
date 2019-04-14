package com.example.assignment2;

import com.example.assignment2.Models.Weather;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {

    @GET("v1/forecast.json")
    Call<Weather> getWeather(@Query("q") String countryName, @Query("key") String key, @Query("days") int days);

}
