package com.eirinimitsopoulou.technewstoday.Activities;

import com.google.android.gms.analytics.Tracker;
import com.google.android.gms.analytics.HitBuilders;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

import com.eirinimitsopoulou.technewstoday.Analytics.AnalyticsApplication;
import com.eirinimitsopoulou.technewstoday.R;

/**
 * Created by eirinimitsopoulou on 07/07/2018.
 */

public class SplashScreen extends Activity {
    private static int SPLASH_TIME_OUT = 2000;
    private Tracker mTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent i = new Intent(SplashScreen.this, MainActivity.class);
                (SplashScreen.this).startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);

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
