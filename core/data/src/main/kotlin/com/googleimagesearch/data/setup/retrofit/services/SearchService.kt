package com.googleimagesearch.data.setup.retrofit.services

import com.googleimagesearch.data.payload.request.ImageSearchRequest
import com.googleimagesearch.data.payload.response.ImageSearchResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface SearchService {
    @POST("images")
    suspend fun searchImage(
        @Body body: ImageSearchRequest,
        @Header("X-API-KEY") apiToken: String,
    ): ImageSearchResponse
}
