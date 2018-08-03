package com.eirinimitsopoulou.technewstoday.activities;

import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.analytics.HitBuilders;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

import com.eirinimitsopoulou.technewstoday.analytics.AnalyticsApplication;
import com.eirinimitsopoulou.technewstoday.R;

/**
 * Created by eirinimitsopoulou on 07/07/2018.
 */

public class SplashScreen extends Activity {
    private Tracker mTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Intent i = new Intent(this, MainActivity.class);
        (SplashScreen.this).startActivity(i);
        finish();


        AnalyticsApplication application = (AnalyticsApplication) getApplication();
        mTracker = application.getDefaultTracker();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mTracker.setScreenName("Google Analytics");
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
    }
}
