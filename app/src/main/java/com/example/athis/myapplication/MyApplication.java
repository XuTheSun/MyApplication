package com.example.athis.myapplication;

import android.app.Application;

import com.tencent.smtt.sdk.QbSdk;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        QbSdk.initX5Environment(this, null);
    }
}
