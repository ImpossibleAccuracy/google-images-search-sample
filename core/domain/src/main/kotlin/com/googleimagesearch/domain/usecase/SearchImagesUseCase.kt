package com.googleimagesearch.domain.usecase

import androidx.paging.PagingData
import com.googleimagesearch.domain.model.SearchParameters
import com.googleimagesearch.domain.model.SearchResult
import com.googleimagesearch.domain.model.SearchType
import com.googleimagesearch.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow

class SearchImagesUseCase(
    private val searchRepository: SearchRepository<SearchType.Images>
) {
    operator fun invoke(parameters: SearchParameters<SearchType.Images>): Flow<PagingData<SearchResult.GoogleImage>> =
        searchRepository.search(parameters)
}
