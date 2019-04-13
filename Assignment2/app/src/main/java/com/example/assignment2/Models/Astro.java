package com.example.assignment2.Models;

public class Astro {

    private String sunrise;
    private String sunset;

    public String getSunrise() {
        return sunrise.replaceAll("\\s", "");
    }

    public String getSunset() {
        return sunset.replaceAll("\\s", "");
    }
}
