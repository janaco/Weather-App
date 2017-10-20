package com.nandy.weatherapp.model;

/**
 * Created by yana on 20.10.17.
 */

public class Weather {

    private Location location;
    private CurrentWeather current;
    private Forecast forecast;

    public Location getLocation() {
        return location;
    }

    public CurrentWeather getCurrent() {
        return current;
    }

    public Forecast getForecast() {
        return forecast;
    }
}
