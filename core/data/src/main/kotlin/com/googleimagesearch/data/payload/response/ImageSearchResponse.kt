package com.googleimagesearch.data.payload.response

import com.googleimagesearch.data.payload.dto.ImageDto

data class ImageSearchResponse(
    var images: List<ImageDto>? = null,
)
