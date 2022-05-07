package com.baseProject.android.util;

import android.util.Log;

import java.util.Formatter;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class SecurityUtils {
    private static String toHexString(final byte[] bytes) {
        final Formatter formatter = new Formatter();
        for (final byte b : bytes) {
            formatter.format("%02x", b);
        }
        return formatter.toString();
    }

    public static String hmacSha256(final String phoneNumber) {
        try {
            final String key = "15$4N!$M $8CR58 K4Y";
            final Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(key.getBytes(), "HmacSHA256"));
            return toHexString(mac.doFinal(phoneNumber.getBytes()));
        } catch (final Exception e) {
            Log.d("TAG4", "hmacSha256: excepted");
        }
        return "excepted";
    }
}
