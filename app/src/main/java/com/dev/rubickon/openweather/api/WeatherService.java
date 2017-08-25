package com.dev.rubickon.openweather.api;

import com.dev.rubickon.openweather.model.FindResponseData;
import com.dev.rubickon.openweather.model.ResponseData;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by DNS1 on 23.08.2017.
 */

public interface WeatherService {

    @GET("group")
    Observable<ResponseData> getWeathers(@Query("id") String ids);

    @GET("find")
    Observable<FindResponseData> findCity(@Query("q") String q);

}
