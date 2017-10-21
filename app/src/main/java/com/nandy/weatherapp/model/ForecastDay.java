package com.nandy.weatherapp.model;

import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yana on 20.10.17.
 */

public class ForecastDay {

    @SerializedName("date")
    private String date;
    @SerializedName("day")
    private Day day;
    @SerializedName("astro")
    private Astro astro;
    @SerializedName("hour")
    private ForecastHour[] hours;

    public Date getDate() {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
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
