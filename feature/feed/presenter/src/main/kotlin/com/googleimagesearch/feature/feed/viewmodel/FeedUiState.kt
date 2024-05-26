package com.googleimagesearch.feature.feed.viewmodel

import com.googleimagesearch.feature.feed.domain.model.SearchHistoryItem
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class FeedUiState(
    val query: String = "",
    val searchHistory: ImmutableList<SearchHistoryItem> = persistentListOf(),
    val isSearchResultInit: Boolean = false,
)
