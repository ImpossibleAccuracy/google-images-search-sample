package com.googleimagesearch.feature.feed.data.repository

import com.googleimagesearch.feature.feed.data.datasource.HistoryDataSource
import com.googleimagesearch.feature.feed.domain.model.SearchHistoryItem
import com.googleimagesearch.feature.feed.domain.repository.SearchHistoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SearchHistoryRepositoryImpl(
    private val historyDataSource: HistoryDataSource,
) : SearchHistoryRepository {
    override fun getSearchHistoryOrdered(limit: Int): Flow<List<SearchHistoryItem>> =
        historyDataSource.fetchHistoryItems()
            .map {
                if (it.size > limit) it.subList(0, limit)
                else it
            }

    override suspend fun createHistoryItem(historyItem: SearchHistoryItem) {
        historyDataSource.insert(historyItem)
    }
}

