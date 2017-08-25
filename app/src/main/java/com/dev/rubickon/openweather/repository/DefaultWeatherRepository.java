package com.dev.rubickon.openweather.repository;

import com.dev.rubickon.openweather.api.ApiFactory;
import com.dev.rubickon.openweather.model.FindResponseData;
import com.dev.rubickon.openweather.model.Response;
import com.dev.rubickon.openweather.model.ResponseData;
import com.dev.rubickon.openweather.repository.cache.RealmCacheErrorHandler;
import com.dev.rubickon.openweather.repository.cache.RealmRewriteCache;
import com.dev.rubickon.openweather.utils.RxUtils;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by DNS1 on 23.08.2017.
 */

public class DefaultWeatherRepository implements WeatherRepository {



    @Override
    public Observable<List<Response>> getWeathers(String ids) {
        return ApiFactory.getWeatherService()
                .getWeathers(ids)
                .map(ResponseData::getResult)
//                .flatMap(new RealmRewriteCache<>(Response.class))
//                .onErrorResumeNext(new RealmCacheErrorHandler<>(Response.class))
                .compose(RxUtils.async());
    }

    @Override
    public Observable<List<Response>> getWeathers() {
        return null;
    }

    @Override
    public Observable<List<Response>> findCity(String s) {
        return ApiFactory.getWeatherService()
                .findCity(s)
                .map(FindResponseData::getResponses);
    }
}
