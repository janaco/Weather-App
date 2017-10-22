package com.nandy.weatherapp.mvp;

import android.content.pm.PackageManager;

import com.nandy.weatherapp.R;
import com.nandy.weatherapp.adapter.ForecastsAdapter;
import com.nandy.weatherapp.eventbus.CurrentLocationEvent;
import com.nandy.weatherapp.eventbus.NetworkConnectionEvent;
import com.nandy.weatherapp.eventbus.PermissionResultEvent;
import com.nandy.weatherapp.eventbus.SearchResultEvent;
import com.nandy.weatherapp.model.Condition;
import com.nandy.weatherapp.model.CurrentWeather;
import com.nandy.weatherapp.model.Day;
import com.nandy.weatherapp.model.ForecastDay;
import com.nandy.weatherapp.model.Weather;
import com.nandy.weatherapp.eventbus.ActivityResultEvent;
import com.nandy.weatherapp.mvp.model.ForecastModel;
import com.nandy.weatherapp.mvp.model.LocationModel;

import org.apache.http.conn.ConnectTimeoutException;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.net.UnknownHostException;
import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

/**
 * Created by yana on 21.10.17.
 */

public class ForecastPresenter implements ForecastContract.Presenter {

    private ForecastContract.View view;
    private ForecastModel forecastModel;
    private LocationModel locationModel;

    private Disposable forecastSubscription;

    public ForecastPresenter(ForecastContract.View view) {
        this.view = view;
    }

    public void setForecastModel(ForecastModel forecastModel) {
        this.forecastModel = forecastModel;
    }

    public void setLocationModel(LocationModel locationModel) {
        this.locationModel = locationModel;
    }

    @Override
    public void start() {
        locationModel.requestLocationUpdate();
    }

    @Override
    public void destroy() {

        if (forecastSubscription != null && !forecastSubscription.isDisposed()) {
            forecastSubscription.dispose();
        }
    }

    @Override
    public void requestCurrentForecast() {
        view.showProgress();
        view.setProgressText(R.string.fetching_location);
        locationModel.requestLocationUpdate();
    }

    @Override
    public void startEventListening() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void stopEventListening() {
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSearchResultEvent(SearchResultEvent event) {
        forecastModel.getForecast(event.getQwery())
                .subscribe(getWeatherObserver());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLocationEvent(CurrentLocationEvent event) {
        if (event.isSuccess()) {
            if (event.getLocation() != null) {

                forecastModel.getForecast(event.getLocation())
                        .subscribe(getWeatherObserver());
            } else {
                view.showError(R.string.failed_to_determine_location);
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onActivityResultEvent(ActivityResultEvent event) {
        if (event.getRequestCode() == LocationModel.REQUEST_CHECK_SETTINGS) {
            locationModel.onActivityResult(event.getResultCode());
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onNetworkStateChanged(NetworkConnectionEvent event) {
        if (event.isNetworkEnabled()) {
            requestCurrentForecast();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPermissionResultEvent(PermissionResultEvent event) {

        if (event.getRequestCode() == LocationModel.REQUEST_LOCATION_PERMISSIONS && event.getGrantResults() != null) {
            int[] grantResults = event.getGrantResults();
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                requestCurrentForecast();
            }
        }
    }

    private SingleObserver<Weather> getWeatherObserver() {
        return new SingleObserver<Weather>() {
            @Override
            public void onSubscribe(Disposable d) {
                forecastSubscription = d;
                view.showProgress();
                view.setProgressText(R.string.loading_forecast);
            }

            @Override
            public void onSuccess(Weather weather) {
                view.setLocationName(weather.getLocation().getName());

                CurrentWeather currentWeather = weather.getCurrent();
                Condition condition = currentWeather.getCondition();
                view.setCondition(condition.getText());
                view.setConditionIcon(condition.getIconUrl());
                view.setFeelsLikeTemperature(currentWeather.getFeelsLikeCelsius());
                view.setCurrentTemperature((int) currentWeather.getTemperatureCelsius());
                view.setWindSpeed(currentWeather.getWindSpeedInKilomiters());
                view.setVisibility((int) currentWeather.getVisKm());
                view.setCloudsCover(currentWeather.getCloud());

                List<ForecastDay> forecasts = weather.getForecast().getForecasts();
                ForecastDay forecast = forecasts.get(0);
                Day day = forecast.getDay();
                view.setCurrentDate(forecast.getDate());
                view.setMaxTemperature((int) day.getMaxtempCelciun());
                view.setMinTemperature((int) day.getMintempCelciun());
                view.setForecastsAdapter(new ForecastsAdapter(forecasts));
                view.showContent();
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();

                if (e instanceof UnknownHostException || e instanceof ConnectTimeoutException) {
                    view.showError(R.string.failed_to_connect);
                    EventBus.getDefault().post(new NetworkConnectionEvent(false));
                } else {
                    view.showError(e.getMessage());
                }
            }
        };
    }

}
