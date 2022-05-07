package com.baseProject.android.data.remote.util;


import com.baseProject.android.data.remote.model.responseModel.WrappedApiResponse;

import okhttp3.Response;
import retrofit2.HttpException;

/**
 * @author Abbas Asadi
 */
public class HttpStatusCode {

    public static final int BAD_TOKEN = 401;
    public static final int TOKEN_NOT_ALLOWED = 403;
    public static final int USER_ONAUTHENTICATED = 410;
    public static final int TOCKEN_AND_REFRESHTOKEN_EXPIERD = 406;
    public static final int TOCKEN_EXPIERD = 407;
    public static final int USER_NOT_AUTHENTICATED = 417;


    /**
     * "error": "ACCESS_DENIED"
     */
    public static boolean isHttp401Error(Response response) {
        return response.code() == BAD_TOKEN;
    }

    /**
     * User Token Is Not Verified And User Most Login
     */
    public static boolean isHttp403Error(Response response) {
        return response.code() == TOKEN_NOT_ALLOWED;
    }

    /**
     * User Token Is Not Verified And User Most Login
     */

    public static boolean isHttp406Error(Response response) {
        return response.code() == TOCKEN_AND_REFRESHTOKEN_EXPIERD;
    }

    public static boolean isHttp406Error(Throwable throwable) {
        if (throwable instanceof HttpException) {
            return ((HttpException) throwable).code() == TOCKEN_AND_REFRESHTOKEN_EXPIERD;
        } else {
            return false;
        }
    }

    /**
     * User Not Authenticated And Most Login To Continue Story
     */
    public static boolean isHttp405Error(Response response) {
        return response.code() == USER_ONAUTHENTICATED;
    }

    /**
     * User Access Token Is Expired
     * Call Refresh Token API
     */
    public static boolean isHttp407Error(Response response) {
        return response.code() == TOCKEN_EXPIERD;
    }

    public static boolean isHttp407Error(Throwable throwable) {
        if (throwable instanceof HttpException) {
            return ((HttpException) throwable).code() == TOCKEN_EXPIERD;
        } else return throwable.getMessage().contains(String.valueOf(TOCKEN_EXPIERD));
    }

    public static boolean isHttp403Error(Throwable throwable) {
        if (throwable instanceof HttpException) {
            return ((HttpException) throwable).code() == TOKEN_NOT_ALLOWED;
        }
        return false;
    }

    public static boolean isHttpSuccessWithoutData(WrappedApiResponse response) {
        return response.statusCode == 204;
    }

    public static boolean isHttpSuccess(WrappedApiResponse response) {
        return response.statusCode >= 200 && response.statusCode < 300;
    }

    public static boolean isHttpSuccessWithData(WrappedApiResponse response) {
        return response.statusCode >= 200 && response.statusCode < 300;
    }

    public static boolean isHttp417Error(Response response) {
        return response.code() == USER_NOT_AUTHENTICATED;
    }
}
