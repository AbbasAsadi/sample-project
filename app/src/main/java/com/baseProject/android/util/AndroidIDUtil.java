package com.baseProject.android.util;

import android.content.Context;
import android.provider.Settings;

import androidx.lifecycle.LiveData;

/**
 * Singleton class that return android ID
 *
 * @author Abbas Asadi
 */
public class AndroidIDUtil extends LiveData<Integer> {

    public static String getAndroidId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }
}
