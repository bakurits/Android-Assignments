package com.example.assignment2.Models;

import com.google.gson.annotations.SerializedName;

public class Condition {

    @SerializedName("icon")
    private String iconUrl;

    public String getIconUrl() {
        if (iconUrl == null) return "https://static.thenounproject.com/png/1439126-200.png";
        return "http:" + iconUrl;
    }
}
