package com.googleimagesearch.feature.feed.domain.model

import kotlinx.datetime.Instant

data class SearchHistoryItem(
    val text: String,
    val createdAt: Instant,
)
