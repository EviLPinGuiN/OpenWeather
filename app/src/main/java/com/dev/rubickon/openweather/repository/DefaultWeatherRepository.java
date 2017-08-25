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
import io.reactivex.annotations.NonNull;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

/**
 * Created by DNS1 on 23.08.2017.
 */

public class DefaultWeatherRepository extends BaseRepository implements WeatherRepository {


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
        String ids = getIds();
        return ApiFactory.getWeatherService()
                .getWeathers(ids)
                .map(ResponseData::getResult)
                .flatMap(new RealmRewriteCache<>(Response.class))
                .onErrorResumeNext(new RealmCacheErrorHandler<>(Response.class))
                .compose(RxUtils.async());
    }

    @Override
    public Observable<List<Response>> findCity(String s) {
        return ApiFactory.getWeatherService()
                .findCity(s)
                .map(FindResponseData::getResponses);
    }

    public void addCity(@NonNull final Response response) {
        executeTransaction(realm -> realm.insertOrUpdate(response));
    }

    @Override
    public void deleteCity(@NonNull int id) {
        executeTransaction(realm -> {
            Response response = realm.where(Response.class).equalTo("id", id).findFirst();
            response.deleteFromRealm();
        });
    }


    private String getIds(){
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Response> results = realm.where(Response.class).findAll();
        String ids = "";
        for (int i = 0; i < results.size(); i++) {
            if (i != results.size() - 1){
                ids += results.get(i).getId() + ",";
            }
            else {
                ids += results.get(i).getId();
            }
        }
        return ids;
    }

}
