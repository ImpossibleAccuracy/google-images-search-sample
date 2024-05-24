package com.googleimagesearch.domain.payload

data class SearchResultSource(
    val source: String,
    val sourceDomain: String,
    val sourceLink: String,
    val sourceGoogleLink: String,
)
