package com.baseProject.android.di.module;

import com.baseProject.android.ui.login.activity.LoginActivity;
import com.baseProject.android.ui.main.MainActivity;
import com.baseProject.android.util.locale.LocaleAwareCompatActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * @author Abbas Asadi
 */
@Module
public abstract class ActivityModule {

   /* @ContributesAndroidInjector
    abstract SplashActivity contributeSplashActivity();

    @ContributesAndroidInjector
    abstract WelcomeActivity contributeWelcomeActivity();*/

    @ContributesAndroidInjector
    abstract LoginActivity contributeLoginActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract MainActivity contributeMainActivity();

    @ContributesAndroidInjector(modules = FragmentModule.class)
    abstract LocaleAwareCompatActivity contributeLocaleAwareCompatActivity();
}

