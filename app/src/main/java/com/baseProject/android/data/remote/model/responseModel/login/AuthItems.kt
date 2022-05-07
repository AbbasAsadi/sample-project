package com.baseProject.android.data.remote.model.responseModel.login

import com.google.gson.annotations.SerializedName

data class AuthItems(

    @field:SerializedName("owner")
    val owner: Boolean? = null,

    @field:SerializedName("timelapse_video")
    val timelapseVideo: Boolean? = null,

    @field:SerializedName("coder")
    val coder: Boolean? = null,

    @field:SerializedName("tracked_timer_month")
    val trackedTimerMonth: Boolean? = null,

    @field:SerializedName("can_edit_time")
    val canEditTime: Boolean? = null,

    @field:SerializedName("member_area")
    val memberArea: Boolean? = null,

    @field:SerializedName("screens_month")
    val screensMonth: Boolean? = null,

    @field:SerializedName("chat")
    val chat: Boolean? = null,

    @field:SerializedName("instant_screen")
    val instantScreen: Boolean? = null,

    @field:SerializedName("disc_space")
    val discSpace: Boolean? = null,

    @field:SerializedName("trial")
    val trial: Boolean? = null
)