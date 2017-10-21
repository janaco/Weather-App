package com.nandy.weatherapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by yana on 20.10.17.
 */

public class Day {

    @SerializedName("maxtemp_c")
    private float maxtempCelciun;
    @SerializedName("mintemp_c")
    private float mintempCelciun;
    @SerializedName("avgtemp_c")
    private float avgtempCelsius;
    @SerializedName("maxwind_kph")
    private float maxwindKilometers;
    @SerializedName("avgvis_km")
    private float averageVisibilityInKm;
    @SerializedName("condition")
    private Condition condition;
    @SerializedName("uvIndex")
    private int uvIndex;

    public float getMaxtempCelciun() {
        return maxtempCelciun;
    }

    public float getMintempCelciun() {
        return mintempCelciun;
    }

    public float getAvgtempCelsius() {
        return avgtempCelsius;
    }

    public float getMaxwindKilometers() {
        return maxwindKilometers;
    }

    public float getAverageVisibilityInKm() {
        return averageVisibilityInKm;
    }

    public Condition getCondition() {
        return condition;
    }

    public int getUvIndex() {
        return uvIndex;
    }
}
