package com.baseProject.android.data.remote.model.responseModel;

import android.os.Build;

import androidx.annotation.Keep;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Keep
public class WrappedApiResponse<Data> {

    public static int OK_WITH_RESULT = 200;
    public static int OK_WITHOUT_RESULT = 204;
    @SerializedName("data")
    @Expose
    public Data data;
    @SerializedName("status")
    @Expose
    public int statusCode;
    @SerializedName("serverError")
    @Expose
    public List<WrappedErrorResponse> serverError;

    public List<String> errorCodes() {
        if (serverError != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                return serverError.stream()
                        .map(wrappedErrorResponse -> wrappedErrorResponse.errorCode)
                        .collect(Collectors.toList());
            } else {
                List<String> errorCodes = new ArrayList<>();
                for (WrappedErrorResponse error : serverError) {
                    errorCodes.add(error.errorCode);
                }
                return errorCodes;
            }
        } else {
            return new ArrayList<>();
        }
    }

    public interface WrappedApiResponseJSONKey {
        String DATA = "data";
        String STATUS = "status";
        String SERVER_ERROR = "serverError";
    }

}
