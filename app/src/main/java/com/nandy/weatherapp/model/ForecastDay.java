package com.nandy.weatherapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by yana on 20.10.17.
 */

public class ForecastDay {

    @SerializedName("date_epoch")
    private long time;
    @SerializedName("day")
    private Day day;
    @SerializedName("astro")
    private Astro astro;
    @SerializedName("hour")
    private ForecastHour[] hours;

    public long getTime() {
        return time;
    }

    public Day getDay() {
        return day;
    }

    public Astro getAstro() {
        return astro;
    }

    public ForecastHour[] getHours() {
        return hours;
    }
}
