package com.nandy.weatherapp.eventbus;

/**
 * Created by yana on 22.10.17.
 */

public class NetworkConnectionEvent {

    private boolean networkEnabled;

    public NetworkConnectionEvent(boolean networkEnabled) {
        this.networkEnabled = networkEnabled;
    }

    public boolean isNetworkEnabled() {
        return networkEnabled;
    }
}
