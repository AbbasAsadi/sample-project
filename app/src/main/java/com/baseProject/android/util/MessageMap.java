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
        messageIdMap.put(Constants.NETWORK_EXCEPTION_CODE.OTP_CODE_NOT_VERIFIED, R.string.otp_code_not_verified);
        messageIdMap.put(Constants.NETWORK_EXCEPTION_CODE.NO_INTERNET_CONNECTION, R.string.no_internet_connection);

        //********************* SERVER ERROR ****************************
        messageIdMap.put(Constants.SERVER_ERROR_CODE.AUTHORIZATION_TO_ENTER_WAS_NOT_FOUND, R.string.authorization_to_enter_was_not_found);
        messageIdMap.put(Constants.SERVER_ERROR_CODE.PHONE_NUMBER_IS_REQUIRED, R.string.phone_number_is_required);
        messageIdMap.put(Constants.SERVER_ERROR_CODE.PHONE_NUMBER_IS_NOT_VALID, R.string.phone_number_is_not_valid);
        messageIdMap.put(Constants.SERVER_ERROR_CODE.USER_NOT_FOUND, R.string.user_not_found);
        messageIdMap.put(Constants.SERVER_ERROR_CODE.MESSAGE_NOT_SENT, R.string.message_not_sent);
        messageIdMap.put(Constants.SERVER_ERROR_CODE.PHONE_CODE_UNIQUE_ID_PUSH_TOKEN_REQUIRED, R.string.phone_code_uniqueID_push_token_required);
        messageIdMap.put(Constants.SERVER_ERROR_CODE.AUTHENTICATION_FAILED, R.string.authentication_failed);
        messageIdMap.put(Constants.SERVER_ERROR_CODE.TOKEN_REQUIRED, R.string.token_is_required);
        messageIdMap.put(Constants.SERVER_ERROR_CODE.UNKNOWN_ERROR, R.string.unknown_error);
        messageIdMap.put(Constants.SERVER_ERROR_CODE.TOKEN_EXPIRE, R.string.token_expired);
        messageIdMap.put(Constants.SERVER_ERROR_CODE.ENTER_ROLE, R.string.pattern_not_found);
        messageIdMap.put(Constants.SERVER_ERROR_CODE.TEMPLATE_IS_REQUIRED, R.string.invalid_pattern);
        messageIdMap.put(Constants.SERVER_ERROR_CODE.SLIDE_NOT_AVAILABLE, R.string.slide_not_available);
        messageIdMap.put(Constants.SERVER_ERROR_CODE.FIELD_REQUIRED, R.string.field_required);
        messageIdMap.put(Constants.SERVER_ERROR_CODE.ORDER_NOT_FOUND, R.string.order_not_found);
        messageIdMap.put(Constants.SERVER_ERROR_CODE.CAN_NOT_ACCEPT_MORE_THAN_2_ORDER, R.string.can_not_accept_more_than_2_order);
        messageIdMap.put(Constants.SERVER_ERROR_CODE.INVALID_NATIONAL_CODE, R.string.invalid_national_code);
        messageIdMap.put(Constants.SERVER_ERROR_CODE.NURSE_NOT_FOUND, R.string.nurse_not_found);
        messageIdMap.put(Constants.SERVER_ERROR_CODE.INVALID_FIRST_NAME_LAST_NAME, R.string.invalid_first_name_last_name);
        messageIdMap.put(Constants.SERVER_ERROR_CODE.NATIONAL_CODE_ALREADY_REGISTERED, R.string.national_code_already_registered);
        messageIdMap.put(Constants.SERVER_ERROR_CODE.NURSE_NOT_REGISTERED, R.string.nurse_not_registered);
        messageIdMap.put(Constants.SERVER_ERROR_CODE.INVALID_IMAGE_FORMAT, R.string.invalid_image_format);
        messageIdMap.put(Constants.SERVER_ERROR_CODE.IMAGE_NOT_FOUND, R.string.image_not_found);
        messageIdMap.put(Constants.SERVER_ERROR_CODE.INSTITUTE_ALREADY_REGISTERED_WITH_THIS_NUMBER, R.string.institute_already_registered_with_this_number);
        messageIdMap.put(Constants.SERVER_ERROR_CODE.INCORRECT_CODE, R.string.incorrect_code);
        messageIdMap.put(Constants.SERVER_ERROR_CODE.INVALID_CITY_OR_PROVINCE, R.string.invalid_city_or_province);
        messageIdMap.put(Constants.SERVER_ERROR_CODE.INVALID_DATE, R.string.invalid_date);
        messageIdMap.put(Constants.SERVER_ERROR_CODE.INVALID_STATUS, R.string.invalid_state);
        messageIdMap.put(Constants.SERVER_ERROR_CODE.CONTACT_DETAIL_IS_REQUIRED, R.string.contact_details_is_required);
        messageIdMap.put(Constants.SERVER_ERROR_CODE.NAME_IS_REQUIRED, R.string.name_required_for_photo);
        messageIdMap.put(Constants.SERVER_ERROR_CODE.INVALID_CONTACT_PHONE, R.string.invalid_contact_phone);
        messageIdMap.put(Constants.SERVER_ERROR_CODE.VIDEO_NOT_AVAILABLE, R.string.video_not_available);
        messageIdMap.put(Constants.SERVER_ERROR_CODE.NURSE_UNKNOWN_STATUS, R.string.nurse_unknown_status);

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
