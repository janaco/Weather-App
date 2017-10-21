package com.nandy.weatherapp.api;

import com.nandy.weatherapp.model.Location;
import com.nandy.weatherapp.model.Weather;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by yana on 20.10.17.
 */

public interface Api {

    String BASE_URL = "https://api.apixu.com/v1/";
    String KEY = "e13c74da798f4ac7821180743172010";


    @GET("forecast.json?")
    Single<Weather> getForecast(@QueryMap Map<String, String> params);

    @GET("search.json?")
    Observable<List<Location>> search(@QueryMap Map<String, String> params);
}
