package com.example.assignment2.Models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Forecast {
    @SerializedName("forecastday")
    private List<DayInfo> forecast;

    public List<DayInfo> getForecast() {
        if (forecast == null) return new ArrayList<>();
        return forecast;
    }
}
