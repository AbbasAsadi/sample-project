package com.baseProject.android.data.remote.model.responseModel.usersForeign

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UsersForeignResponse(
    @Expose
    @SerializedName("users")
    val users: List<UsersItem?>? = null
)