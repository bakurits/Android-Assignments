package com.example.assignment2;

import android.app.ProgressDialog;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.assignment2.Models.Astro;
import com.example.assignment2.Models.Weather;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment {
    private WeatherApi api;
    private RecyclerView recyclerView;

    private TextView cityTextView;
    private TextView perceivedTextView;
    private TextView precipitationTextView;
    private TextView humidityTextView;
    private TextView windSpeedInfoTextView;
    private TextView dayAndNightTextView;
    private TextView temperatureTextView;


    private TextView topDateDay;
    private TextView topDateHour;
    private ImageView topGrad;
    private ImageView dayIndicatorIcon;
    private ImageView drop;
    private ImageView drops;
    private ImageView flag;

    private ProgressBar progressBar;


    private static final String COUNTRY_PARAM = "countryParam";

    private String country;


    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MainFragment.
     */
    public static MainFragment newInstance(String country) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(COUNTRY_PARAM, country);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            country = getArguments().getString(COUNTRY_PARAM);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        progressBar = view.findViewById(R.id.progressBar_cyclic);
        cityTextView = view.findViewById(R.id.cityName);
        perceivedTextView = view.findViewById(R.id.perceivedInfo);
        precipitationTextView = view.findViewById(R.id.precipitationInfo);
        humidityTextView = view.findViewById(R.id.humidityInfo);
        windSpeedInfoTextView = view.findViewById(R.id.windSpeedInfo);
        dayAndNightTextView = view.findViewById(R.id.dayAndNightInfo);
        temperatureTextView = view.findViewById(R.id.temperatureInfo);
        dayIndicatorIcon = view.findViewById(R.id.imageView2);
        drop = view.findViewById(R.id.imageView3);
        drops = view.findViewById(R.id.imageView4);
        flag = view.findViewById(R.id.imageView5);
        topGrad = view.findViewById(R.id.imageView);
        progressBar.setVisibility(View.VISIBLE);
        topDateDay = view.findViewById(R.id.dayInfo);
        topDateHour = view.findViewById(R.id.timeInfo);


        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        initializeRetrofit();

        getForecast(country);
        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    private void getForecast(String country) {
        api.getWeather(country, "1b4fcf51354d41179f595933190704", 10).enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(@NonNull Call<Weather> call, @NonNull Response<Weather> response) {

                if (response.isSuccessful()) {
                    assert response.body() != null;
                    fillData(response.body());
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Weather> call, @NonNull Throwable t) {
                Log.d("response_TAG", t.getMessage());
            }
        });
    }


    private void initializeRetrofit() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.apixu.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        api = retrofit.create(WeatherApi.class);
    }

    private void fillData(Weather data) {

        setupTheme(data.getCurrent().isDay());

        String city = data.getLocation().getCity();
        String perceived = data.getCurrent().getPerceived();
        String temperature = data.getCurrent().getTemp();
        String humidity = data.getCurrent().getHumidity();
        String wind = data.getCurrent().getMaxWind();
        Astro astro = new Astro();
        if (data.getForecast().getForecast().size() > 0)
            astro = data.getForecast().getForecast().get(0).getAstro();

        String sunRiseAndSet = astro.getSunrise() + " " + astro.getSunset();
        String precip = data.getCurrent().getPrecip();

        setTopTime(data.getLocation().getLocalTime());

        cityTextView.setText(city);
        perceivedTextView.setText(perceived);
        temperatureTextView.setText(temperature);
        humidityTextView.setText(humidity);
        windSpeedInfoTextView.setText(wind);
        dayAndNightTextView.setText(sunRiseAndSet);
        precipitationTextView.setText(precip);

        Glide.with(dayIndicatorIcon.getContext()).load(data.getCurrent().getCondition().getIconUrl())
                .into(dayIndicatorIcon);


        RecyclerView.Adapter mAdapter = new RecyclerViewAdapter(data.getForecast().getForecast());

        recyclerView.setAdapter(mAdapter);


    }


    private void setupTheme(boolean isDay) {


        if (isDay) {
            flag.setImageTintList(null);
            drop.setImageTintList(null);
            drops.setImageTintList(null);
            dayIndicatorIcon.setImageResource(R.drawable.ic_sun);
            topGrad.setImageResource(R.drawable.top_gradiental_shape_day);

        } else {
            int colorInt = ContextCompat.getColor(flag.getContext(), R.color.colorDarkMode);
            flag.setImageTintList(ColorStateList.valueOf(colorInt));
            drop.setImageTintList(ColorStateList.valueOf(colorInt));
            drops.setImageTintList(ColorStateList.valueOf(colorInt));
            topGrad.setImageResource(R.drawable.top_gradiental_shape_night);
            dayIndicatorIcon.setImageResource(R.drawable.ic_moon);
        }

    }

    private void setTopTime(String tm) {
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.US).parse(tm);
            String topDate = new SimpleDateFormat("EEEE dd", Locale.US).format(date);
            String topTime = new SimpleDateFormat("hh:mm a", Locale.US).format(date);
            topDateDay.setText(topDate);
            topDateHour.setText(topTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
