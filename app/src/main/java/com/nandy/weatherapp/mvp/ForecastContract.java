package com.nandy.weatherapp.mvp;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.support.annotation.StringRes;

import com.nandy.weatherapp.adapter.ForecastsAdapter;

import java.util.Date;

/**
 * Created by yana on 21.10.17.
 */

public class ForecastContract {

    public interface View {

        void setPresenter(Presenter presenter);

        void setLocationName(String name);

        void setCurrentDate(Date date);

        void setCurrentTemperature(int temperature);

        void setMaxTemperature(int maxTemperature);

        void setMinTemperature(int minTemperature);

        void setConditionIcon(String iconUrl);

        void setFeelsLikeTemperature(float temperature);

        void setVisibility(int chanceOfRain);

        void setWindSpeed(float windSpeed);

        void setCloudsCover(int persentage);

        void setCondition(String condition);

        void setForecastsAdapter(ForecastsAdapter adapter);

        void showProgress();

        void showContent();

        void setProgressText(@StringRes int resId);

        void showError(String message);

        void showError(@StringRes int resId);
    }

    public interface Presenter {

        void start();

        void destroy();

        void startEventListening();

        void stopEventListening();

        void requestCurrentForecast();

    }
}
