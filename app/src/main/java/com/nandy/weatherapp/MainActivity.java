package com.nandy.weatherapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.nandy.weatherapp.api.WeatherService;
import com.nandy.weatherapp.model.Weather;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new WeatherService().getForecast("Lviv", 2)
                .subscribe(new SingleObserver<Weather>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("WEATHER_", "onSubscribe");
                    }

                    @Override
                    public void onSuccess(Weather weather) {
                        Log.d("WEATHER_", "onSuccess");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("WEATHER_", "onError: " + e);

                    }
                });
    }
}
