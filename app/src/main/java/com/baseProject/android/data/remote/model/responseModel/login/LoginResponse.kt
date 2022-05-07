package com.baseProject.android.data.remote.model.responseModel.login

import com.google.gson.annotations.SerializedName

data class LoginResponse(

    @field:SerializedName("already_join_project")
    val alreadyJoinProject: Any? = null,

    @field:SerializedName("app_init")
    val appInit: AppInit? = null,

    @field:SerializedName("token")
    val token: String? = null
)