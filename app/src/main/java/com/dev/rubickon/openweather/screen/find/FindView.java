package com.dev.rubickon.openweather.screen.find;

import android.support.annotation.NonNull;

import com.dev.rubickon.openweather.model.Response;

import java.util.List;

/**
 * Created by DNS1 on 24.08.2017.
 */

public interface FindView {

    void findResult(@NonNull List<Response> items);

    void error(Throwable throwable);

    void addCityInfo(@NonNull Response response);
}
