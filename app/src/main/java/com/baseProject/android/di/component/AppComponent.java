package com.baseProject.android.di.component;

import android.app.Application;

import com.baseProject.android.BaseApplication;
import com.baseProject.android.di.module.ActivityModule;
import com.baseProject.android.di.module.ApiModule;
import com.baseProject.android.di.module.FragmentModule;
import com.baseProject.android.di.module.PicassoModule;
import com.baseProject.android.di.module.ServiceModule;
import com.baseProject.android.di.module.ViewModelModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * We mark this interface with the @Component annotation.
 * And we define all the modules that can be injected.
 * Note that we provide AndroidSupportInjectionModule.class
 * here. This class was not created by us.
 * It is an internal class in Dagger 2.10.
 * Provides our activities and fragments with given module.
 *
 * @author Abbas Asadi
 */

@Component(modules = {
        ApiModule.class,
        ViewModelModule.class,
        ActivityModule.class,
        PicassoModule.class,
        ServiceModule.class,
        AndroidSupportInjectionModule.class,
        FragmentModule.class
})
@Singleton
public interface AppComponent {

    /**
     * This is our custom Application class
     */
    void inject(BaseApplication baseApplication);


    /**
     * We will call this builder interface from our custom Application class.
     * This will set our application object to the AppComponent.
     * So inside the AppComponent the application instance is available.
     * So this application instance can be accessed by our modules
     * such as ApiModule when needed
     */

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
