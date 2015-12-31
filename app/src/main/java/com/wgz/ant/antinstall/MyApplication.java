package com.wgz.ant.antinstall;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;

/**
 * Created by qwerr on 2015/12/30.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SDKInitializer.initialize(getApplicationContext());
    }
}
