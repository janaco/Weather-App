package com.nandy.weatherapp.eventbus;

/**
 * Created by yana on 22.10.17.
 */

public class PermissionRequestEvent {

    private String []permissions;
    private int requestCode;

    public PermissionRequestEvent(String[] permissions, int requestCode) {
        this.permissions = permissions;
        this.requestCode = requestCode;
    }

    public String[] getPermissions() {
        return permissions;
    }

    public int getRequestCode() {
        return requestCode;
    }
}
