package com.googleimagesearch.feature.feed.viewmodel

sealed interface FeedIntent {
    data class UpdateQuery(val query: String) : FeedIntent
}
