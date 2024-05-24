package com.googleimagesearch.domain.repository

import androidx.paging.PagingData
import com.googleimagesearch.domain.model.SearchParameters
import com.googleimagesearch.domain.model.SearchResult
import com.googleimagesearch.domain.model.SearchType
import kotlinx.coroutines.flow.Flow

interface SearchRepository<T : SearchType> {
    fun <R : SearchResult<T>> search(parameters: SearchParameters<T>): Flow<PagingData<R>>
}
