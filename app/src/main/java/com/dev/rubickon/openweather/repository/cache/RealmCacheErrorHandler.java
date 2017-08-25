package com.dev.rubickon.openweather.repository.cache;

import android.support.annotation.NonNull;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.realm.Realm;
import io.realm.RealmModel;
import io.realm.RealmResults;

/**
 * Created by DNS1 on 23.08.2017.
 */

public class RealmCacheErrorHandler <T extends RealmModel> implements Function<Throwable, Observable<List<T>>> {

    private final Class<T> mClass;

    public RealmCacheErrorHandler(@NonNull Class<T> tClass) {
        mClass = tClass;
    }


    @Override
    public Observable<List<T>> apply(@io.reactivex.annotations.NonNull Throwable throwable) throws Exception {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<T> results = realm.where(mClass).findAll();
        return Observable.just(realm.copyFromRealm(results));
    }
}