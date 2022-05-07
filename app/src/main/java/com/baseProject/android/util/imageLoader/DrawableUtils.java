package com.baseProject.android.util.imageLoader;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

/**
 * @author Abbas Asadi
 */
public class DrawableUtils {
    public static Drawable getDrawableByName(Context context, String resourceName) {
        try {
            int resourceImage = context.getResources().getIdentifier(resourceName, "drawable", context.getPackageName());
            return ContextCompat.getDrawable(context, resourceImage);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
