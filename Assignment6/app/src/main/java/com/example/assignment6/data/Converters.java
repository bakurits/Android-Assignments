package com.example.assignment6.data;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class Converters {
    @TypeConverter
    public static List<Item> fromString(String value) {
        Type listType = new TypeToken<List<Item>>() {
        }.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromList(List<Item> list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }
}
