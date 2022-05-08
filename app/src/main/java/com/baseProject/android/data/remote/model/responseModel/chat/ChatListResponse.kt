package com.baseProject.android.data.remote.model.responseModel.chat

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ChatListResponse(
    @Expose
    @SerializedName("channels")
    val channels: List<ChannelsItem?>? = null
)