package com.baseProject.android.di.module;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.baseProject.android.di.ViewModelKey;
import com.baseProject.android.factory.ViewModelFactory;
import com.baseProject.android.ui.login.fragments.login.LoginViewModel;
import com.baseProject.android.ui.login.fragments.signup.SignupViewModel;
import com.baseProject.android.util.userAutentication.UserAuthenticationViewModel;

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
    @ViewModelKey(UserAuthenticationViewModel.class)
    protected abstract ViewModel userAuthenticationViewModel(UserAuthenticationViewModel userAuthenticationViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel.class)
    protected abstract ViewModel loginViewModel(LoginViewModel loginViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(SignupViewModel.class)
    protected abstract ViewModel signupViewModel(SignupViewModel signupViewModel);

}