package com.nandy.weatherapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by yana on 20.10.17.
 */

public class Condition {

    @SerializedName("text")
    private String text;
    @SerializedName("icon")
    private String iconUrl;
    @SerializedName("code")
    private int code;

    public String getText() {
        return text;
    }

    public String getIconUrl() {
        return "https:".concat(iconUrl);
    }

    public int getCode() {
        return code;
    }
}
