package com.baseProject.android.util;

/**
 * @author Abbas Asadi
 */
public class Constants {
    public static final String[] SETTINGS = {"password", "profile", "registration", "signIn", "address"};


    public enum MENU {
        home, user_account, weblog, setting
    }

    public static final class MEDIA_TYPE {
        public static final String WEBVIEW_IMG_STYLE = "<style>img{display: inline;height: auto;max-width: 100%;}</style>";
        public static final String JSON = "application/json; charset=utf-8";
        public static final String MIME_TYPE = "text/html";
        public static final String ENCODING = "UTF-8";
    }

    public static final class PREFERENCES_KEY {
        public static final String LOCALE_INIT = "locale_init";
        public static final String PUSH_ID = "push_notification_id";
        public static final String OTP_AUTHENTICATE_TYPE = "otp_authenticate_type";
    }

    public static final class ACTION {
        public static final String RESET_PASSWORD = "reset_password";
    }

    public static final class NETWORK_EXCEPTION_CODE {

        public static final String SOCKET_TIME_OUT = "5000";
        public static final String FAILED_TO_CONNECT = "5001";
        public static final String NO_INTERNET_CONNECTION = "5002";
        public static final String OTP_CODE_NOT_VERIFIED = "6000";
    }

    public static final class SERVER_ERROR_CODE {


        // new error codes
        public static final String AUTHORIZATION_TO_ENTER_WAS_NOT_FOUND = "1010";
        public static final String PHONE_NUMBER_IS_REQUIRED = "1011";
        public static final String PHONE_NUMBER_IS_NOT_VALID = "1012";
        public static final String USER_NOT_FOUND = "1013";
        public static final String MESSAGE_NOT_SENT = "1014";
        public static final String PHONE_CODE_UNIQUE_ID_PUSH_TOKEN_REQUIRED = "1015";
        public static final String AUTHENTICATION_FAILED = "1016";
        public static final String TOKEN_REQUIRED = "1017";
        public static final String UNKNOWN_ERROR = "1018";
        public static final String TOKEN_EXPIRE = "1019";
        public static final String ENTER_ROLE = "1020";
        public static final String TEMPLATE_IS_REQUIRED = "1021";
        public static final String SLIDE_NOT_AVAILABLE = "1022";
        public static final String FIELD_REQUIRED = "1023";
        public static final String ORDER_NOT_FOUND = "1024";
        public static final String CAN_NOT_ACCEPT_MORE_THAN_2_ORDER = "1025";
        public static final String INVALID_NATIONAL_CODE = "1026";
        public static final String NURSE_NOT_FOUND = "1027";
        public static final String INVALID_FIRST_NAME_LAST_NAME = "1028";
        public static final String NATIONAL_CODE_ALREADY_REGISTERED = "1029";
        public static final String NURSE_NOT_REGISTERED = "1030";
        public static final String INVALID_IMAGE_FORMAT = "1031";
        public static final String IMAGE_NOT_FOUND = "1032";
        public static final String INSTITUTE_ALREADY_REGISTERED_WITH_THIS_NUMBER = "1033";
        public static final String INCORRECT_CODE = "1034";
        public static final String INVALID_CITY_OR_PROVINCE = "1035";
        public static final String INVALID_DATE = "1036";
        public static final String INVALID_STATUS = "1037";
        public static final String CONTACT_DETAIL_IS_REQUIRED = "1038";
        public static final String NAME_IS_REQUIRED = "1039";
        public static final String INVALID_CONTACT_PHONE = "1040";
        public static final String VIDEO_NOT_AVAILABLE = "1041";
        public static final String NURSE_UNKNOWN_STATUS = "1043";


    }
}
