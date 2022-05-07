package com.baseProject.android.data.publicModel.wrapper;


import com.baseProject.android.util.CharacterNormalizerUtil;

import org.jetbrains.annotations.NotNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;


/**
 * Server Date format is UTC-ISO String format
 * when rest api call default {@toString()} method dont match to UTC-ISO
 * this class extends {@Date} and override toString() method and convert to UTC_ISO format
 * sample date format come from server: "2019-12-07T07:15:18.413Z"
 *
 * @author Abbas Asadi
 */
public class UtcISODate extends Date {

    public static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    private final DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());


    @Override
    @NotNull
    public String toString() {
        dateFormat.setTimeZone(TimeZone.getDefault());
        String formattedDate = dateFormat.format(this);
        return CharacterNormalizerUtil.normalize(formattedDate);
    }

}
