package com.googleimagesearch.data.repository.base

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.googleimagesearch.data.Constants
import com.googleimagesearch.data.datasource.paging.base.BasePagingSource
import kotlinx.coroutines.flow.Flow

abstract class BaseRepository {
    protected fun <Model : Any, DTO : Any> doPagingRequest(
        pagingSource: BasePagingSource<Model, DTO>,
        pageSize: Int = Constants.MAX_PAGE_SIZE,
        prefetchDistance: Int = Constants.PREFETCH_DISTANCE,
        enablePlaceholders: Boolean = true,
        initialLoadSize: Int = pageSize * 3,
        maxSize: Int = Int.MAX_VALUE,
        jumpThreshold: Int = Int.MIN_VALUE
    ): Flow<PagingData<Model>> {
        return Pager(
            config = PagingConfig(
                pageSize,
                prefetchDistance,
                enablePlaceholders,
                initialLoadSize,
                maxSize,
                jumpThreshold
            ),
            pagingSourceFactory = {
                pagingSource
            }
        ).flow
    }
}