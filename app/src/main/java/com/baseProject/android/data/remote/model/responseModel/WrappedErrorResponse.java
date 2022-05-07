package com.baseProject.android.data.remote.model.responseModel;

import androidx.annotation.Keep;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Keep
public class WrappedErrorResponse {

    @SerializedName("errorCode")
    @Expose
    public String errorCode;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("message")
    @Expose
    public String errorMessage;

}
