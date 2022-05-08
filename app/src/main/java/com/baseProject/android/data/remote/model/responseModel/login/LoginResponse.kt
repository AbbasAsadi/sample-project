package com.baseProject.android.data.remote.model.responseModel.login

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @Expose
    @SerializedName("already_join_project")
    val alreadyJoinProject: Any? = null,
    @Expose
    @SerializedName("app_init")
    val appInit: AppInit? = null,
    @Expose
    @SerializedName("token")
    val token: String? = null
)