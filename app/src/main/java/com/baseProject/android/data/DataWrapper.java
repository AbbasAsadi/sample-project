package com.baseProject.android.data;

import static com.baseProject.android.data.Status.CONNECTION_ERROR;
import static com.baseProject.android.data.Status.ERROR;
import static com.baseProject.android.data.Status.LOADING;
import static com.baseProject.android.data.Status.SERVER_ERROR;
import static com.baseProject.android.data.Status.SUCCESS;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.baseProject.android.data.publicModel.exception.CustomConnectException;

import java.util.List;


/**
 * @author Abbas Asadi
 * <p>
 * This wrapper class return from repository methods to viewmodel
 * and viewmodel pass this datawrapper to view ( Fragment,Activity , ... )
 */
@Keep
public class DataWrapper<T> {

    @NonNull
    public final Status status;
    @Nullable
    public final T data;
    @Nullable
    public final String message;
    //@Nullable public final String serverErrorMessage;
    @Nullable
    public final List<String> serverErrorCodes;
    @Nullable
    public final Throwable throwable;

    private DataWrapper(@NonNull Status status,
                        @Nullable T data,
                        @Nullable String message,
                        // @Nullable String serverErrorMessage,
                        @Nullable List<String> serverErrorCode,
                        @Nullable Throwable throwable) {
        this.status = status;
        this.data = data;
        this.message = message;
        // this.serverErrorMessage = serverErrorMessage;
        this.throwable = throwable;
        this.serverErrorCodes = serverErrorCode;
    }


    public static <T> DataWrapper<T> success(@NonNull T data) {
        return new DataWrapper<>(SUCCESS, data, null, null, null);
    }

    public static <T> DataWrapper<T> error(T data, @Nullable Throwable error, String msg) {
        return new DataWrapper<>(ERROR, data, msg, null, error);
    }

    public static <T> DataWrapper<T> serverError(List<String> serverErrorCode) {
        return new DataWrapper<>(SERVER_ERROR, null, null, serverErrorCode, null);
    }

    public static <T> DataWrapper<T> loading(@Nullable T data) {
        return new DataWrapper<>(LOADING, data, null, null, null);
    }

    public static <T> DataWrapper<T> empty() {
        return new DataWrapper<>(LOADING, null, null, null, null);
    }

    public static <T> DataWrapper<T> connectionError() {
        return new DataWrapper<>(CONNECTION_ERROR, null, null, null, new CustomConnectException());
    }

    public String getServerErrorCodesString() {
        StringBuilder result = new StringBuilder();
        if (serverErrorCodes != null) {
            for (String errorCode : serverErrorCodes) {
                result.append(errorCode).append("-");
            }
        }
        return result.toString();
    }
}

