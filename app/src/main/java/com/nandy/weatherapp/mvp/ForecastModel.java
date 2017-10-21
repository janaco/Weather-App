package com.nandy.weatherapp.mvp;

import com.nandy.weatherapp.api.WeatherService;
import com.nandy.weatherapp.model.Weather;

import io.reactivex.Single;

/**
 * Created by yana on 21.10.17.
 */

public class ForecastModel {


    public Single<Weather>  getForecast(String name, int days){

        return new WeatherService().getForecast(name, days);
    }
}
