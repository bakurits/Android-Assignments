package com.example.assignment2.Models;

import com.google.gson.annotations.SerializedName;

public class Condition {

    @SerializedName("icon")
    private String iconUrl;

    public String getIconUrl() {
        return "http:" + iconUrl;
    }
}
