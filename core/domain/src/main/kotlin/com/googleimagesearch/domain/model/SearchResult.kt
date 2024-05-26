package com.googleimagesearch.domain.model

import com.googleimagesearch.domain.payload.ImageSize
import com.googleimagesearch.domain.payload.SearchResultSource
import java.io.Serializable

sealed interface SearchResult<T : SearchType> {
    val title: String

    data class Patent(
        override val title: String,
        val snippet: String,
        val link: String,
    ) : SearchResult<SearchType.Patents>, Serializable

    data class GoogleImage(
        override val title: String,
        val imageUrl: String,
        val imageSize: ImageSize,
        val thumbnailUrl: String,
        val thumbnailSize: ImageSize,
        val source: SearchResultSource,
    ) : SearchResult<SearchType.Images>, Serializable // TODO: add Parcelable impl
}
