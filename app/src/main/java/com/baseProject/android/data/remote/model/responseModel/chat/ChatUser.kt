package com.baseProject.android.data.remote.model.responseModel.chat

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ChatUser(
    @Expose
    @SerializedName("avatar_url")
    val avatarUrl: String? = null,
    @Expose
    @SerializedName("name")
    val name: String? = null
)