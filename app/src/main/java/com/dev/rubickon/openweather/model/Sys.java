package com.dev.rubickon.openweather.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by DNS1 on 24.08.2017.
 */

public class Sys extends RealmObject {


    @SerializedName("country")
    @Expose
    private String country;


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
