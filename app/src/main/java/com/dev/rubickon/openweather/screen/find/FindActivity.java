package com.dev.rubickon.openweather.screen.find;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.dev.rubickon.openweather.R;
import com.dev.rubickon.openweather.model.Response;
import com.dev.rubickon.openweather.screen.adapters.BaseAdapter;
import com.dev.rubickon.openweather.screen.adapters.FindAdapter;
import com.dev.rubickon.openweather.utils.Constants;
import com.dev.rubickon.openweather.widget.DividerItemDecoration;
import com.dev.rubickon.openweather.widget.EmptyRecyclerView;
import com.jakewharton.rxbinding2.support.v7.widget.RxSearchView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class FindActivity extends AppCompatActivity implements FindView, BaseAdapter.OnItemClickListener {

    //    @BindView(R.id.find_et)
//    EditText mEditText;
//    @BindView(R.id.find_lv)
//    ListView mListView;
//    @BindView(R.id.searchView)
//    SearchView mSearchView;
    @BindView(R.id.recyclerView)
    EmptyRecyclerView mRecyclerView;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;


    private FindPresenter presenter;
    private FindAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        setParentBackArrow(mToolbar);
        initRecycler();
        presenter = new FindPresenter(this);
//        RxSearchView.queryTextChanges(mSearchView)
//                .debounce(500, TimeUnit.MILLISECONDS)
//                .filter(s -> s.length() > 2)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(q -> {
//                    mAdapter.changeDataSet(presenter.find(q));
//
//                });
    }


    private void initRecycler() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this));
        mAdapter = getAdapter();
        mAdapter.attachToRecyclerView(mRecyclerView);
        mAdapter.setOnItemClickListener(this);
    }

    private FindAdapter getAdapter() {
        return new FindAdapter(new ArrayList<>());
    }

    @Override
    public void findResult(@NonNull List<Response> responses) {
        mAdapter.changeDataSet(responses);
    }

    @Override
    public void error(Throwable throwable) {

    }

    @Override
    public void addCityInfo(@NonNull Response response) {
        showDialog(Constants.DIALOG_KEY, getResources().getString(R.string.msg_success_response));
    }



    @Override
    public void onItemClick(@NonNull Response response) {
        presenter.onItemClick(response);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_item, menu);
        MenuItem search = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(search);
        search(searchView);
        return true;
    }


    private void search(SearchView searchView){
        RxSearchView.queryTextChanges(searchView)
                .debounce(600, TimeUnit.MILLISECONDS)
                .filter(s -> s.length() > 2)
                .map(CharSequence::toString)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.io())
                .flatMap(s -> presenter.find(s))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::findResult, this::error);
    }

    private void setParentBackArrow(Toolbar toolbar) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            toolbar.setNavigationOnClickListener(v -> onBackPressed());
        }
    }

    private void showDialog(String key, String value){
        Bundle args = new Bundle();
        args.putString(key, value);
        ResponseDialogFragment responseDialogFragment = new ResponseDialogFragment();
        responseDialogFragment.setArguments(args);
        responseDialogFragment.show(getSupportFragmentManager(), "response");
    }

}
