package com.fatfattim.ad.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.NativeExpressAdView;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.VideoOptions;


public class NativeExpressActivity extends AppCompatActivity {
    private final String TAG = getClass().getSimpleName();

    private NativeExpressAdView mAdView;
    private VideoController mVideoController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        // Initialize the Mobile Ads SDK.
        //MobileAds.initialize(this, APP_ID);
        setContentView(R.layout.activity_native_express);

        // Locate the NativeExpressAdView.
        mAdView = (NativeExpressAdView) findViewById(R.id.adView);

        // Set its video options.
        mAdView.setVideoOptions(new VideoOptions.Builder()
                .setStartMuted(true)
                .build());

        // The VideoController can be used to get lifecycle events and info about an ad's video
        // asset. One will always be returned by getVideoController, even if the ad has no video
        // asset.
        mVideoController = mAdView.getVideoController();
        mVideoController.setVideoLifecycleCallbacks(new VideoController.VideoLifecycleCallbacks() {
            @Override
            public void onVideoEnd() {
                Log.d(TAG, "Video playback is finished.");
                super.onVideoEnd();
            }
        });

        // Set an AdListener for the AdView, so the Activity can take action when an ad has finished
        // loading.
        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                if (mVideoController.hasVideoContent()) {
                    Log.d(TAG, "Received an ad that contains a video asset.");
                } else {
                    Log.d(TAG, "Received an ad that does not contain a video asset.");
                }
            }
            @Override
            public void onAdClosed() {
                Log.d(TAG, "onAdClosed");
            }
            @Override
            public void onAdFailedToLoad(int var1) {
                Log.d(TAG, "onAdFailedToLoad " + var1);
            }
            @Override
            public void onAdLeftApplication() {
                Log.d(TAG, "onAdLeftApplication ");
            }
            @Override
            public void onAdOpened() {
                Log.d(TAG, "onAdOpened ");
            }

        });
        Log.d(TAG, "onCreate: " + mAdView.isLoading());
        mAdView.loadAd(new AdRequest.Builder().build());
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: " + mAdView.isLoading());
        Log.d(TAG, "unit id: " + mAdView.getAdUnitId());
    }
}
