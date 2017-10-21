package com.nandy.weatherapp.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.common.api.Status;
import com.nandy.weatherapp.ForecastsAdapter;
import com.nandy.weatherapp.R;
import com.nandy.weatherapp.mvp.ForecastContract;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yana on 21.10.17.
 */

public class ForecastFragment extends Fragment implements ForecastContract.View {

    @BindView(R.id.name)
    TextView nameText;
    @BindView(R.id.date)
    TextView dateText;
    @BindView(R.id.condition_icon)
    ImageView conditionIcon;
    @BindView(R.id.condition)
    TextView conditionText;
    @BindView(R.id.current_temp)
    TextView currentTemperatureText;
    @BindView(R.id.max_temp)
    TextView maxTemperatureText;
    @BindView(R.id.min_temp)
    TextView minTemperatureText;
    @BindView(R.id.feels_like)
    TextView feelsLikeText;
    @BindView(R.id.chance_of_rain)
    TextView changeOfRainText;
    @BindView(R.id.wind_speed)
    TextView windSpeedText;
    @BindView(R.id.sunset_time)
    TextView sunsetTimeText;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private ForecastContract.Presenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_forecast, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        presenter.start();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.destroy();
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.startEventListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.stopEventListening();
    }

    @Override
    public void setPresenter(ForecastContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @OnClick(R.id.search)
    void onSearchClick() {

        getActivity().getSupportFragmentManager().beginTransaction()
                .add(R.id.container, new SearchFragment(), SearchFragment.class.getSimpleName())
                .addToBackStack(SearchFragment.class.getSimpleName())
                .commit();
    }

    @OnClick(R.id.gps)
    void onGpsClick() {

    }

    @Override
    public void setLocationName(String name) {
        nameText.setText(name);
    }

    @Override
    public void setCurrentDate(long timestamp) {
        dateText.setText(new SimpleDateFormat("EEE, MMM d", Locale.ENGLISH).format(new Date(timestamp)));
    }

    @Override
    public void setCurrentTemperature(int temperature) {

        currentTemperatureText.setText(String.format("%d\u2103", temperature));
    }

    @Override
    public void setMaxTemperature(int maxTemperature) {
        maxTemperatureText.setText(String.format("%d\u2103", maxTemperature));

    }

    @Override
    public void setMinTemperature(int minTemperature) {
        minTemperatureText.setText(String.format("%d\u2103", minTemperature));
    }

    @Override
    public void setConditionIcon(String iconUrl) {

    }

    @Override
    public void setFeelsLikeTemperature(float temperature) {
//        currentTemperatureText.setText(String.format("%f\u2103", temperature));
        feelsLikeText.setText(temperature + "\u2103");
    }

    @Override
    public void setChanceOfRain(int chanceOfRain) {
//        currentTemperatureText.setText(String.format("%d%", chanceOfRain));
        changeOfRainText.setText(chanceOfRain + "%");
    }

    @Override
    public void setWindSpeed(float windSpeed) {
//        currentTemperatureText.setText(String.format("%f %s",windSpeed, getString(R.string.km_per_hour)));
        windSpeedText.setText(windSpeed + " " + getString(R.string.km_per_hour));

    }

    @Override
    public void setSunsetTime(long timestamp) {
        dateText.setText(new SimpleDateFormat("HH:mm", Locale.ENGLISH).format(new Date(timestamp)));

    }

    @Override
    public void setCondition(String condition) {
        conditionText.setText(condition);
    }

    @Override
    public void setForecastsAdapter(ForecastsAdapter adapter) {
        recyclerView.setAdapter(adapter);
    }

}
