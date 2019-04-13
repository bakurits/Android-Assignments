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
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getLocalTime() {
        return localTime;
    }
}
