package com.googleimagesearch.data.setup.retrofit

import com.googleimagesearch.data.security.ApiInfoProvider
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun buildRetrofit(apiInfoProvider: ApiInfoProvider): Retrofit =
    Retrofit.Builder()
        .baseUrl(apiInfoProvider.baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
