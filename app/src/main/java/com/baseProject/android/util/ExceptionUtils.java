package com.baseProject.android.util;

import com.baseProject.android.BuildConfig;
//import com.google.firebase.crashlytics.FirebaseCrashlytics;

/**
 * @author Abbas Asadi
 */
public class ExceptionUtils {

    public static void throwNullPointerException(Exception e) throws Exception {
        if (BuildConfig.DEBUG) {
            throw e;
        } else {
//            FirebaseCrashlytics.getInstance().recordException(e);
        }
    }
}
