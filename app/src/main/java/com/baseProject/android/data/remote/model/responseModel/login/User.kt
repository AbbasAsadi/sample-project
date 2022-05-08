package com.baseProject.android.data.remote.model.responseModel.login

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class User(
    @Expose
    @SerializedName("is_chat_email_notification")
    val isChatEmailNotification: Boolean? = null,
    @Expose
    @SerializedName("due_penalties")
    val duePenalties: Int? = null,
    @Expose
    @SerializedName("is_active")
    val isActive: Boolean? = null,
    @Expose
    @SerializedName("role")
    val role: String? = null,
    @Expose
    @SerializedName("projects")
    val projects: List<Any?>? = null,
    @Expose
    @SerializedName("is_telegram_connected")
    val isTelegramConnected: Boolean? = null,
    @Expose
    @SerializedName("timezone_offset")
    val timezoneOffset: Int? = null,
    @Expose
    @SerializedName("id_company")
    val idCompany: Int? = null,
    @Expose
    @SerializedName("nick")
    val nick: String? = null,
    @Expose
    @SerializedName("dta_create")
    val dtaCreate: String? = null,
    @Expose
    @SerializedName("dta_timer_activity")
    val dtaTimerActivity: Any? = null,
    @Expose
    @SerializedName("is_shared_from_me")
    val isSharedFromMe: Boolean? = null,
    @Expose
    @SerializedName("is_timer_online")
    val isTimerOnline: Int? = null,
    @Expose
    @SerializedName("avatar_url")
    val avatarUrl: Any? = null,
    @Expose
    @SerializedName("timer_last")
    val timerLast: Any? = null,
    @Expose
    @SerializedName("telegram_connect_url")
    val telegramConnectUrl: String? = null,
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
    val isOnline: Int? = null,
    @Expose
    @SerializedName("email")
    val email: String? = null
)