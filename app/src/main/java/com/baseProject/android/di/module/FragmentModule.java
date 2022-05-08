package com.baseProject.android.di.module;

import com.baseProject.android.ui.baseFragments.IdentifiedFragment;
import com.baseProject.android.ui.login.fragments.login.LoginFragment;
import com.baseProject.android.ui.login.fragments.signup.SignupFragment;
import com.google.android.gms.common.ErrorDialogFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * @author Abbas Asadi
 */
@Module
public abstract class FragmentModule {

    /*
     * We define the name of the Fragment we are going
     * to inject the ViewModelFactory into. i.e. in our case
     */

    @ContributesAndroidInjector
    abstract IdentifiedFragment contributesIdentifiedFragment();

    @ContributesAndroidInjector
    abstract ErrorDialogFragment contributesErrorDialogFragment();

    @ContributesAndroidInjector
    abstract LoginFragment contributesLoginFragment();

    @ContributesAndroidInjector
    abstract SignupFragment contributesSignupFragment();


}