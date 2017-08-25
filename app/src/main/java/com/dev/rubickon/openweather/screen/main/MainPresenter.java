package com.dev.rubickon.openweather.screen.main;

import android.support.annotation.NonNull;

import com.dev.rubickon.openweather.repository.RepositoryProvider;

/**
 * Created by DNS1 on 23.08.2017.
 */

public class MainPresenter {

    private final MainView mView;

    public MainPresenter(@NonNull MainView mView) {
        this.mView = mView;
    }

    public void update(){
        RepositoryProvider.provideWeatherRepository()
                .getWeathers()
                .subscribe(mView::showWeather, mView::showError);
    }


    public void delete(int id){
        RepositoryProvider.provideWeatherRepository()
                .deleteCity(id);
        update();
    }

}
