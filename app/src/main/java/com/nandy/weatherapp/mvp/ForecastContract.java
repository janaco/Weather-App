package com.nandy.weatherapp.mvp;

import android.support.annotation.StringRes;

import com.nandy.weatherapp.adapter.ForecastsAdapter;

/**
 * Created by yana on 21.10.17.
 */

public class ForecastContract {

    public interface View{

        void setPresenter(Presenter presenter);

        void setLocationName(String name);

        void setCurrentDate(long timestamp);

        void setCurrentTemperature(int temperature);

        void setMaxTemperature(int maxTemperature);

        void setMinTemperature(int minTemperature);

        void setConditionIcon(String iconUrl);

        void setFeelsLikeTemperature(float temperature);

        void setChanceOfRain(int chanceOfRain);

        void setWindSpeed(float windSpeed);

        void setSunsetTime(long timestamp);

        void setCondition(String condition);

        void setForecastsAdapter(ForecastsAdapter adapter);

        void showProgress();

        void showContent();

        void setProgressText(@StringRes int resId);
    }

    public interface Presenter{

        void start();

        void destroy();

        void startEventListening();

        void stopEventListening();

    }
}
