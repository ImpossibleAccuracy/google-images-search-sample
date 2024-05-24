package com.googleimagesearch.feature.feed.viewmodel

sealed interface FeedIntent {
    data object Search : FeedIntent
    data class UpdateQuery(val query: String) : FeedIntent
    data object ClearQuery : FeedIntent
}
