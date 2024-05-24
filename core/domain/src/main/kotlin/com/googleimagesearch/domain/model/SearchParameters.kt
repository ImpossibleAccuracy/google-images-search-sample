package com.googleimagesearch.domain.model

import com.googleimagesearch.domain.payload.SearchDateRange

sealed interface SearchParameters<T : SearchType> {
    data class ImageSearchParams(
        val query: String,

        val country: String?,
        val location: String?,
        val language: String?,

        val dateRange: SearchDateRange = SearchDateRange.AnyTime,
        val autocorrect: Boolean = true,
    ) : SearchParameters<SearchType.Images>

    data class PatentsSearchParams(
        val query: String,
    ) : SearchParameters<SearchType.Patents>
}
