package com.nandy.weatherapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by yana on 20.10.17.
 */

public class Forecast {

    @SerializedName("forecastday")
    private List<ForecastDay> forecasts;

    public List<ForecastDay> getForecasts() {
        return forecasts;
    }
}
