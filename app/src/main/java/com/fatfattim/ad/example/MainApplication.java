package com.fatfattim.ad.example;

import android.app.Application;

import com.google.android.gms.ads.MobileAds;


public class MainApplication extends Application {
    private final String TAG = getClass().getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        // Initialize the Mobile Ads SDK.
        MobileAds.initialize(this, getString(R.string.test_application_id));
    }
}
