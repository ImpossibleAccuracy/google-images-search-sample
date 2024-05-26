package com.googleimagesearch.data.payload.request

import com.google.gson.annotations.SerializedName

data class ImageSearchRequest(
    @SerializedName("q")
    val query: String,

    @SerializedName("gl")
    val country: String?,
    @SerializedName("location")
    val location: String?,
    @SerializedName("hl")
    val language: String?,

    @SerializedName("tbs")
    val dateRange: String?,
    @SerializedName("autocorrect")
    val autocorrect: Boolean?,

    @SerializedName("num")
    val pageSize: Int,
    @SerializedName("page")
    val page: Int,
)
