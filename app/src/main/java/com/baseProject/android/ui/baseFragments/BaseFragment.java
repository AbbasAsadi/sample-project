package com.baseProject.android.ui.baseFragments;


import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.library.baseAdapters.BuildConfig;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.baseProject.android.factory.ViewModelFactory;

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

    public BaseFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);

        if (BuildConfig.DEBUG) {
            Log.i("Fragment_Name", this.getClass().getCanonicalName());
        }
    }
}
