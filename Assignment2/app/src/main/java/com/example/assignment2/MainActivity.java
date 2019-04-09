package com.example.assignment2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        ArrayList<WeatherByDay> arr = new ArrayList<>();
        arr.add(new WeatherByDay("2019-04-09", 14.4, "http://cdn.apixu.com/weather/64x64/day/176.png"));
        arr.add(new WeatherByDay("2019-04-09", 14.4, "http://cdn.apixu.com/weather/64x64/day/176.png"));
        arr.add(new WeatherByDay("2019-04-09", 14.4, "http://cdn.apixu.com/weather/64x64/day/176.png"));
        arr.add(new WeatherByDay("2019-04-09", 14.4, "http://cdn.apixu.com/weather/64x64/day/176.png"));
        arr.add(new WeatherByDay("2019-04-09", 14.4, "http://cdn.apixu.com/weather/64x64/day/176.png"));
        arr.add(new WeatherByDay("2019-04-09", 14.4, "http://cdn.apixu.com/weather/64x64/day/176.png"));
        arr.add(new WeatherByDay("2019-04-09", 14.4, "http://cdn.apixu.com/weather/64x64/day/176.png"));
        arr.add(new WeatherByDay("2019-04-09", 14.4, "http://cdn.apixu.com/weather/64x64/day/176.png"));
        arr.add(new WeatherByDay("2019-04-09", 14.4, "http://cdn.apixu.com/weather/64x64/day/176.png"));
        arr.add(new WeatherByDay("2019-04-09", 14.4, "http://cdn.apixu.com/weather/64x64/day/176.png"));
        arr.add(new WeatherByDay("2019-04-09", 14.4, "http://cdn.apixu.com/weather/64x64/day/176.png"));
        arr.add(new WeatherByDay("2019-04-09", 14.4, "http://cdn.apixu.com/weather/64x64/day/176.png"));
        arr.add(new WeatherByDay("2019-04-09", 14.4, "http://cdn.apixu.com/weather/64x64/day/176.png"));
        arr.add(new WeatherByDay("2019-04-09", 14.4, "http://cdn.apixu.com/weather/64x64/day/176.png"));
        arr.add(new WeatherByDay("2019-04-09", 14.4, "http://cdn.apixu.com/weather/64x64/day/176.png"));
        arr.add(new WeatherByDay("2019-04-09", 14.4, "http://cdn.apixu.com/weather/64x64/day/176.png"));
        arr.add(new WeatherByDay("2019-04-09", 14.4, "http://cdn.apixu.com/weather/64x64/day/176.png"));
        arr.add(new WeatherByDay("2019-04-09", 14.4, "http://cdn.apixu.com/weather/64x64/day/176.png"));

        mAdapter = new RecyclerViewAdapter(arr);
        recyclerView.setAdapter(mAdapter);
    }
}
