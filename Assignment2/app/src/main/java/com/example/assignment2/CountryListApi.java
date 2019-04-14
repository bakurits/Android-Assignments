package com.example.assignment2;

import com.example.assignment2.Models.Country;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CountryListApi {
    @GET("rest/v2/all?fields=name")
    Call<List<Country>> listCountries();
}