package com.googleimagesearch.data.datasource.paging

import com.googleimagesearch.data.datasource.declaration.PatentsDataSource
import com.googleimagesearch.data.datasource.paging.base.BasePagingSource
import com.googleimagesearch.data.payload.dto.PatentDto
import com.googleimagesearch.domain.model.SearchParameters
import com.googleimagesearch.domain.model.SearchResult

class PatentsSearchPagingSource(
    private val params: SearchParameters.PatentsSearchParams,
    private val dataSource: PatentsDataSource.Remote,
) : BasePagingSource<SearchResult.Patent, PatentDto>(
    dataFetcher = {
        dataSource.search(it, params)
    },
    dataMapper = {
        SearchResult.Patent(
            title = it.title!!,
            snippet = it.snippet!!,
            link = it.link!!,
        )
    }
)
