package com.baseProject.android.data.remote.model.responseModel.chat

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Draft(
    @Expose
    @SerializedName("text")
    val text: Any? = null,
    @Expose
    @SerializedName("id_reply")
    val idReply: Any? = null
)