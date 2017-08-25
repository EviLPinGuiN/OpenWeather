package com.dev.rubickon.openweather.api;

import android.support.annotation.NonNull;

import com.dev.rubickon.openweather.BuildConfig;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by DNS1 on 23.08.2017.
 */

public class TokenInterceptor implements Interceptor {

    private final static String API_KEY_PARAM = "APPID";

    private TokenInterceptor() {
    }

    @NonNull
    public static Interceptor create() {
        return new TokenInterceptor();
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Request request = original.newBuilder()
                .url(getUrl(original)).build();
        return chain.proceed(request);
    }

    private HttpUrl getUrl(Request request){
        String key = BuildConfig.API_KEY;
        return request.url().newBuilder()
                .addQueryParameter(API_KEY_PARAM, key)
                .build();
    }
}
