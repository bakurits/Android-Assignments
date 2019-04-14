package com.example.assignment2.Models;

public class Astro {

    private String sunrise;
    private String sunset;

    public String getSunrise() {
        if (sunrise == null) return "Empty";
        return sunrise.replaceAll("\\s", "");
    }

    public String getSunset() {
        if (sunset == null) return "Empty";
        return sunset.replaceAll("\\s", "");
    }
}
