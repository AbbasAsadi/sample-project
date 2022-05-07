package com.baseProject.android.util;

import android.Manifest;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.baseProject.android.util.uploadImage.UploadImageUtils;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import java.security.Key;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import javax.crypto.spec.SecretKeySpec;

import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author Abbas Asadi
 */
public class ApplicationUtils {

    public static boolean isAppIsInBackground(Context context) {
        boolean isInBackground = true;
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            List<ActivityManager.RunningAppProcessInfo> runningProcesses = am.getRunningAppProcesses();
            for (ActivityManager.RunningAppProcessInfo processInfo : runningProcesses) {
                if (processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                    for (String activeProcess : processInfo.pkgList) {
                        if (activeProcess.equals(context.getPackageName())) {
                            isInBackground = false;
                        }
                    }
                }
            }
        } else {
           /* List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);
            ComponentName componentInfo = taskInfo.get(0).topActivity;
            if (componentInfo.getPackageName().equals(context.getPackageName())) {
                isInBackground = false;
            }*/

            //ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            List<ActivityManager.RunningAppProcessInfo> runningProcesses = am.getRunningAppProcesses();
            for (ActivityManager.RunningAppProcessInfo processInfo : runningProcesses) {
                if (processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                    for (String activeProcess : processInfo.pkgList) {
                        if (activeProcess.equals(context.getPackageName())) {
                            //If your app is the process in foreground, then it's not in running in background
                            return false;
                        }
                    }
                }
            }

            isInBackground = true;
        }

        return isInBackground;
    }

    public static Key getSigningKey() {
        String secret =
                "eThWmZq4t7w!z%C*F-J@NcRfUjXn2r5u8x/A?D(G+KbPdSgVkYp3s6v9y$&E)H@McQfThWmZq4t7w!z%C*F-JaNdRgUkXn2r5u8x/A?D(G+KbPeShVmYq3s6v9y$&E";
        try {
            byte[] apiKeySecretBytes = Hex.decodeHex(secret);
            Key signingKey = new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS256.getJcaName());
            return signingKey;
        } catch (DecoderException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void showExternalPermission(Activity activity, int requestCode, int pickImageRequestCode, Fragment fragment) {
        int permissionCheck = ContextCompat.checkSelfPermission(
                activity, Manifest.permission.READ_EXTERNAL_STORAGE
        );
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    activity,
                    Manifest.permission.READ_EXTERNAL_STORAGE
            )
            ) {
                showExplanation(
                        "دریافت مجوز",
                        "دریافت مجوز ضروری است. پس از زدن دکمه تایید با دریافت مجوز موافقت نمایید.",
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        requestCode,
                        fragment
                );
            } else {
                requestPermission(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        requestCode,
                        fragment
                );
            }
        } else {
            fragment.startActivityForResult(
                    UploadImageUtils.createIntentForOpenGallery(),
                    pickImageRequestCode
            );
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private static void showExplanation(
            String title,
            String message,
            String permission,
            int permissionRequestCode,
            Fragment fragment
    ) {
        TextView customTitle = new TextView(fragment.getContext());
        customTitle.setText(title);
        customTitle.setPadding(20, 20, 70, 20);
        customTitle.setGravity(Gravity.START);
        customTitle.setTextSize(18f);
        customTitle.setTextColor(Color.WHITE);

        new AlertDialog.Builder(fragment.getContext())
                .setCustomTitle(customTitle)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, (dialog, which) -> requestPermission(
                        permission,
                        permissionRequestCode,
                        fragment

                ))
                .setCancelable(false)
                .show();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void requestPermission(String permissionName, int permissionRequestCode, Fragment fragment) {
        String[] permissionArray = {permissionName};
        fragment.requestPermissions(permissionArray, permissionRequestCode);
    }

    public static String numberSeparator(String s) {
        if (s != null) {
            try {
                String originalString = s;
                double value;

                originalString = convertToJustNumber(originalString);
                value = Double.parseDouble(originalString);
                DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getInstance(Locale.US);
                decimalFormat.applyPattern("#,###,###,###");

                return decimalFormat.format(value);
            } catch (NumberFormatException numberFormatException) {
                numberFormatException.printStackTrace();
            }
        }
        return s;
    }

    public static String convertToJustNumber(String originalString) {
        String tempOriginalString = originalString;
        if (tempOriginalString != null) {
            if (tempOriginalString.contains(",")) {
                tempOriginalString = tempOriginalString.replace(",", "");
            }
        }
        return tempOriginalString;
    }

    public static void hideKeyboard(Context context, View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(context);
        }
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
