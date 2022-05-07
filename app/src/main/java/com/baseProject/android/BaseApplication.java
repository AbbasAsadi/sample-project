package com.baseProject.android;

import android.content.Context;

import com.baseProject.android.di.component.DaggerAppComponent;
import com.baseProject.android.util.InternetUtil;
import com.baseProject.android.util.MessageUtil;
import com.example.asanisminstitueproject.util.locale.LocaleAwareApplication;
import com.orhanobut.hawk.Hawk;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;

/**
 * @author Abbas Asadi
 */
public class BaseApplication extends LocaleAwareApplication implements HasAndroidInjector {
    @Inject
    DispatchingAndroidInjector<Object> dispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();

        Hawk.init(this).build();
        InternetUtil.init(this);
        MessageUtil.init(this);
        DaggerAppComponent.builder().application(this).build().inject(this);
    }

    @Override
    public AndroidInjector<Object> androidInjector() {
        return dispatchingAndroidInjector;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }
}
