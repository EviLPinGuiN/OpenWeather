package com.dev.rubickon.openweather.screen.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.dev.rubickon.openweather.R;
import com.dev.rubickon.openweather.model.Response;
import com.dev.rubickon.openweather.screen.adapters.WeatherAdapter;
import com.dev.rubickon.openweather.screen.find.FindActivity;
import com.dev.rubickon.openweather.widget.DividerItemDecoration;
import com.dev.rubickon.openweather.widget.EmptyRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainView, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.recyclerView)
    EmptyRecyclerView mRecyclerView;
    @BindView(R.id.main_lt_swipe)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.main_add)
    FloatingActionButton mAdd;
    @BindView(R.id.empty)
    View mEmptyView;

    private MainPresenter presenter;
    private WeatherAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        presenter = new MainPresenter(this);
        initRecycler();
        presenter.update();
    }

    private void initRecycler() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this));
        mRecyclerView.setEmptyView(mEmptyView);
        mAdapter = getAdapter();
        mAdapter.attachToRecyclerView(mRecyclerView);
    }

    private WeatherAdapter getAdapter() {
        return new WeatherAdapter(new ArrayList<>());
    }

    @Override
    public void showWeather(@NonNull List<Response> responses) {
        mAdapter.changeDataSet(responses);
        mSwipeRefreshLayout.setRefreshing(false);
    }


    @Override
    public void deleteCity(View v) {
        presenter.delete((int) v.getTag());
    }


    @Override
    public void showError(Throwable throwable) {
        String message = throwable.getMessage();
        System.out.print(message);
    }


    @Override
    public void onRefresh() {
        presenter.update();
    }

    @OnClick(R.id.main_add)
    public void findPage(View v){
        Intent find = new Intent(this, FindActivity.class);
        startActivity(find);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSwipeRefreshLayout.setRefreshing(true);
        presenter.update();
    }
}
