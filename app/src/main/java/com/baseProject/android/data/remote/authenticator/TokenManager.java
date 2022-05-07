package com.baseProject.android.data.remote.authenticator;

import static com.baseProject.android.data.remote.RemoteConstants.ACCESS_TOKEN;
import static com.baseProject.android.data.remote.RemoteConstants.TOKEN_EXPIRATION_TIME;

import com.orhanobut.hawk.Hawk;

import java.util.Date;

//import static com.baseProject.android.data.remote.RemoteConstants.REFRESH_TOKEN;


/**
 * The type Token manager.
 * manage user access and refresh token in shared preferences
 *
 * @author Abbas Asadi
 */
public class TokenManager {

//    private final static String PERFIX = "Token ";
    /**
     * this if from (60 sec * 1000 millis)
     */
    private final static Long MINUTE_TO_MILLIS = 60000L;

    /**
     * Gets token.
     *
     * @return user access token
     */
    public static String oldGetToken() {
        return Hawk.get(ACCESS_TOKEN);
    }

    /**
     * if current time is lower than token ttl , token does not expired yet
     *
     * @return
     */
    public static String getToken() {
        String accessToken = Hawk.get(ACCESS_TOKEN);
        return accessToken;
       /* Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        long currentUtcTime = cal.getTimeInMillis();

        if (Hawk.get(TOKEN_EXPIRATION_TIME) != null && currentUtcTime <= (Long) Hawk.get(TOKEN_EXPIRATION_TIME)) {
            return Hawk.get(ACCESS_TOKEN);
        }
        return null;*/
    }

    /**
     * Has token boolean.
     *
     * @return if user login and shared preferences has access token return true else return false
     */
    public static boolean hasToken() {
        return Hawk.contains(ACCESS_TOKEN);
    }

    /**
     * Clear access token.
     */
    public static void clearAccessToken() {
        Hawk.delete(ACCESS_TOKEN);
//        Hawk.delete(REFRESH_TOKEN);
    }

    /**
     * Clear token.
     * <p>
     * clear tokens and user has logged out
     */
    public static void clearToken() {
        Hawk.delete(ACCESS_TOKEN);
//        Hawk.delete(REFRESH_TOKEN);
    }

    /**
     * Refresh token string.
     *
     * @return the refresh token
     */
//    public static String refreshToken() {
//        return Hawk.get(REFRESH_TOKEN);
//    }

    /**
     * Save access token.
     *
     * @param accessToken the access token save access token in shared preferences
     */
    public static void saveAccessToken(String accessToken) {
        Hawk.put(ACCESS_TOKEN, /*PERFIX + */accessToken);
    }

    /**
     * save token with time to live
     *
     * @param accessToken: String access token
     * @param ttl          token time ti live, in minute
     */
    public static void saveAccessToken(String accessToken, Date creationTime, int ttl) {
        Hawk.put(ACCESS_TOKEN, /*PERFIX + */accessToken);
        Hawk.put(TOKEN_EXPIRATION_TIME, creationTime.getTime() + (ttl * MINUTE_TO_MILLIS));
    }

    /* *//**
     * Save refresh token
     *
     * @param refreshToken the refresh token save refresh token in shared preferences
     *//*
    public static void saveRefreshToken(String refreshToken) {
        Hawk.put(REFRESH_TOKEN, PERFIX + refreshToken);
    }*/
}
