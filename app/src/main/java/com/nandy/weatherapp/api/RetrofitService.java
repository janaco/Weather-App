package com.nandy.weatherapp.api;

import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yana on 20.10.17.
 */

public class RetrofitService {


    private static Retrofit getRetrofit() {

        return new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .client(new OkHttpClient.Builder().build())
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

    }

    private static Api getService() {
        return getRetrofit().create(Api.class);
    }

}
