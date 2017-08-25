package com.dev.rubickon.openweather;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.dev.rubickon.openweather.api.ApiFactory;
import com.dev.rubickon.openweather.repository.RepositoryProvider;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.rx.RealmObservableFactory;

/**
 * Created by DNS1 on 24.08.2017.
 */

public class App extends Application {

    private static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
        setupRealm();
        ApiFactory.recreate();
        RepositoryProvider.init();
    }

    private void setupRealm() {
        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .rxFactory(new RealmObservableFactory())
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(configuration);
    }


    @NonNull
    public static Context getContext() {
        return sContext;
    }

}