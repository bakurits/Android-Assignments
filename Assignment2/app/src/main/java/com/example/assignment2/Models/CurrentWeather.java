package com.example.assignment2.Models;

import com.google.gson.annotations.SerializedName;

public class CurrentWeather {
    @SerializedName("temp_c")
    private Double temp;

    @SerializedName("wind_kph")
    private Double maxWind;

    @SerializedName("humidity")
    private Double humidity;

    @SerializedName("precip_mm")
    private Double precip;

    @SerializedName("feelslike_c")
    private Double perceived;

    @SerializedName("condition")
    private Condition condition;

    @SerializedName("is_day")
    private Integer isDay;


    public String getHumidity() {
        if (humidity == null) return "Empty";
        return humidity.toString();
    }


    public String getMaxWind() {
        if (maxWind == null) return "Empty";
        return maxWind.toString();
    }

    public String getPrecip() {
        if (precip == null) return "Empty";
        return precip.toString();
    }

    public String getPerceived() {
        if (perceived == null) return "Empty";
        return perceived.toString();
    }

    public String getTemp() {
        if (temp == null) return "Empty";
        return temp.toString();
    }

    public Condition getCondition() {
        if (condition == null) return new Condition();
        return condition;
    }

    public Boolean isDay() {
        return isDay == null || isDay > 0;
    }
}
