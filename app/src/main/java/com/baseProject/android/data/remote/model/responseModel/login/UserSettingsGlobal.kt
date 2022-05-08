package com.baseProject.android.data.remote.model.responseModel.login

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserSettingsGlobal(
    @Expose
    @SerializedName("is_chat_email_notification")
    val isChatEmailNotification: Boolean? = null,
    @Expose
    @SerializedName("client_settings")
    val clientSettings: Any? = null
)