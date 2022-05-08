package com.baseProject.android.data.remote.model.responseModel.login

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CompaniesItem(
    @Expose
    @SerializedName("max_file_count")
    val maxFileCount: Int? = null,
    @Expose
    @SerializedName("logo_url")
    val logoUrl: String? = null,
    @Expose
    @SerializedName("timezone")
    val timezone: String? = null,
    @Expose
    @SerializedName("timezone_offset")
    val timezoneOffset: Int? = null,
    @Expose
    @SerializedName("screens_interval")
    val screensInterval: Int? = null,
    @Expose
    @SerializedName("id_user")
    val idUser: Int? = null,
    @Expose
    @SerializedName("screens_quality")
    val screensQuality: Int? = null,
    @Expose
    @SerializedName("dta_create")
    val dtaCreate: String? = null,
    @Expose
    @SerializedName("uid")
    val uid: String? = null,
    @Expose
    @SerializedName("max_file_size")
    val maxFileSize: Int? = null,
    @Expose
    @SerializedName("name")
    val name: String? = null,
    @Expose
    @SerializedName("id")
    val id: Int? = null,
    @Expose
    @SerializedName("owner_fullname")
    val ownerFullname: String? = null,
    @Expose
    @SerializedName("is_my")
    val isMy: Boolean? = null,
    @Expose
    @SerializedName("updated")
    val updated: Int? = null,
    @Expose
    @SerializedName("is_plan_almost_used")
    val isPlanAlmostUsed: Boolean? = null
)