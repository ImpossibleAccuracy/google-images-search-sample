package com.googleimagesearch.feature.feed.domain.repository

import com.googleimagesearch.feature.feed.domain.model.SearchHistoryItem
import kotlinx.coroutines.flow.Flow

interface SearchHistoryRepository {
    fun getSearchHistoryOrdered(limit: Int): Flow<List<SearchHistoryItem>>

    suspend fun createHistoryItem(historyItem: SearchHistoryItem)
}
