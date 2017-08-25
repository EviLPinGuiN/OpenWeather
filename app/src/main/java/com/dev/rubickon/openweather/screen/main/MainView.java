package com.dev.rubickon.openweather.screen.main;

import android.support.annotation.NonNull;

import com.dev.rubickon.openweather.model.Response;

import java.util.List;

/**
 * Created by DNS1 on 23.08.2017.
 */

public interface MainView {

    void showWeather(@NonNull List<Response> items);

    void updateWeather(@NonNull List<Response> items);

    void deleteCity();

    void showError(Throwable throwable);

}
