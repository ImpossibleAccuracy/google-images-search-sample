package com.googleimagesearch.data.datasource.impl.remote

import com.googleimagesearch.data.datasource.declaration.PatentsDataSource
import com.googleimagesearch.data.payload.PatentDto
import com.googleimagesearch.domain.model.SearchParameters
import pro.respawn.apiresult.ApiResult

class RemotePatentsDataSourceImpl : PatentsDataSource.Remote {
    override suspend fun search(page: Int, params: SearchParameters.PatentsSearchParams): ApiResult<List<PatentDto>> =
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