package com.example.assignment2;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

class WeatherByDay {
    private String date;
    private String weather;
    private String iconUrl;

    WeatherByDay(String date, double weather, String iconUrl) {
        SimpleDateFormat fmnth = new SimpleDateFormat("MMM", Locale.US);
        SimpleDateFormat fday = new SimpleDateFormat("d", Locale.US);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        try {
            Date dt = format.parse(date);
            this.date = fday.format(dt) + " " + fmnth.format(dt);

            this.iconUrl = iconUrl;
            this.weather = weather + "\u2103";
        } catch (Exception e) {
            this.date = "Err";

            this.iconUrl = "Err";
            this.weather = "Err";
        }

    }

    public String getDay() {

        return date;
    }

    public String getWeather() {
        return weather;
    }

    public String getIconUrl() {
        return iconUrl;
    }
}
