package com.dev.rubickon.openweather.api;

import android.support.annotation.NonNull;

import com.dev.rubickon.openweather.utils.Constants;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by DNS1 on 23.08.2017.
 */

public final class ApiFactory {

    private static OkHttpClient sClient;


    private static volatile WeatherService weatherService;

    private ApiFactory() {
    }

    @NonNull
    public static WeatherService getWeatherService() {
        WeatherService service = weatherService;
        if (service == null) {
            synchronized (ApiFactory.class) {
                service = weatherService;
                if (service == null) {
                    service = weatherService = buildRetrofit().create(WeatherService.class);
                }
            }
        }
        return service;
    }

    public static void recreate() {
        sClient = null;
        sClient = getClient();
        weatherService = buildRetrofit().create(WeatherService.class);

    }


    @NonNull
    private static Retrofit buildRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(getClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @NonNull
    private static OkHttpClient getClient() {
        OkHttpClient client = sClient;
        if (client == null) {
            synchronized (ApiFactory.class) {
                client = sClient;
                if (client == null) {
                    client = sClient = buildClient();
                }
            }
        }
        return client;
    }



    @NonNull
    private static OkHttpClient buildClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(TokenInterceptor.create())
                .addInterceptor(UnitsInterceptor.create())
                .build();
    }


}
