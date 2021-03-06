package com.nandy.weatherapp.api;

import com.nandy.weatherapp.model.Location;
import com.nandy.weatherapp.model.Weather;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by yana on 20.10.17.
 */

public class WeatherService extends RestService{

    private static String KEY = "key";
    private static String QWERY = "q";
    private static String DAYS = "days";

    public Single<Weather> getForecast(String qwery, int days) {

        Map<String, String> queryMap = new HashMap<>();
        queryMap.put(KEY, Api.KEY);
        queryMap.put(QWERY, qwery);
        queryMap.put(DAYS, String.valueOf(days));

        return getRetrofitService().getForecast(queryMap);
    }


    public Single<Weather> getForecast(android.location.Location location, int days) {

        Map<String, String> queryMap = new HashMap<>();
        queryMap.put(KEY, Api.KEY);
        queryMap.put(QWERY, String.format("%f,%f", location.getLatitude(), location.getLongitude()));
        queryMap.put(DAYS, String.valueOf(days));

        return getRetrofitService().getForecast(queryMap);
    }

    public Observable<List<Location>> search(String qwery) {

        Map<String, String> queryMap = new HashMap<>();
        queryMap.put(KEY, Api.KEY);
        queryMap.put(QWERY, qwery);

        return getRetrofitService().search(queryMap);
    }



}
