package com.googleimagesearch.data.datasource.declaration

import com.googleimagesearch.data.payload.PatentDto
import com.googleimagesearch.domain.model.SearchParameters
import pro.respawn.apiresult.ApiResult

sealed interface PatentsDataSource {
    interface Remote : PatentsDataSource {
        suspend fun search(
            page: Int,
            params: SearchParameters.PatentsSearchParams,
        ): ApiResult<List<PatentDto>>
    }
}
