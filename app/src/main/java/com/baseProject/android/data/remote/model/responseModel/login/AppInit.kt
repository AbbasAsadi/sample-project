package com.baseProject.android.data.remote.model.responseModel.login

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AppInit(
    @Expose
    @SerializedName("user_settings")
    val userSettings: UserSettings? = null,
    @Expose
    @SerializedName("user_settings_global")
    val userSettingsGlobal: UserSettingsGlobal? = null,
    @Expose
    @SerializedName("companies")
    val companies: List<CompaniesItem?>? = null,
    @Expose
    @SerializedName("chats_count_unread")
    val chatsCountUnread: Int? = null,
    @Expose
    @SerializedName("hidden_projects")
    val hiddenProjects: List<Any?>? = null,
    @Expose
    @SerializedName("auth_items")
    val authItems: AuthItems? = null,
    @Expose
    @SerializedName("user")
    val user: User? = null
)