package com.baseProject.android.util;

import android.app.Activity;
import android.content.Context;

import com.baseProject.android.R;

import org.aviran.cookiebar2.CookieBar;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Abbas Asadi
 */
public class MessageMap {

    private final static Map<String, Integer> messageIdMap;

    static {
        messageIdMap = new HashMap<>();
        messageIdMap.put("1160", R.string.internal_server_error);
        messageIdMap.put(Constants.NETWORK_EXCEPTION_CODE.SOCKET_TIME_OUT, R.string.exception_socket_time_out);
        messageIdMap.put(Constants.NETWORK_EXCEPTION_CODE.FAILED_TO_CONNECT, R.string.exception_socket_time_out);
        messageIdMap.put(Constants.NETWORK_EXCEPTION_CODE.NO_INTERNET_CONNECTION, R.string.no_internet_connection);

        //********************* SERVER ERROR ****************************

    }


    public static String getMessageWithServerErrorCode(String errorCode) {
        return messageIdMap.containsKey(errorCode) ? MessageUtil.getRelativeMessage(messageIdMap.get(errorCode)) : "";
    }

    public static String getMessageWithServerErrorCode(Context context, String errorCode) {
        return messageIdMap.containsKey(errorCode) ? context.getString(messageIdMap.get(errorCode)) : "";
    }

    public static String getMessageWithServerErrorCode(String errorCode, int param) {
        String message = messageIdMap.containsKey(errorCode) ? MessageUtil.getRelativeMessage(messageIdMap.get(errorCode)) : "";
        if (!message.isEmpty()) {
            message = message.replace("{0}", param + "");
        }
        return message;
    }


    public static String getMessageWithThrowableException(String errorCode) {
        return messageIdMap.containsKey(errorCode) ? MessageUtil.getRelativeMessage(messageIdMap.get(errorCode)) : "";
    }

    public static void showMessage(Activity activity, List<String> serverErrorCodes, Context context) {
        CookieBar.build(activity)
                .setTitle(R.string.error)
                .setMessage(getMessageString(serverErrorCodes, context))
                .setCookiePosition(CookieBar.TOP)  // Cookie will be displayed at the bottom
                .setBackgroundColor(R.color.colorRed)
                .setDuration(3000)
                .show();
    }

    public static void showMessage(String errorMessage, Activity activity) {
        CookieBar.build(activity)
                .setTitle(R.string.error)
                .setMessage(errorMessage)
                .setCookiePosition(CookieBar.TOP)  // Cookie will be displayed at the bottom
                .setBackgroundColor(R.color.colorRed)
                .setDuration(3000)
                .show();
    }

    public static void showMessage(Activity activity, String message) {
        CookieBar.build(activity)
                .setMessage(message)
                .setCookiePosition(CookieBar.TOP)  // Cookie will be displayed at the bottom
                .setBackgroundColor(R.color.colorGreen)
                .setDuration(3000)
                .show();
    }

    public static String getMessageString(List<String> errorList, Context context) {
        if (errorList != null) {
            StringBuilder stringBuilder = new StringBuilder();
            boolean hasMultipleError = errorList.size() > 1;
            boolean firstLine = true;
            for (String errorCode : errorList) {
                if (!firstLine) {
                    stringBuilder.append("\n");
                }
                if (hasMultipleError) {
                    stringBuilder.append("- ");
                    firstLine = false;
                }
                stringBuilder.append(MessageMap.getMessageWithServerErrorCode(context, errorCode));
            }
            return stringBuilder.toString();
        } else {
            return context.getString(R.string.unknown_error);
        }
    }
}
