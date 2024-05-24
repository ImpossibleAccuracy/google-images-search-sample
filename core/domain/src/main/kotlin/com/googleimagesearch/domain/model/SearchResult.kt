package com.googleimagesearch.domain.model

import com.googleimagesearch.domain.payload.ImageSize
import com.googleimagesearch.domain.payload.SearchResultSource

sealed interface SearchResult<T : SearchType> {
    val title: String

    data class Patent(
        override val title: String,
        val snippet: String,
        val link: String,
    ) : SearchResult<SearchType.Patents>

    data class GoogleImage(
        override val title: String,
        val imageUrl: String,
        val imageSize: ImageSize,
        val thumbnailUrl: String,
        val thumbnailSize: ImageSize,
        val source: SearchResultSource,
    ) : SearchResult<SearchType.Images>
}
