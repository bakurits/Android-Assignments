package com.example.assignment2.Models;

import com.google.gson.annotations.SerializedName;

public class Country {
    @SerializedName("name")
    private String name;

    public String getName() {
        return name;
    }
}
