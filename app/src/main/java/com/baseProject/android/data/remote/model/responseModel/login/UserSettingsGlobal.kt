package com.baseProject.android.data.remote.model.responseModel.login

import com.google.gson.annotations.SerializedName

data class UserSettingsGlobal(

    @field:SerializedName("is_chat_email_notification")
    val isChatEmailNotification: Boolean? = null,

    @field:SerializedName("client_settings")
    val clientSettings: Any? = null
)