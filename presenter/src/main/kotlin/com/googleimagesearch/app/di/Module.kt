package com.googleimagesearch.app.di

import com.googleimagesearch.di.dataSourceModule
import com.googleimagesearch.di.repositoryModule
import com.googleimagesearch.feature.feed.di.feedModule

val totalAppModules = listOf(
    dataSourceModule,
    repositoryModule,
    feedModule,
)
