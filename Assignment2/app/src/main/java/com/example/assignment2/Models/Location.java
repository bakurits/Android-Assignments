package com.example.assignment2.Models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Location {

    private String country;
    @SerializedName("name")
    private String city;

    @SerializedName("localtime")
    private String localTime;

    public String getCity() {
        if (city == null) return "Empty";
        return city;
    }

    public String getCountry() {
        if (country == null) return "Empty";
        return country;
    }

    public String getLocalTime() {
        if (localTime == null) return "Empty";
        return localTime;
    }
}
