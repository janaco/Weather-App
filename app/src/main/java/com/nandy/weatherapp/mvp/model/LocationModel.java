package com.nandy.weatherapp.mvp.model;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.nandy.weatherapp.eventbus.CurrentLocationEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by yana on 21.10.17.
 */

public class LocationModel implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, ResultCallback<LocationSettingsResult> {


    public final static int REQUEST_CHECK_SETTINGS = 2000;

    private GoogleApiClient googleApiClient;

    public LocationModel(Activity activity) {
        if (checkPlayServices(activity)) {
            buildGoogleApiClient(activity);
        }
    }

    private void getLocation() {


        try {
            Location lastKnownLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
            Log.i("LOCATION_", "lastKnownLocation: " + lastKnownLocation);

            EventBus.getDefault().post(new CurrentLocationEvent(lastKnownLocation));
        } catch (SecurityException e) {
            e.printStackTrace();
        }


    }


    private synchronized void buildGoogleApiClient(Activity activity) {
        googleApiClient = new GoogleApiClient.Builder(activity)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();

        googleApiClient.connect();

    }

    public void requestLocationUpdate() {
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationServices.SettingsApi
                .checkLocationSettings(googleApiClient, new LocationSettingsRequest.Builder().addLocationRequest(locationRequest).build())
                .setResultCallback(this);
    }


    private boolean checkPlayServices(Context context) {


        return GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context) == ConnectionResult.SUCCESS;
    }

    public void onActivityResult(int resultCode) {
        switch (resultCode) {
            case Activity.RESULT_OK:
                getLocation();
                break;
        }
    }


    @Override
    public void onConnectionFailed(ConnectionResult result) {
        Log.i("LOCATION_", "Connection failed: ConnectionResult.getErrorCode() = " + result.getErrorCode());
    }

    @Override
    public void onConnected(Bundle arg0) {
        Log.i("LOCATION_", "onConnected");

        getLocation();
    }

    @Override
    public void onConnectionSuspended(int arg0) {
        Log.i("LOCATION_", "onConnectionSuspended");

        googleApiClient.connect();
    }


    @Override
    public void onResult(@NonNull LocationSettingsResult locationSettingsResult) {
        final Status status = locationSettingsResult.getStatus();

        Log.i("LOCATION_", "onResult: " + status);

        switch (status.getStatusCode()) {
            case LocationSettingsStatusCodes.SUCCESS:
                getLocation();
                break;
            case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                EventBus.getDefault().post(new CurrentLocationEvent(status));
                break;
        }
    }
}
