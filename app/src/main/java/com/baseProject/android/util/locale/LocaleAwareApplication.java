package com.baseProject.android.util.locale;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import androidx.multidex.MultiDex;

/**
 * @author Abbas Asadi
 */
public class LocaleAwareApplication extends Application {

//    private final LocaleUtilApplicationDelegate localeAppDelegate = new LocaleUtilApplicationDelegate();

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //localeAppDelegate.onConfigurationChanged(this);
    }
}
