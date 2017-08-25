package com.dev.rubickon.openweather.repository;

import com.dev.rubickon.openweather.model.Response;
import com.dev.rubickon.openweather.model.ResponseData;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by DNS1 on 23.08.2017.
 */

public interface WeatherRepository {

    Observable<List<Response>> getWeathers(String ids);

    Observable<List<Response>> getWeathers();

    Observable<List<Response>> findCity(String s);

}
