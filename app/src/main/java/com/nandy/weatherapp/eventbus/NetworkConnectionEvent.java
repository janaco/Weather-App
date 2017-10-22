package com.nandy.weatherapp.eventbus;

/**
 * Created by yana on 22.10.17.
 */

public class NetworkConnectionEvent {

    private boolean networkEmabled;

    public NetworkConnectionEvent(boolean networkEmabled) {
        this.networkEmabled = networkEmabled;
    }

    public boolean isNetworkEmabled() {
        return networkEmabled;
    }
}
