package com.googleimagesearch.domain.payload

import java.io.Serializable

data class ImageSize(
    val width: Int,
    val height: Int,
) : Serializable {
    val aspectRatio: Float
        get() = width.toFloat() / height
}
