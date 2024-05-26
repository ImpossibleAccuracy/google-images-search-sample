package com.googleimagesearch.domain.payload

import java.io.Serializable

data class SearchResultSource(
    val source: String,
    val sourceDomain: String,
    val sourceLink: String,
    val sourceGoogleLink: String,
) : Serializable
