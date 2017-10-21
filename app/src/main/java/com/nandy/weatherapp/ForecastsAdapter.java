package com.nandy.weatherapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nandy.weatherapp.model.Day;
import com.nandy.weatherapp.model.ForecastDay;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yana on 21.10.17.
 */

public class ForecastsAdapter extends RecyclerView.Adapter<ForecastsAdapter.ViewHolder> {

    private final List<ForecastDay> forecasts;

    public ForecastsAdapter(List<ForecastDay> forecasts){
        this.forecasts = forecasts;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_forecast, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        ForecastDay forecast = forecasts.get(position);
        Day day = forecast.getDay();
        Date date = forecast.getDate();
        holder.setDay(date);
        holder.setDate(date);

        holder.setConditionIcon(day.getCondition().getIconUrl());
        holder.setMinTemperature((int) day.getMintempCelciun(), (int) day.getMaxtempCelciun());

    }

    @Override
    public int getItemCount() {
        return forecasts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.day)
        TextView textDay;
        @BindView(R.id.date)
        TextView textDate;
        @BindView(R.id.temp)
        TextView temperatureText;
        @BindView(R.id.chance_of_rain)
        TextView chanceOfRainText;
        @BindView(R.id.icon)
        ImageView conditionIcon;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void setDay(Date day){
            textDay.setText(new SimpleDateFormat("EEE").format(day));
        }


        void setDate(Date day){
            textDate.setText(new SimpleDateFormat("MM/dd").format(day));
        }

        void setMinTemperature(int min, int max){
            temperatureText.setText(String.format("%d\u00B0/%d\u00B0", min, max));
        }

        void setChanceOfRain(int percentage) {
           chanceOfRainText.setText(String.format("%d%", percentage));
        }

        void setConditionIcon(String url){
            Picasso.with(conditionIcon.getContext()).load(url).into(conditionIcon);
        }

    }
}
