package com.nandy.weatherapp.eventbus;

/**
 * Created by yana on 21.10.17.
 */

public class ActivityResultEvent {

    private final int requestCode;
    private final int resultCode;

    public ActivityResultEvent(int requestCode, int resultCode){
        this.requestCode = requestCode;
        this.resultCode = resultCode;
    }

    public int getRequestCode() {
        return requestCode;
    }

    public int getResultCode() {
        return resultCode;
    }
}
