package com.nandy.weatherapp.mvp.model;

import android.location.Location;

import com.nandy.weatherapp.api.WeatherService;
import com.nandy.weatherapp.model.Weather;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by yana on 21.10.17.
 */

public class ForecastModel {

    private int days = 10;

    public void setDays(int days) {
        this.days = days;
    }

    public Single<Weather> getForecast(String name) {

        return new WeatherService().getForecast(name, days)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    public Single<Weather> getForecast(Location location) {

        return new WeatherService().getForecast(location, days)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
