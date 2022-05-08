package com.baseProject.android.util.locale;

import android.content.Context;
import android.os.Bundle;

import dagger.android.support.DaggerAppCompatActivity;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

/**
 * @author Abbas Asadi
 */
public abstract class LocaleAwareCompatActivity extends DaggerAppCompatActivity {

    //private LocaleUtilActivityDelegateImpl localeDelegate = new LocaleUtilActivityDelegateImpl();


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        localeDelegate.onCreate(this);
//        boolean localeInit = Hawk.get(PREFERENCES_KEY.LOCALE_INIT, false);
//        if (!localeInit) {
//            updateLocale(new Locale(LocaleConstants.PERSIAN_LANGUAGE_CODE , LocaleConstants.PERSIAN_COUNTRY_CODE));
//            Hawk.put(Constants.PREFERENCES_KEY.LOCALE_INIT , true);
//        }
    }

    @Override
    protected void onResume() {
        super.onResume();
//        localeDelegate.onResumed(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
//        localeDelegate.onPaused();
    }

}
