package com.baseProject.android.data.remote.model.responseModel.login

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AuthItems(
    @Expose
    @SerializedName("owner")
    val owner: Boolean? = null,
    @Expose
    @SerializedName("timelapse_video")
    val timelapseVideo: Boolean? = null,
    @Expose
    @SerializedName("coder")
    val coder: Boolean? = null,
    @Expose
    @SerializedName("tracked_timer_month")
    val trackedTimerMonth: Boolean? = null,
    @Expose
    @SerializedName("can_edit_time")
    val canEditTime: Boolean? = null,
    @Expose
    @SerializedName("member_area")
    val memberArea: Boolean? = null,
    @Expose
    @SerializedName("screens_month")
    val screensMonth: Boolean? = null,
    @Expose
    @SerializedName("chat")
    val chat: Boolean? = null,
    @Expose
    @SerializedName("instant_screen")
    val instantScreen: Boolean? = null,
    @Expose
    @SerializedName("disc_space")
    val discSpace: Boolean? = null,
    @Expose
    @SerializedName("trial")
    val trial: Boolean? = null
)