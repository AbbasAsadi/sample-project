package com.baseProject.android.data.remote.interceptor;

import android.os.Build;
import android.text.TextUtils;

/**
 * The type Call api helper.
 *
 * @author Abbas Asadi
 */
public class CallApiHelper {


    /**
     * Create user agent string.
     *
     * @return the string
     */
    public static String createUserAgent() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getDeviceName())
                .append(",")
                .append("Android")
                .append(" ")
                .append(Build.VERSION.RELEASE)
                .append("(")
                .append(Build.VERSION.SDK_INT)
                .append(")");

        return stringBuilder.toString();
    }


    /**
     * Returns the consumer friendly device name
     */
    private static String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        }
        return capitalize(manufacturer) + " " + model;
    }

    private static String capitalize(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        char[] arr = str.toCharArray();
        boolean capitalizeNext = true;

        StringBuilder phrase = new StringBuilder();
        for (char c : arr) {
            if (capitalizeNext && Character.isLetter(c)) {
                phrase.append(Character.toUpperCase(c));
                capitalizeNext = false;
                continue;
            } else if (Character.isWhitespace(c)) {
                capitalizeNext = true;
            }
            phrase.append(c);
        }

        return phrase.toString();
    }
}
