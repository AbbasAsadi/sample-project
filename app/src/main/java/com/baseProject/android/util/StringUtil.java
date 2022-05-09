package com.baseProject.android.util;

import android.text.format.DateFormat;
import android.text.format.DateUtils;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtil {
    public static String getDateText(String dateString) {
        if (dateString != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date date = sdf.parse(dateString);
                if (DateUtils.isToday(date.getTime())) {
                    return (DateFormat.format("HH", date) + ":" + DateFormat.format("mm", date));
                } else
                    return (DateFormat.format("dd", date) + " " + DateFormat.format("MMM", date));
            } catch (ParseException ex) {
                Log.v("Exception", ex.getLocalizedMessage());
            }
        }
        return "";
    }
}
