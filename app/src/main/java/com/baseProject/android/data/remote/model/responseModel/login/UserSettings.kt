package com.baseProject.android.data.remote.model.responseModel.login

import com.google.gson.annotations.SerializedName

data class UserSettings(

    @field:SerializedName("mute_until_period")
    val muteUntilPeriod: Int? = null,

    @field:SerializedName("client_settings")
    val clientSettings: Any? = null,

    @field:SerializedName("cache_updated")
    val cacheUpdated: Boolean? = null,

    @field:SerializedName("is_mute_chats")
    val isMuteChats: Boolean? = null,

    @field:SerializedName("lang")
    val lang: String? = null,

    @field:SerializedName("dta_mute_until")
    val dtaMuteUntil: Any? = null
)