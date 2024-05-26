package com.googleimagesearch.feature.feed.domain.usecase

import com.googleimagesearch.feature.feed.domain.model.SearchHistoryItem
import com.googleimagesearch.feature.feed.domain.repository.SearchHistoryRepository
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetSearchHistoryUseCase(
    private val historyRepository: SearchHistoryRepository,
) {
    companion object {
        const val MAX_HISTORY_ITEMS = 15
    }

    operator fun invoke(limit: Int = MAX_HISTORY_ITEMS): Flow<ImmutableList<SearchHistoryItem>> =
        historyRepository.getSearchHistoryOrdered(limit)
            .map { it.toPersistentList() }
}
