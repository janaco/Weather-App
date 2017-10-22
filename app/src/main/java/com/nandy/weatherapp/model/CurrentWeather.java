package com.nandy.weatherapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by yana on 20.10.17.
 */

public class CurrentWeather {

    @SerializedName("temp_c")
    private float temperatureCelsius;
    @SerializedName("condition")
    private Condition condition;
    @SerializedName("wind_kph")
    private float windSpeedInKilomiters;
    @SerializedName("cloud")
    private int cloud;
    @SerializedName("feelslike_c")
    private float feelsLikeCelsius;
    @SerializedName("vis_km")
    private float visKm;


    public float getTemperatureCelsius() {
        return temperatureCelsius;
    }


    public Condition getCondition() {
        return condition;
    }


    public float getWindSpeedInKilomiters() {
        return windSpeedInKilomiters;
    }

    public int getCloud() {
        return cloud;
    }

    public float getFeelsLikeCelsius() {
        return feelsLikeCelsius;
    }

    public float getVisKm() {
        return visKm;
    }

}
