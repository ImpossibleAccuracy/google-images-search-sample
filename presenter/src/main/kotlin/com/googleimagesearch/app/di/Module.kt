package com.googleimagesearch.app.di

import com.googleimagesearch.app.security.LocalApiInfoProvider
import com.googleimagesearch.di.dataSourceModule
import com.googleimagesearch.di.repositoryModule
import com.googleimagesearch.feature.feed.di.feedModule
import com.googleimagesearch.feature.gallery.di.galleryModule

val totalAppModules = listOf(
    dataSourceModule(LocalApiInfoProvider()),
    repositoryModule,
    feedModule,
    galleryModule,
)
