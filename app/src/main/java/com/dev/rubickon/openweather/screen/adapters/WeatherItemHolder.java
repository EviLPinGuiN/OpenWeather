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

public class WeatherItemHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.city)
    TextView mCity;
    @BindView(R.id.speed)
    TextView mSpeed;
    @BindView(R.id.deg)
    TextView mDeg;
    @BindView(R.id.temp)
    TextView mTemp;
    @BindView(R.id.iv_deg)
    ImageView mDegIcon;
    @BindView(R.id.delete)
    ImageView mDelete;


    @NonNull
    public static WeatherItemHolder create(@NonNull Context context) {
        View view = View.inflate(context, R.layout.item_main, null);
        WeatherItemHolder holder = new WeatherItemHolder(view);
        ButterKnife.bind(holder, view);
        return holder;
    }

    public WeatherItemHolder(View itemView) {
        super(itemView);
    }

    public void bind(@NonNull Response response) {
        mCity.setText(response.getName());
        mSpeed.setText(String.valueOf(response.getWind().getSpeed()));
        bindDeg(response.getWind().getDeg());
        bindTemp(response.getMain().getTemp());
        mDelete.setTag(response.getId());
    }

    private void bindDeg(double deg) {
        int result = R.string.error;
        int resId = R.drawable.ic_error_outline_black_18dp;
        float scaleX = 1;

        if ((deg >= 0 && deg < 22.5) || (deg >= 337 && deg <= 360)) {
            result = R.string.N;
            resId = R.drawable.ic_arrow_upward_black_18dp;
        }
        if (deg >= 22.5 && deg < 67.5) {
            result = R.string.NE;
            resId = R.drawable.ic_call_made_black_18dp;
            scaleX = 1;
        }
        if (deg >= 67.5 && deg < 112.5) {
            result = R.string.E;
            resId = R.drawable.ic_arrow_forward_black_18dp;
        }
        if (deg >= 112.5 && deg < 157.5) {
            result = R.string.SE;
            resId = R.drawable.ic_call_received_black_18dp;
            scaleX = -1;
        }
        if (deg >= 157.5 && deg < 202.5) {
            result = R.string.S;
            resId = R.drawable.ic_arrow_downward_black_18dp;
        }
        if (deg >= 202.5 && deg < 247.5) {
            result = R.string.SW;
            resId = R.drawable.ic_call_received_black_18dp;
            scaleX = 1;
        }
        if (deg >= 247.5 && deg < 292.5) {
            result = R.string.W;
            resId = R.drawable.ic_arrow_back_black_18dp;
        }
        if (deg >= 292.5 && deg < 337.5) {
            result = R.string.NW;
            resId = R.drawable.ic_call_made_black_18dp;
            scaleX = -1;
        }
        mDegIcon.setImageResource(resId);
        mDegIcon.setScaleX(scaleX);
        mDeg.setText(result);
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
