package com.baseProject.android.di.module;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.baseProject.android.di.ViewModelKey;
import com.baseProject.android.factory.ViewModelFactory;
import com.baseProject.android.ui.SharedViewModel;
import com.baseProject.android.ui.main.MainActivityViewModel;
import com.baseProject.android.ui.userAccount.userAutentication.UserAuthenticationViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * @author Abbas Asadi
 */
@Module
public abstract class ViewModelModule {

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);

    /**
     * This methods basically says
     * inject this object into a Map using the @IntoMap annotation,
     * with the  MainActivityViewModel.class as key,
     * and a Provider that will build a MainActivityViewModel
     * object.
     */
    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel.class)
    protected abstract ViewModel mainActivityViewModel(MainActivityViewModel mainActivityViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(UserAuthenticationViewModel.class)
    protected abstract ViewModel userAuthenticationViewModel(UserAuthenticationViewModel userAuthenticationViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(SharedViewModel.class)
    protected abstract ViewModel sharedViewModel(SharedViewModel sharedViewModel);

}