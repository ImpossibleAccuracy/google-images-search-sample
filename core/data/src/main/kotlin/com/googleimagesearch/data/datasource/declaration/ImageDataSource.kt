package com.googleimagesearch.data.datasource.declaration

import com.googleimagesearch.data.payload.dto.ImageDto
import com.googleimagesearch.domain.model.SearchParameters
import pro.respawn.apiresult.ApiResult

sealed interface ImageDataSource {
    interface Remote : ImageDataSource {
        suspend fun search(
            page: Int,
            params: SearchParameters.ImageSearchParams,
        ): ApiResult<List<ImageDto>>
    }
}
