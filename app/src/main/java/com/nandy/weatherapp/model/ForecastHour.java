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
    @SerializedName("temp_f")
    private float tempFahrenheit;
    @SerializedName("is_day")
    private int day;
    @SerializedName("condition")
    private Condition condition;
    @SerializedName("wind_mph")
    private float windSpeedMiles;
    @SerializedName("wind_kph")
    private float windSpeedInKilometers;
    @SerializedName("wind_degree")
    private int windDegree;
    @SerializedName("wind_dir")
    private WindDirection windDirection;
    @SerializedName("pressure_mb")
    private int pressureMillibars;
    @SerializedName("precip_mm")
    private int precipMm;
    @SerializedName("humidity")
    private int humidity;
    @SerializedName("cloud")
    private int cloud;
    @SerializedName("feelslike_c")
    private float feelslikeCelsius;
    @SerializedName("feelslike_f")
    private float feelslikeFahrenheit;
    @SerializedName("windchill_c")
    private float windchillCelsius;
    @SerializedName("windchill_f")
    private float windchillFahrenheit;
    @SerializedName("heatindex_c")
    private float heatindexCelsius;
    @SerializedName("heatindex_f")
    private float heatindexFahrenheit;
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
    @SerializedName("vis_miles")
    private int visibilityMiles;

    public long getTime() {
        return time;
    }

    public float getTempCelsius() {
        return tempCelsius;
    }

    public float getTempFahrenheit() {
        return tempFahrenheit;
    }

    public int getDay() {
        return day;
    }

    public Condition getCondition() {
        return condition;
    }

    public float getWindSpeedMiles() {
        return windSpeedMiles;
    }

    public float getWindSpeedInKilometers() {
        return windSpeedInKilometers;
    }

    public int getWindDegree() {
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

    public float getFeelslikeCelsius() {
        return feelslikeCelsius;
    }

    public float getFeelslikeFahrenheit() {
        return feelslikeFahrenheit;
    }

    public float getWindchillCelsius() {
        return windchillCelsius;
    }

    public float getWindchillFahrenheit() {
        return windchillFahrenheit;
    }

    public float getHeatindexCelsius() {
        return heatindexCelsius;
    }

    public float getHeatindexFahrenheit() {
        return heatindexFahrenheit;
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

    public int getVisibilityMiles() {
        return visibilityMiles;
    }
}
