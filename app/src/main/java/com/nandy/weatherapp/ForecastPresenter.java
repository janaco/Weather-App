package com.nandy.weatherapp;

import android.util.Log;

import com.nandy.weatherapp.model.Condition;
import com.nandy.weatherapp.model.CurrentWeather;
import com.nandy.weatherapp.model.Weather;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by yana on 21.10.17.
 */

public class ForecastPresenter implements ForecastContract.Presenter {

    private ForecastContract.View view;
    private ForecastModel forecastModel;

    private Disposable forecastSubscription;

    public ForecastPresenter(ForecastContract.View view){
        this.view = view;
    }

    public void setForecastModel(ForecastModel forecastModel) {
        this.forecastModel = forecastModel;
    }

    @Override
    public void start() {
        forecastModel.getForecast("Lviv", 10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Weather>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        forecastSubscription  = d;
                    }

                    @Override
                    public void onSuccess(Weather weather) {
                        Log.d("WEATHER_", "success: " + weather);
                        view.setLocationName(weather.getLocation().getName());
                        displayCurrentWeather(weather.getCurrent());
                        view.setForecastsAdapter(new ForecastsAdapter(weather.getForecast().getForecasts()));
;
                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.d("WEATHER_", "error: " + e);
                    }
                });

    }

    @Override
    public void destroy() {
        if (forecastSubscription != null && !forecastSubscription.isDisposed()){
            forecastSubscription.dispose();
        }
    }

    private void displayCurrentWeather(CurrentWeather weather){

        Condition condition = weather.getCondition();
        view.setCondition(condition.getText());
        view.setConditionIcon(condition.getIcon());

        view.setFeelsLikeTemperature(weather.getFeelsLikeCelsius());
        view.setCurrentTemperature(weather.getTemperatureCelsius());
        view.setWindSpeed(weather.getWindSpeedInKilomiters());

    }
}
