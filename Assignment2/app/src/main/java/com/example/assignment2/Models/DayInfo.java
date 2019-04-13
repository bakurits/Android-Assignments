package com.example.assignment2.Models;

import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import java.text.DateFormatSymbols;

public class DayInfo {


    private class Temperature {
        @SerializedName("avgtemp_c")
        private Double temp;

        @SerializedName("condition")
        private Condition condition;

        Condition getCondition() {
            return condition;
        }

        Double getTemp() {
            return temp;
        }
    }

    @SerializedName("day")
    private Temperature temperature;

    @SerializedName("astro")
    private Astro astro;


    @SerializedName("date")
    private Date date;

    public String getTemp() {
        return temperature.getTemp().toString();
    }

    public Astro getAstro() {
        return astro;
    }

    public Condition getCondition() {
        return temperature.getCondition();
    }

    public Date getDate() {
        return date;
    }

    public String getFormattedDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        String[] shortMonths = new DateFormatSymbols().getShortMonths();
        String month = shortMonths[cal.get(Calendar.MONTH)];
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return "" + day + " " + month;
    }
}
