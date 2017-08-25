package com.dev.rubickon.openweather.screen.find;


import android.support.annotation.NonNull;

import com.dev.rubickon.openweather.model.Response;
import com.dev.rubickon.openweather.repository.RepositoryProvider;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by DNS1 on 23.08.2017.
 */

public class FindPresenter {

    private final FindView mView;

    public FindPresenter(@NonNull FindView mView) {
        this.mView = mView;
    }

    public Observable<List<Response>> find(String s){
        return RepositoryProvider.provideWeatherRepository()
                .findCity(s);
    }

    public  void onItemClick(Response response){
        RepositoryProvider
                .provideWeatherRepository()
                .addCity(response);
        mView.addCityInfo(response);
    }


}
