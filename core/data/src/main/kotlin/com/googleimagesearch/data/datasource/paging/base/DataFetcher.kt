package com.googleimagesearch.data.datasource.paging.base

import pro.respawn.apiresult.ApiResult

fun interface DataFetcher<R> {
    suspend fun fetch(page: Int): ApiResult<List<R>>
}