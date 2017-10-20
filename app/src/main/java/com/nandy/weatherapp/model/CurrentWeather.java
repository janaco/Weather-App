package com.nandy.weatherapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by yana on 20.10.17.
 */

public class CurrentWeather {

    @SerializedName("last_updated_epoch")
    private long lastUpdatedTime;
    @SerializedName("temp_c")
    private int temperatureCelsius;
    @SerializedName("temp_f")
    private int temperatureFahrenheit;
    @SerializedName("is_day")
    private int day;
    @SerializedName("condition")
    private Condition condition;
    @SerializedName("wind_mph")
    private float windSpeedInMiles;
    @SerializedName("wind_kph")
    private float windSpeedInKilomiters;
    @SerializedName("wind_degree")
    private float windDegree;
    @SerializedName("wind_dir")
    private WindDirection windDirection;
    @SerializedName("pressure_mb")
    private int pressureMillibars;
    @SerializedName("precip_mm")
    private int precipMm; //Precipitation amount in millimeters
    @SerializedName("humidity")
    private int humidity;
    @SerializedName("cloud")
    private int cloud;
    @SerializedName("feelslike_c")
    private float feelsLikeCelsius;
    @SerializedName("feelslike_f")
    private float feelsLikeFarangeith;
    @SerializedName("vis_km")
    private float visKm;
    @SerializedName("vis_miles")
    private float visMiles;

    public long getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public int getTemperatureCelsius() {
        return temperatureCelsius;
    }

    public int getTemperatureFahrenheit() {
        return temperatureFahrenheit;
    }

    public int getDay() {
        return day;
    }

    public Condition getCondition() {
        return condition;
    }

    public float getWindSpeedInMiles() {
        return windSpeedInMiles;
    }

    public float getWindSpeedInKilomiters() {
        return windSpeedInKilomiters;
    }

    public float getWindDegree() {
        return windDegree;
    }

    public WindDirection getWindDirection() {
        return windDirection;
    }

    public int getPressureMillibars() {
        return pressureMillibars;
    }

    public int getPrecipMm() {
        return precipMm;
    }

    public int getHumidity() {
        return humidity;
    }

    public int getCloud() {
        return cloud;
    }

    public float getFeelsLikeCelsius() {
        return feelsLikeCelsius;
    }

    public float getFeelsLikeFarangeith() {
        return feelsLikeFarangeith;
    }

    public float getVisKm() {
        return visKm;
    }

    public float getVisMiles() {
        return visMiles;
    }
}
