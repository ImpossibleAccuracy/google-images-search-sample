package com.googleimagesearch.feature.feed.domain.usecase

import com.googleimagesearch.feature.feed.domain.model.SearchHistoryItem
import com.googleimagesearch.feature.feed.domain.repository.SearchHistoryRepository
import kotlinx.datetime.Clock

class AddSearchHistoryItemUseCase(
    private val searchHistoryRepository: SearchHistoryRepository,
) {
    suspend operator fun invoke(searchText: String) {
        searchHistoryRepository.createHistoryItem(
            SearchHistoryItem(
                text = searchText,
                createdAt = Clock.System.now(),
            )
        )
    }
}