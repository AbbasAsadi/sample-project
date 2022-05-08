package com.baseProject.android.data.remote.model.responseModel.usersForeign

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UsersItem(
    @Expose
    @SerializedName("nick")
    val nick: String? = null,
    @Expose
    @SerializedName("dta_create")
    val dtaCreate: String? = null,
    @Expose
    @SerializedName("is_chat_email_notification")
    val isChatEmailNotification: Boolean? = null,
    @Expose
    @SerializedName("is_active")
    val isActive: Boolean? = null,
    @Expose
    @SerializedName("avatar_url")
    val avatarUrl: String? = null,
    @Expose
    @SerializedName("timezone_offset")
    val timezoneOffset: Int? = null,
    @Expose
    @SerializedName("dta_activity")
    val dtaActivity: String? = null,
    @Expose
    @SerializedName("name")
    val name: String? = null,
    @Expose
    @SerializedName("id")
    val id: Int? = null,
    @Expose
    @SerializedName("is_online")
    val isOnline: Int? = null
)