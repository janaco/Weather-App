package com.nandy.weatherapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by yana on 20.10.17.
 */

public class Day {

    @SerializedName("maxtemp_c")
    private float maxtempCelciun;
    @SerializedName("maxtemp_f")
    private float maxtempFarangeith;
    @SerializedName("mintemp_c")
    private float mintempCelsius;
    @SerializedName("mintemp_f")
    private float mintempFarangeith;
    @SerializedName("avgtemp_c")
    private float avgtempCelsius;
    @SerializedName("avgtemp_f")
    private float avgtempFarengeith;
    @SerializedName("maxwind_mph")
    private float maxwindMilles;
    @SerializedName("maxwind_kph")
    private float maxwindKilometers;
    @SerializedName("totalprecip_mm")
    private int totalprecipMilimeters;
    @SerializedName("avgvis_km")
    private float averageVisibilityInKm;
    @SerializedName("avgvis_miles")
    private int averageVisibilityInMiles;
    @SerializedName("averageHumidity")
    private int averageHumidity;
    @SerializedName("condition")
    private Condition condition;
    @SerializedName("uvIndex")
    private int uvIndex;

    public float getMaxtempCelciun() {
        return maxtempCelciun;
    }

    public float getMaxtempFarangeith() {
        return maxtempFarangeith;
    }

    public float getMintempCelsius() {
        return mintempCelsius;
    }

    public float getMintempFarangeith() {
        return mintempFarangeith;
    }

    public float getAvgtempCelsius() {
        return avgtempCelsius;
    }

    public float getAvgtempFarengeith() {
        return avgtempFarengeith;
    }

    public float getMaxwindMilles() {
        return maxwindMilles;
    }

    public float getMaxwindKilometers() {
        return maxwindKilometers;
    }

    public int getTotalprecipMilimeters() {
        return totalprecipMilimeters;
    }

    public float getAverageVisibilityInKm() {
        return averageVisibilityInKm;
    }

    public int getAverageVisibilityInMiles() {
        return averageVisibilityInMiles;
    }

    public int getAverageHumidity() {
        return averageHumidity;
    }

    public Condition getCondition() {
        return condition;
    }

    public int getUvIndex() {
        return uvIndex;
    }
}
