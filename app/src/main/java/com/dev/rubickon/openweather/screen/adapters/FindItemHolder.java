package com.dev.rubickon.openweather.screen.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dev.rubickon.openweather.App;
import com.dev.rubickon.openweather.R;
import com.dev.rubickon.openweather.model.Response;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by DNS1 on 24.08.2017.
 */

public class FindItemHolder extends RecyclerView.ViewHolder {


    @BindView(R.id.find_city)
    TextView mCity;
    @BindView(R.id.country)
    TextView mCountry;
    @BindView(R.id.find_temp)
    TextView mTemp;


    @NonNull
    public static FindItemHolder create(@NonNull Context context) {
        View view = View.inflate(context, R.layout.item_find, null);
        FindItemHolder holder = new FindItemHolder(view);
        ButterKnife.bind(holder, view);
        return holder;
    }

    public FindItemHolder(View itemView) {
        super(itemView);
    }

    public void bind(@NonNull Response response) {
        mCity.setText(response.getName());
        mCountry.setText(response.getSys().getCountry());
        bindTemp(response.getMain().getTemp());
    }


    private void bindTemp(double temp) {
        int colorId = R.color.zero;
        if (temp <= -25) {
            colorId = R.color.very_cold;
        }
        if (temp <= -15 && temp > -25) {
            colorId = R.color.winter;
        }
        if (temp < 0 && temp > -15) {
            colorId = R.color.cold;
        }
        if (temp == 0)
            colorId = R.color.zero;
        if (temp > 0 && temp < -10) {
            colorId = R.color.autumn;
        }
        if (temp >= 10 && temp < 20) {
            colorId = R.color.warm;
        }
        if (temp >= 20 && temp < 30) {
            colorId = R.color.hot;
        }
        if (temp >= 30) {
            colorId = R.color.very_hot;
        }
        String format = temp > 0 ? "+##.#" : temp < 0 ? "##.#" : "#";
        mTemp.setText(new DecimalFormat(format).format(temp));
        mTemp.setTextColor(App.getContext().getResources().getColor(colorId));
    }


}
