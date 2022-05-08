package com.baseProject.android.util;

import android.text.TextUtils;

public class ValidateUtil {
    public static boolean commonValidator(CharSequence input) {
        return !TextUtils.isEmpty(input);
    }

    public static boolean isValidEmail(CharSequence input) {
        return !TextUtils.isEmpty(input) && android.util.Patterns.EMAIL_ADDRESS.matcher(input).matches();
    }
}
