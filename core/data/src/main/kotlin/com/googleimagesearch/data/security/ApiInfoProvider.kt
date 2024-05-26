package com.googleimagesearch.data.security

interface ApiInfoProvider {
    val baseUrl: String

    suspend fun getToken(): String
}
