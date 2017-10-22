package com.nandy.weatherapp.ui.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import com.nandy.weatherapp.R;
import com.nandy.weatherapp.eventbus.CurrentLocationEvent;
import com.nandy.weatherapp.eventbus.NetworkConnectionEvent;
import com.nandy.weatherapp.eventbus.PermissionRequestEvent;
import com.nandy.weatherapp.eventbus.PermissionResultEvent;
import com.nandy.weatherapp.mvp.ForecastPresenter;
import com.nandy.weatherapp.eventbus.ActivityResultEvent;
import com.nandy.weatherapp.mvp.model.ForecastModel;
import com.nandy.weatherapp.mvp.model.LocationModel;
import com.nandy.weatherapp.ui.fragment.ForecastFragment;
import com.nandy.weatherapp.util.ConnectionUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ForecastFragment fragment = new ForecastFragment();
        ForecastPresenter presenter = new ForecastPresenter(fragment);
        presenter.setForecastModel(new ForecastModel());
        presenter.setLocationModel(new LocationModel(this));

        fragment.setPresenter(presenter);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment, ForecastFragment.class.getSimpleName())
                .addToBackStack(ForecastFragment.class.getSimpleName())
                .commit();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        EventBus.getDefault().post(new ActivityResultEvent(requestCode, resultCode));
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EventBus.getDefault().post(new PermissionResultEvent(requestCode, permissions, grantResults));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLocationEvent(CurrentLocationEvent event) {
        if (!event.isSuccess()) {
            try {
                event.getStatus().startResolutionForResult(this, LocationModel.REQUEST_CHECK_SETTINGS);
            } catch (IntentSender.SendIntentException e) {
                e.printStackTrace();
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onNetworkConnectionEvent(NetworkConnectionEvent event){
        if (!event.isNetworkEnabled()){
            IntentFilter filter = new IntentFilter();
            filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");

            registerReceiver(new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    if (ConnectionUtils.isNetworkEnabled(context)) {
                        EventBus.getDefault().post(new NetworkConnectionEvent(true));
                        context.unregisterReceiver(this);
                    }
                }
            }, filter);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPermissionRequestEvent(PermissionRequestEvent event){
        ActivityCompat.requestPermissions(this, event.getPermissions(), event.getRequestCode());
    }


}
