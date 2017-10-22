package com.nandy.weatherapp.eventbus;

/**
 * Created by yana on 22.10.17.
 */

public class PermissionResultEvent {

    private String []permissions;
    private int requestCode;
    private int []grantResults;

    public PermissionResultEvent(int requestCode, String[] permissions, int[] grantResults) {
        this.permissions = permissions;
        this.requestCode = requestCode;
        this.grantResults = grantResults;
    }

    public int[] getGrantResults() {
        return grantResults;
    }

    public String[] getPermissions() {
        return permissions;
    }

    public int getRequestCode() {
        return requestCode;
    }

}
