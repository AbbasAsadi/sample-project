package com.baseProject.android.di.module;

/**
 * @author Abbas Asadi
 */

import com.baseProject.android.ui.notification.PushNotificationService;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ServiceModule {

    @ContributesAndroidInjector
    abstract PushNotificationService contributePushNotificationService();
}
