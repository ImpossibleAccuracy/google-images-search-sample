package com.googleimagesearch.feature.feed.viewmodel

import com.googleimagesearch.feature.feed.domain.model.SearchHistoryItem

sealed interface FeedIntent {
    data object Search : FeedIntent
    data class SearchByHistoryItem(
        val historyItem: SearchHistoryItem,
    ) : FeedIntent

    data class UpdateQuery(val query: String) : FeedIntent
    data object ClearQuery : FeedIntent
}
