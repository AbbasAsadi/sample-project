package com.baseProject.android.data.remote.model.requestModel.login

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LoginRequest(
    @Expose
    @SerializedName("email")
    val email: String,
    @Expose
    @SerializedName("password")
    val password: String
)