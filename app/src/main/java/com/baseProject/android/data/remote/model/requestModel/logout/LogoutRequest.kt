package com.baseProject.android.data.remote.model.requestModel.logout

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LogoutRequest(
    @Expose
    @SerializedName("anywhere")
    val anywhere: Boolean
)