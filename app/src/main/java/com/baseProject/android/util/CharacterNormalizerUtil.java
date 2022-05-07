package com.baseProject.android.util;

import android.text.TextUtils;

/**
 * @author Abbas Asadi
 */
public final class CharacterNormalizerUtil {

    public static String normalize(String plainText) {
        if (TextUtils.isEmpty(plainText)) {
            return plainText;
        } else {
            return plainText
                    .replace("۰", "0").replace("٠", "0")
                    .replace("۱", "1").replace("١", "1")
                    .replace("۲", "2").replace("٢", "2")
                    .replace("۳", "3").replace("٣", "3")
                    .replace("۴", "4").replace("٤", "4")
                    .replace("۵", "5").replace("٥", "5")
                    .replace("۶", "6").replace("٦", "6")
                    .replace("۷", "7").replace("٧", "7")
                    .replace("۸", "8").replace("٨", "8")
                    .replace("۹", "9").replace("٩", "9")
                    .replace("بِ", "ب")
                    .replace("زِ", "ز")
                    .replace("دِ", "د")
                    .replace("ذِِ", "ذ")
                    .replace("سِ", "س")
                    .replace("شِ", "ش")
                    .replace("ك", "ک")
                    .replace("ى", "ی")
                    .replace("ي", "ی")
                    .trim();
        }
    }
}
