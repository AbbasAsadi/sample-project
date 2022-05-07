package com.baseProject.android.data.remote.model.requestModel.signup

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SignupRequest(
    @Expose
    @SerializedName("name")
    val name: String,
    @Expose
    @SerializedName("email")
    val email: String,
    @Expose
    @SerializedName("password")
    val password: String
)