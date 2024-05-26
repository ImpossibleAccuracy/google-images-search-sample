package com.googleimagesearch.data.datasource.impl.remote

import android.util.Log
import com.googleimagesearch.data.Constants
import com.googleimagesearch.data.datasource.declaration.ImageDataSource
import com.googleimagesearch.data.payload.dto.ImageDto
import com.googleimagesearch.data.payload.request.ImageSearchRequest
import com.googleimagesearch.data.security.ApiInfoProvider
import com.googleimagesearch.data.setup.retrofit.services.SearchService
import com.googleimagesearch.domain.model.SearchParameters
import com.googleimagesearch.domain.payload.SearchDateRange
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pro.respawn.apiresult.ApiResult
import retrofit2.Retrofit

class RemoteImageDataSourceImpl(
    private val retrofit: Retrofit,
    private val apiInfoProvider: ApiInfoProvider,
) : ImageDataSource.Remote {
    private val searchService by lazy {
        retrofit.create(SearchService::class.java)
    }

    override suspend fun search(page: Int, params: SearchParameters.ImageSearchParams): ApiResult<List<ImageDto>> =
        withContext(Dispatchers.IO) {
            ApiResult {
                Log.i(RemoteImageDataSourceImpl::class.simpleName, "Loading page: $page")

                val body = buildRequestData(page, params)

                val response = searchService.searchImage(
                    body = body,
                    apiToken = apiInfoProvider.getToken(),
                )

                response.images!!
            }
        }

    private fun buildRequestData(page: Int, params: SearchParameters.ImageSearchParams) = ImageSearchRequest(
        query = params.query,
        country = params.country,
        location = params.location,
        language = params.language,
        dateRange = getSearchDateRangeString(params.dateRange),
        autocorrect = if (params.autocorrect) null else false,
        pageSize = Constants.PAGE_SIZE,
        page = page,
    )

    private fun getSearchDateRangeString(dateRange: SearchDateRange): String? = when (dateRange) {
        SearchDateRange.AnyTime -> null
        SearchDateRange.PastHour -> "h"
        SearchDateRange.PastDay -> "d"
        SearchDateRange.PastWeek -> "w"
        SearchDateRange.PastYear -> "y"
    }
}
