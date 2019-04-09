package com.example.assignment2;

import com.google.gson.Gson;
import com.google.gson.JsonObject;


import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {

    @GET("v1/forecast.json")
    Call<JsonObject> listRepos(@Query("q") String countryName, @Query("key") String key, @Query("days") int days);

}
