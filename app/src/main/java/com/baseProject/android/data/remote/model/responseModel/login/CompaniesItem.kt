package com.baseProject.android.data.remote.model.responseModel.login

import com.google.gson.annotations.SerializedName

data class CompaniesItem(

    @field:SerializedName("max_file_count")
    val maxFileCount: Int? = null,

    @field:SerializedName("logo_url")
    val logoUrl: String? = null,

    @field:SerializedName("timezone")
    val timezone: String? = null,

    @field:SerializedName("timezone_offset")
    val timezoneOffset: Int? = null,

    @field:SerializedName("screens_interval")
    val screensInterval: Int? = null,

    @field:SerializedName("id_user")
    val idUser: Int? = null,

    @field:SerializedName("screens_quality")
    val screensQuality: Int? = null,

    @field:SerializedName("dta_create")
    val dtaCreate: String? = null,

    @field:SerializedName("uid")
    val uid: String? = null,

    @field:SerializedName("max_file_size")
    val maxFileSize: Int? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("owner_fullname")
    val ownerFullname: String? = null,

    @field:SerializedName("is_my")
    val isMy: Boolean? = null,

    @field:SerializedName("updated")
    val updated: Int? = null,

    @field:SerializedName("is_plan_almost_used")
    val isPlanAlmostUsed: Boolean? = null
)