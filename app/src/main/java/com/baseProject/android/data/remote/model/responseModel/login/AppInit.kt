package com.baseProject.android.data.remote.model.responseModel.login

import com.google.gson.annotations.SerializedName

data class AppInit(

    @field:SerializedName("user_settings")
    val userSettings: UserSettings? = null,

    @field:SerializedName("user_settings_global")
    val userSettingsGlobal: UserSettingsGlobal? = null,

    @field:SerializedName("companies")
    val companies: List<CompaniesItem?>? = null,

    @field:SerializedName("chats_count_unread")
    val chatsCountUnread: Int? = null,

    @field:SerializedName("hidden_projects")
    val hiddenProjects: List<Any?>? = null,

    @field:SerializedName("auth_items")
    val authItems: AuthItems? = null,

    @field:SerializedName("user")
    val user: User? = null
)