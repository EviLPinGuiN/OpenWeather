package com.dev.rubickon.openweather.screen.adapters;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.dev.rubickon.openweather.model.Response;

import java.util.List;

/**
 * Created by DNS1 on 24.08.2017.
 */

public class FindAdapter extends BaseAdapter<FindItemHolder, Response> {

    public FindAdapter(@NonNull List<Response> items) {
        super(items);
    }

    @Override
    public FindItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return FindItemHolder.create(parent.getContext());
    }

    @Override
    public void onBindViewHolder(FindItemHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        Response item = getItem(position);
        holder.bind(item);
    }
}