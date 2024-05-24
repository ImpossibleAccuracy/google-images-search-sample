package com.googleimagesearch.domain.payload

data class ImageSize(
    val width: Int,
    val height: Int,
) {
    val aspectRatio: Float
        get() = width.toFloat() / height
}
