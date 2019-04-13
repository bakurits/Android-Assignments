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
        return humidity.toString();
    }


    public String getMaxWind() {
        return maxWind.toString();
    }

    public String getPrecip() {
        return precip.toString();
    }

    public String getPerceived() {
        return perceived.toString();
    }

    public String getTemp() {
        return temp.toString();
    }

    public Condition getCondition() {
        return condition;
    }

    public Boolean isDay() {
        return isDay > 0;
    }
}
