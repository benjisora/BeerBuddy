package com.benjisora.beerbuddy;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by saugues on 31/07/17.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FlowManager.init(this);
    }

}
