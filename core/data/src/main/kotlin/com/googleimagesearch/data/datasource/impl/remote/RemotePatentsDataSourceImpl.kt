package com.googleimagesearch.data.datasource.impl.remote

import com.googleimagesearch.data.datasource.declaration.PatentsDataSource
import com.googleimagesearch.data.payload.dto.PatentDto
import com.googleimagesearch.domain.model.SearchParameters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pro.respawn.apiresult.ApiResult

class RemotePatentsDataSourceImpl : PatentsDataSource.Remote {
    override suspend fun search(page: Int, params: SearchParameters.PatentsSearchParams): ApiResult<List<PatentDto>> =
        withContext(Dispatchers.IO) {
            ApiResult {
                listOf(
                    PatentDto(
                        title = "ASDASDSA",
                        snippet = "GFGDFG",
                        link = "FDGDFGFD",
                    )
                )
            }
        }
}