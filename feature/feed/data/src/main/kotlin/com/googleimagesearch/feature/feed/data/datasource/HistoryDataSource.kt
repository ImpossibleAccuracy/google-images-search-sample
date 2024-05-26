package com.googleimagesearch.feature.feed.data.datasource

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.googleimagesearch.feature.feed.data.FeedDatabase
import com.googleimagesearch.feature.feed.data.sqldelight.history.HistoryItem
import com.googleimagesearch.feature.feed.domain.model.SearchHistoryItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext
import kotlinx.datetime.Instant

class HistoryDataSource(
    private val feedDatabase: FeedDatabase,
) {
    fun fetchHistoryItems(): Flow<List<SearchHistoryItem>> =
        feedDatabase.historyItemQueries.selectAll()
            .asFlow()
            .onEach { println(it) }
            .mapToList(Dispatchers.IO)
            .map { it.map(::mapToModel) }
            .map { it.sortedByDescending(SearchHistoryItem::createdAt) }

    suspend fun insert(item: SearchHistoryItem) {
        withContext(Dispatchers.IO) {
            feedDatabase.historyItemQueries.insert(
                search_text = item.text,
                created_at = item.createdAt.toString(),
            )
        }
    }
}

private fun mapToModel(
    item: HistoryItem
) = SearchHistoryItem(
    text = item.search_text,
    createdAt = Instant.parse(item.created_at)
)
