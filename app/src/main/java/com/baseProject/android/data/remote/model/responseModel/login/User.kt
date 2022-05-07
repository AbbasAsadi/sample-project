package com.baseProject.android.data.remote.model.responseModel.login

import com.google.gson.annotations.SerializedName

data class User(

    @field:SerializedName("is_chat_email_notification")
    val isChatEmailNotification: Boolean? = null,

    @field:SerializedName("due_penalties")
    val duePenalties: Int? = null,

    @field:SerializedName("is_active")
    val isActive: Boolean? = null,

    @field:SerializedName("role")
    val role: String? = null,

    @field:SerializedName("projects")
    val projects: List<Any?>? = null,

    @field:SerializedName("is_telegram_connected")
    val isTelegramConnected: Boolean? = null,

    @field:SerializedName("timezone_offset")
    val timezoneOffset: Int? = null,

    @field:SerializedName("id_company")
    val idCompany: Int? = null,

    @field:SerializedName("nick")
    val nick: String? = null,

    @field:SerializedName("dta_create")
    val dtaCreate: String? = null,

    @field:SerializedName("dta_timer_activity")
    val dtaTimerActivity: Any? = null,

    @field:SerializedName("is_shared_from_me")
    val isSharedFromMe: Boolean? = null,

    @field:SerializedName("is_timer_online")
    val isTimerOnline: Int? = null,

    @field:SerializedName("avatar_url")
    val avatarUrl: Any? = null,

    @field:SerializedName("timer_last")
    val timerLast: Any? = null,

    @field:SerializedName("telegram_connect_url")
    val telegramConnectUrl: String? = null,

    @field:SerializedName("dta_activity")
    val dtaActivity: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("is_online")
    val isOnline: Int? = null,

    @field:SerializedName("email")
    val email: String? = null
)