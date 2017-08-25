package com.dev.rubickon.openweather.screen.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.dev.rubickon.openweather.model.Response;

import java.util.List;

/**
 * Created by DNS1 on 24.08.2017.
 */

public class WeatherAdapter extends BaseAdapter<WeatherItemHolder, Response>{

    public WeatherAdapter(@NonNull List<Response> items) {
        super(items);
    }

    @Override
    public WeatherItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return WeatherItemHolder.create(parent.getContext());
    }

    @Override
    public void onBindViewHolder(WeatherItemHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        Response item = getItem(position);
        holder.bind(item);
    }
}

