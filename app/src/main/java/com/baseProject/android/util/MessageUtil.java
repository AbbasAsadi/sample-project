package com.baseProject.android.util;

import android.app.Application;

import androidx.lifecycle.LiveData;

/**
 * @author Abbas Asadi
 */
public class MessageUtil extends LiveData<Integer> {

    private static Application application;


    public static void init(Application application) {
        MessageUtil.application = application;
    }

    public static String getRelativeMessage(int code) {

        return application.getResources().getString(code);
    }
}
