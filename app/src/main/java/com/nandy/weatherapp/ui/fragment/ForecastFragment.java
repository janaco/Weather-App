package com.nandy.weatherapp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nandy.weatherapp.R;
import com.nandy.weatherapp.adapter.ForecastsAdapter;
import com.nandy.weatherapp.mvp.ForecastContract;
import com.squareup.picasso.Picasso;

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
    @BindView(R.id.visibility)
    TextView visibilityText;
    @BindView(R.id.wind_speed)
    TextView windSpeedText;
    @BindView(R.id.clouds)
    TextView cloudsText;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.progress)
    View progressLayout;
    @BindView(R.id.content)
    View contentLayout;
    @BindView(R.id.progress_text)
    TextView progressText;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.toolbar)
    View toolbarLayout;

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
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), LinearLayoutManager.HORIZONTAL));
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

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_in, R.anim.slide_out);

        transaction
                .add(R.id.container, new SearchFragment(), SearchFragment.class.getSimpleName())
                .addToBackStack(SearchFragment.class.getSimpleName())
                .commit();
    }

    @OnClick(R.id.gps)
    void onGpsClick() {
        presenter.requestCurrentForecast();
    }

    @Override
    public void setLocationName(String name) {
        nameText.setText(name);
    }

    @Override
    public void setCurrentDate(Date date) {
        dateText.setText(new SimpleDateFormat("EEE, MMM d", Locale.ENGLISH).format(date));
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
        Picasso.with(getContext()).load(iconUrl).into(conditionIcon);
    }

    @Override
    public void setFeelsLikeTemperature(float temperature) {
        feelsLikeText.setText(String.valueOf(temperature).concat("\u2103"));
    }

    @Override
    public void setVisibility(int visibility) {
        visibilityText.setText(String.format("%d %s", visibility, getString(R.string.km)));
    }

    @Override
    public void setWindSpeed(float windSpeed) {
        windSpeedText.setText(String.valueOf(windSpeed).concat(" ").concat(getString(R.string.km_per_hour)));

    }

    @Override
    public void setCloudsCover(int persentage) {
        cloudsText.setText(String.format("%d%s", persentage, "%"));
    }

    @Override
    public void setCondition(String condition) {
        conditionText.setText(condition);
    }

    @Override
    public void setForecastsAdapter(ForecastsAdapter adapter) {
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showProgress() {
        contentLayout.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        progressText.setText("");
        progressLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void showContent() {
        progressLayout.setVisibility(View.GONE);
        contentLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void setProgressText(int resId) {
        progressText.setText(resId);
    }

    @Override
    public void showError(String message) {
        contentLayout.setVisibility(View.GONE);
        progressLayout.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        progressText.setText(message);
    }

    @Override
    public void showError(int resId) {
        showError(getString(resId));
    }

}
