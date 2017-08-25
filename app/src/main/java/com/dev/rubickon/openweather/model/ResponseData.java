package com.dev.rubickon.openweather.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by DNS1 on 23.08.2017.
 */

public class ResponseData extends RealmObject{

    @SerializedName("count")
    @Expose
    private int count;
    @SerializedName("list")
    @Expose
    private RealmList<Response> result;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public RealmList<Response> getResult() {
        return result;
    }

    public void setResult(RealmList<Response> result) {
        this.result = result;
    }
}
