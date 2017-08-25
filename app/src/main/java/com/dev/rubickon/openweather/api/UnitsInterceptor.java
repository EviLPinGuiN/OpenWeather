package com.dev.rubickon.openweather.api;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by DNS1 on 23.08.2017.
 */

public class UnitsInterceptor implements Interceptor {

    private final static String UNITS_PARAM = "units";

    private UnitsInterceptor() {
    }

    @NonNull
    public static Interceptor create() {
        return new UnitsInterceptor();
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Request request = original.newBuilder()
                .url(getUrl(original)).build();
        return chain.proceed(request);
    }

    private HttpUrl getUrl(Request request){
        //can change to SharedPreference
        String key = "metric";
        return request.url().newBuilder()
                .addQueryParameter(UNITS_PARAM, key)
                .build();
    }


}
