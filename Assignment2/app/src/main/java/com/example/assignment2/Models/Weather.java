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
        if (forecast == null) new Forecast();
        return forecast;
    }

    public CurrentWeather getCurrent() {
        if (current == null) new CurrentWeather();
        return current;
    }

    public Location getLocation() {
        if (location == null) new Location();
        return location;
    }
}
