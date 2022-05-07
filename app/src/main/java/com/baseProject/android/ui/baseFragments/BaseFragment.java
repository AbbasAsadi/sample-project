package com.baseProject.android.ui.baseFragments;


import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.library.baseAdapters.BuildConfig;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.baseProject.android.factory.ViewModelFactory;
import com.baseProject.android.ui.main.MainActivityViewModel;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;


/**
 * A simple {@link Fragment} subclass.
 *
 * @author Abbas Asadi
 */
public abstract class BaseFragment extends DaggerFragment {
    protected NavController navController;
    @Inject
    protected ViewModelFactory viewModelFactory;
    protected MainActivityViewModel mainActivityViewModel;

    public BaseFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivityViewModel = new ViewModelProvider(this, viewModelFactory).get(MainActivityViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);

        if (BuildConfig.DEBUG) {
            Log.i("Fragment_Name", this.getClass().getCanonicalName());
        }
    }

    /**
     * invisible bottom navigation when enter to some fragment  like product fragment , etc...
     */
    protected void inVisibleBottomNavigation() {
        mainActivityViewModel.getBottomNavigationVisibility().postValue(false);
    }

    /**
     * visible bottom navigation when exit from some fragment  like product fragment , etc...
     */
    protected void visibleBottomNavigation() {
        mainActivityViewModel.getBottomNavigationVisibility().postValue(true);
    }


}
