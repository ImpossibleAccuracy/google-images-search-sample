package com.googleimagesearch.data.repository

import androidx.paging.PagingData
import com.googleimagesearch.data.datasource.declaration.ImageDataSource
import com.googleimagesearch.data.datasource.declaration.PatentsDataSource
import com.googleimagesearch.data.datasource.paging.ImageSearchPagingSource
import com.googleimagesearch.data.datasource.paging.PatentsSearchPagingSource
import com.googleimagesearch.data.repository.base.BaseRepository
import com.googleimagesearch.domain.model.SearchParameters
import com.googleimagesearch.domain.model.SearchResult
import com.googleimagesearch.domain.model.SearchType
import com.googleimagesearch.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow

class SearchRepositoryImpl<T : SearchType>(
    private val patentsDataSource: PatentsDataSource.Remote,
    private val imageDataSource: ImageDataSource.Remote
) : SearchRepository<T>, BaseRepository() {
    override fun <R : SearchResult<T>> search(parameters: SearchParameters<T>): Flow<PagingData<R>> {
        val source = when (parameters) {
            is SearchParameters.PatentsSearchParams ->
                PatentsSearchPagingSource(parameters, patentsDataSource)

            is SearchParameters.ImageSearchParams ->
                ImageSearchPagingSource(parameters, imageDataSource)
        }

        @Suppress("UNCHECKED_CAST")
        return doPagingRequest(
            pagingSource = source,
        ) as Flow<PagingData<R>>
    }
}
