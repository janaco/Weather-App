package com.nandy.weatherapp.eventbus;

import android.location.Location;

import com.google.android.gms.common.api.Status;

/**
 * Created by yana on 21.10.17.
 */

public class CurrentLocationEvent {

    private Location location;
    private boolean success;
    private Status status;

    public CurrentLocationEvent(Location location) {
        this.location = location;
        this.success = true;
    }

    public CurrentLocationEvent(Status status) {
        this.status = status;
        this.success = false;
    }

    public Location getLocation() {
        return location;
    }

    public Status getStatus() {

        return status;
    }

    public boolean isSuccess() {
        return success;
    }
}
