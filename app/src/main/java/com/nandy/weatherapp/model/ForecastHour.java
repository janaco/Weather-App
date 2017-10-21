package com.nandy.weatherapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by yana on 20.10.17.
 */

public class ForecastHour {

    @SerializedName("time_epoch")
    private long time;
    @SerializedName("temp_c")
    private float tempCelsius;
    @SerializedName("is_day")
    private int day;
    @SerializedName("condition")
    private Condition condition;
    @SerializedName("wind_kph")
    private float windSpeedInKilometers;
    @SerializedName("pressure_mb")
    private int pressureMillibars;
    @SerializedName("cloud")
    private int cloud;
    @SerializedName("feelslike_c")
    private float feelslikeCelsius;
    @SerializedName("will_it_rain")
    private int rain;
    @SerializedName("chance_of_rain")
    private int chanceOfRain;
    @SerializedName("will_it_snow")
    private int will_it_snow;
    @SerializedName("chance_of_snow")
    private int chanceOfSnow;
    @SerializedName("vis_km")
    private float visibilityInKm;

    public long getTime() {
        return time;
    }

    public float getTempCelsius() {
        return tempCelsius;
    }

    public int getDay() {
        return day;
    }

    public Condition getCondition() {
        return condition;
    }

    public float getWindSpeedInKilometers() {
        return windSpeedInKilometers;
    }

    public int getPressureMillibars() {
        return pressureMillibars;
    }

    public int getCloud() {
        return cloud;
    }

    public float getFeelslikeCelsius() {
        return feelslikeCelsius;
    }

    public int getRain() {
        return rain;
    }

    public int getChanceOfRain() {
        return chanceOfRain;
    }

    public int getWill_it_snow() {
        return will_it_snow;
    }

    public int getChanceOfSnow() {
        return chanceOfSnow;
    }

    public float getVisibilityInKm() {
        return visibilityInKm;
    }
}
