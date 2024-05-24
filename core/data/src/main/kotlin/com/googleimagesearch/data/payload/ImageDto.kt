package com.googleimagesearch.data.payload

data class ImageDto(
    var title: String? = null,
    var imageUrl: String? = null,
    var imageWidth: Int? = null,
    var imageHeight: Int? = null,
    var thumbnailUrl: String? = null,
    var thumbnailWidth: Int? = null,
    var thumbnailHeight: Int? = null,
    var source: String? = null,
    var domain: String? = null,
    var link: String? = null,
    var googleUrl: String? = null,
    var position: Int? = null,
)
