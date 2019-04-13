package com.example.assignment2.Models;

import com.google.gson.annotations.SerializedName;

public class Weather {

    @SerializedName("location")
    private Location location;

    @SerializedName("current")
    private CurrentWeather current;

    @SerializedName("forecast")
    private Forecast forecast;


    public Forecast getForecast() {
        return forecast;
    }

    public CurrentWeather getCurrent() {
        return current;
    }

    public Location getLocation() {
        return location;
    }
}
