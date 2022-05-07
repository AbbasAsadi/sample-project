package com.baseProject.android.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * @author Abbas Asadi
 */
public class ViewUtility {
    public static int calculateNoOfColumns(Context context, float columnWidthDp) { // For example columnWidthdp=180
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float screenWidthDp = displayMetrics.widthPixels / displayMetrics.density;
        int noOfColumns = (int) (screenWidthDp / columnWidthDp + 0.5); // +0.5 for correct rounding to int.
        return noOfColumns;
    }

    public static int caculateViewSizeBasedOnSpanCount(Context context, int spanCount) {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        //if you need three fix imageview in width
        return displaymetrics.widthPixels / spanCount;
    }

    public static void setCustomFont(TabLayout tabLayout, Context context) {

        ViewGroup vg = (ViewGroup) tabLayout.getChildAt(0);
        int tabsCount = vg.getChildCount();

        for (int j = 0; j < tabsCount; j++) {
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);

            int tabChildsCount = vgTab.getChildCount();

            for (int i = 0; i < tabChildsCount; i++) {
                View tabViewChild = vgTab.getChildAt(i);
                if (tabViewChild instanceof TextView) {
                    ((TextView) tabViewChild)
                            .setTypeface(Typeface.createFromAsset(context.getAssets(), "fonts/IRANSansMobile_Bold.ttf"));
                }
            }
        }
    }

    public static void hideKeyboard(Context context, View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(context);
        }
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * this method separate price
     * example: 42050 --> 42,050
     *
     * @param originalString
     * @param containsToman
     * @return
     */
    public static String numberSeparator(String originalString, boolean containsToman) {
        try {
            originalString = convertToJustNumber(originalString, containsToman);
            Long value = Long.valueOf(originalString);
            DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
            formatter.applyPattern("#,###,###,###");
            String formattedString = formatter.format(value);
            if (containsToman)
                formattedString += "  تومان";

            return formattedString;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * this method clear price from comma
     * example: 42,050 --> 42050
     *
     * @param originalString
     * @param containsToman
     * @return
     */
    public static String convertToJustNumber(String originalString, boolean containsToman) {
        if (originalString.contains(",")) {
            originalString = originalString.replace(",", "");
        }
        if (containsToman && originalString.contains("  تومان")) {
            originalString = originalString.replace("  تومان", "");
        }
        return originalString;
    }


}
