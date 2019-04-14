package com.example.assignment2;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.example.assignment2.Models.DayInfo;
import com.example.assignment2.Models.Forecast;

import java.util.List;
import java.util.Locale;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private List<DayInfo> forecast;

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView day, weather;
        ImageView icon;

        MyViewHolder(View view) {
            super(view);
            day = (TextView) view.findViewById(R.id.row_day);
            weather = (TextView) view.findViewById(R.id.row_weather);
            icon = (ImageView) view.findViewById(R.id.row_icon);
        }
    }


    RecyclerViewAdapter(List<DayInfo> forecast) {
        this.forecast = forecast;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DayInfo day = forecast.get(position);
        holder.day.setText(day.getFormattedDate());
        holder.weather.setText(day.getTemp() + "\u2103");
        Glide.with(holder.itemView.getContext()).load(day.getCondition().getIconUrl())
                .into(holder.icon);



    }

    @Override
    public int getItemCount() {
        return forecast.size();
    }
}