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
    }

    public static final class ACTION {
    }

    public static final class NETWORK_EXCEPTION_CODE {

        public static final String SOCKET_TIME_OUT = "5000";
        public static final String FAILED_TO_CONNECT = "5001";
        public static final String NO_INTERNET_CONNECTION = "5002";
    }

    public static final class SERVER_ERROR_CODE {

    }
}
