package com.nandy.weatherapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by yana on 20.10.17.
 */

public class Location {

    @SerializedName("name")
    private String name;
    @SerializedName("region")
    private String region;
    @SerializedName("country")
    private String country;
    @SerializedName("lat")
    private double latitude;
    @SerializedName("lon")
    private double longitude;
    @SerializedName("tz_id")
    private String timezoneId;
    @SerializedName("localtime_epoch")
    private long localTime;

    public String getName() {
        return name;
    }

    public String getRegion() {
        return region;
    }

    public String getCountry() {
        return country;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getTimezoneId() {
        return timezoneId;
    }

    public long getLocalTime() {
        return localTime;
    }
}
