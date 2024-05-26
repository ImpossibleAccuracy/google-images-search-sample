package com.googleimagesearch.app.security

import com.googleimagesearch.app.BuildConfig
import com.googleimagesearch.data.security.ApiInfoProvider

class LocalApiInfoProvider : ApiInfoProvider {
    override val baseUrl: String = "https://google.serper.dev/"

    override suspend fun getToken(): String =
        BuildConfig.SERPER_API_KEY
}