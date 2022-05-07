package com.baseProject.android.util;

import static com.baseProject.android.data.remote.api.ServerAddresses.BASE_URL;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.lifecycle.LiveData;

import java.util.Objects;


/**
 * @author Abbas Asadi
 */
public class InternetUtil extends LiveData<Boolean> {

    private static Application application;
    private BroadcastReceiver broadcastReceiver;

    public static void init(Application application) {
        InternetUtil.application = application;
    }

    public static Boolean isInternetOn() {
        ConnectivityManager cm = (ConnectivityManager) application.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = Objects.requireNonNull(cm).getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }

    public static String createURL(long productId) {
        return BASE_URL + productId;
    }

    @Override
    protected void onActive() {
        // registerBroadCastReceiver();
    }

    @Override
    protected void onInactive() {
        //  unRegisterBroadCastReceiver();
    }

    private void registerBroadCastReceiver() {

        if (broadcastReceiver == null) {
            IntentFilter filter = new IntentFilter();
            filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);

            broadcastReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    Bundle bundle = intent.getExtras();
                    NetworkInfo networkInfo = bundle.getParcelable("networkInfo");
                    postValue(networkInfo.getState() == NetworkInfo.State.CONNECTED);
                }
            };

            application.registerReceiver(broadcastReceiver, filter);
        }

    }

    private void unRegisterBroadCastReceiver() {

        if (broadcastReceiver != null) {
            application.unregisterReceiver(broadcastReceiver);
            broadcastReceiver = null;
        }

    }

}
