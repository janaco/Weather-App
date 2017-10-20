package com.nandy.weatherapp.api;

import com.nandy.weatherapp.model.Location;
import com.nandy.weatherapp.model.Weather;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by yana on 20.10.17.
 */

public class WeatherService extends RetrofitService {

    private static String KEY = "key";
    private static String QWERY = "q";
    private static String DAYS = "days";

    public Single<Weather> getForecast(String qwery, int days) {

        Map<String, String> queryMap = new HashMap<>();
        queryMap.put(KEY, Api.KEY);
        queryMap.put(QWERY, qwery);
        queryMap.put(DAYS, String.valueOf(days));

        return getRetrofitService().getForecast(queryMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    public Single<Weather> getForecast(android.location.Location location, int days) {

        Map<String, String> queryMap = new HashMap<>();
        queryMap.put(KEY, Api.KEY);
        queryMap.put(QWERY, String.format("%f,%f", location.getLatitude(), location.getLatitude()));
        queryMap.put(DAYS, String.valueOf(days));

        return getRetrofitService().getForecast(queryMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<List<Location>> search(String qwery) {

        Map<String, String> queryMap = new HashMap<>();
        queryMap.put(KEY, Api.KEY);
        queryMap.put(QWERY, qwery);

        return getRetrofitService().search(queryMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


}
