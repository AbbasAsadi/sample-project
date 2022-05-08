package com.baseProject.android.data.remote.model.responseModel.chat

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ChannelsItem(
    @Expose
    @SerializedName("dta_change_msg")
    val dtaChangeMsg: String? = null,
    @Expose
    @SerializedName("mute_until_period")
    val muteUntilPeriod: Int? = null,
    @Expose
    @SerializedName("dta_pin")
    val dtaPin: Any? = null,
    @Expose
    @SerializedName("id_partner")
    val idPartner: Int? = null,
    @Expose
    @SerializedName("dta_last_read")
    val dtaLastRead: String? = null,
    @Expose
    @SerializedName("pin_to_top")
    val pinToTop: Boolean? = null,
    @Expose
    @SerializedName("id_users")
    val idUsers: List<Int?>? = null,
    @Expose
    @SerializedName("type")
    val type: String? = null,
    @Expose
    @SerializedName("id_project")
    val idProject: Any? = null,
    @Expose
    @SerializedName("dta_create")
    val dtaCreate: String? = null,
    @Expose
    @SerializedName("is_starred")
    val isStarred: Boolean? = null,
    @Expose
    @SerializedName("custom_info")
    val customInfo: Any? = null,
    @Expose
    @SerializedName("is_mute")
    val isMute: Boolean? = null,
    @Expose
    @SerializedName("draft")
    val draft: Draft? = null,
    @Expose
    @SerializedName("is_unread_manual")
    val isUnreadManual: Boolean? = null,
    @Expose
    @SerializedName("message_last")
    val messageLast: MessageLast? = null,
    @Expose
    @SerializedName("id")
    val id: Int? = null,
    @Expose
    @SerializedName("is_hide_in_chats_list")
    val isHideInChatsList: Boolean? = null
)