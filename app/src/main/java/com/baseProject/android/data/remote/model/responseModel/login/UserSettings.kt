package com.baseProject.android.data.remote.model.responseModel.login

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserSettings(
    @Expose
    @SerializedName("mute_until_period")
    val muteUntilPeriod: Int? = null,
    @Expose
    @SerializedName("client_settings")
    val clientSettings: Any? = null,
    @Expose
    @SerializedName("cache_updated")
    val cacheUpdated: Boolean? = null,
    @Expose
    @SerializedName("is_mute_chats")
    val isMuteChats: Boolean? = null,
    @Expose
    @SerializedName("lang")
    val lang: String? = null,
    @Expose
    @SerializedName("dta_mute_until")
    val dtaMuteUntil: Any? = null
)