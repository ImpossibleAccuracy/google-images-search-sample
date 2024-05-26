package com.googleimagesearch.data.payload.dto

import com.google.gson.annotations.SerializedName

data class ImageDto(
    @SerializedName("title")
    var title: String? = null,

    @SerializedName("imageUrl")
    var imageUrl: String? = null,

    @SerializedName("imageWidth")
    var imageWidth: Int? = null,
    @SerializedName("imageHeight")
    var imageHeight: Int? = null,

    @SerializedName("thumbnailUrl")
    var thumbnailUrl: String? = null,

    @SerializedName("thumbnailWidth")
    var thumbnailWidth: Int? = null,

    @SerializedName("thumbnailHeight")
    var thumbnailHeight: Int? = null,

    @SerializedName("source")
    var source: String? = null,
    @SerializedName("domain")
    var domain: String? = null,
    @SerializedName("link")
    var link: String? = null,
    @SerializedName("googleUrl")
    var googleUrl: String? = null,

    @SerializedName("position")
    var position: Int? = null,
)
