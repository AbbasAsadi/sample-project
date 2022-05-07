package com.baseProject.android.ui.main;

import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ProcessLifecycleOwner;

import com.baseProject.android.data.DataWrapper;
import com.baseProject.android.data.publicModel.exception.InternetConnectionException;
import com.baseProject.android.data.remote.model.responseModel.update.AppUpdateResponse;
import com.baseProject.android.data.repository.setting.SettingRepository;
import com.baseProject.android.ui.ParentViewModel;
import com.baseProject.android.util.InternetUtil;

import javax.inject.Inject;

/**
 * @author Abbas Asadi
 */
public class MainActivityViewModel extends ParentViewModel implements LifecycleObserver {

    public static final String SPLASH_KEY = "splash";
    private final SettingRepository repository;
    private final MutableLiveData<Integer> menuItemIdLivData = new MutableLiveData<>();
    private final MutableLiveData<DataWrapper<AppUpdateResponse>> appUpdateResponseLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> bottomNavigationVisibility = new MutableLiveData<>(true);


    @Inject
    public MainActivityViewModel(SettingRepository repository) {
        this.repository = repository;
        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);
    }

    public void getAppUpdate() {
        if (InternetUtil.isInternetOn()) {
            loading.set(true);
            addToUnsubscribed(repository.getAppUpdate()
                    .subscribe(dataWrapper -> {
                        loading.set(false);
                        appUpdateResponseLiveData.postValue(dataWrapper);
                    }, throwable -> {
                        loading.set(false);
                        exceptionParser(appUpdateResponseLiveData, throwable);
                    }));
        } else {
            exceptionParser(appUpdateResponseLiveData, new InternetConnectionException());
        }
    }

    public MutableLiveData<Integer> getMenuItemIdLivData() {
        return menuItemIdLivData;
    }

    public MutableLiveData<Boolean> getBottomNavigationVisibility() {
        return bottomNavigationVisibility;
    }

    public MutableLiveData<DataWrapper<AppUpdateResponse>> getAppUpdateResponseLiveData() {
        return appUpdateResponseLiveData;
    }
}

