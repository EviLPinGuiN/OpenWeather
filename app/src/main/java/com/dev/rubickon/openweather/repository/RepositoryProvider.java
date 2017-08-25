package com.dev.rubickon.openweather.repository;

import android.support.annotation.MainThread;
import android.support.annotation.NonNull;

/**
 * Created by DNS1 on 23.08.2017.
 */

public final class RepositoryProvider {

    private static WeatherRepository weatherRepository;

    private RepositoryProvider() {
    }

    @NonNull
    public static WeatherRepository provideWeatherRepository() {
        if (weatherRepository == null) {
            weatherRepository = new DefaultWeatherRepository();
        }
        return weatherRepository;
    }

    @MainThread
    public static void init() {
        weatherRepository = new DefaultWeatherRepository();
    }

}
