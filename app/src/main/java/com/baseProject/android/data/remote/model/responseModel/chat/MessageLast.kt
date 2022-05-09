package com.baseProject.android.data.remote.model.responseModel.chat

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MessageLast(
    @Expose
    @SerializedName("dta_create")
    val dtaCreate: String? = null,
    @Expose
    @SerializedName("dta_change_msg")
    val dtaChangeMsg: String? = null,
    @Expose
    @SerializedName("is_read")
    val isRead: Int? = null,
    @Expose
    @SerializedName("snippet")
    val snippet: String? = null,
    @Expose
    @SerializedName("reply_on")
    val replyOn: Any? = null,
    @Expose
    @SerializedName("is_starred")
    val isStarred: Boolean? = null,
    @Expose
    @SerializedName("file")
    val file: Any? = null,
    @Expose
    @SerializedName("id_channel")
    val idChannel: Int? = null,
    @Expose
    @SerializedName("is_edited")
    val isEdited: Boolean? = null,
    @Expose
    @SerializedName("id")
    val id: Int? = null,
    @Expose
    @SerializedName("id_user")
    val idUser: Int? = null,
    @Expose
    @SerializedName("text")
    val text: String? = null,
    @Expose
    @SerializedName("user")
    val chatUser: ChatUser? = null
)