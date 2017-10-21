package com.nandy.weatherapp.eventbus;

/**
 * Created by yana on 21.10.17.
 */

public class SearchResultEvent {

    private final String qwery;

    public SearchResultEvent(String qwery){
        this.qwery = qwery;
    }

    public String getQwery() {
        return qwery;
    }
}
