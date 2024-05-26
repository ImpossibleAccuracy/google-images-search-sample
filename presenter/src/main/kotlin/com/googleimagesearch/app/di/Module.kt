package com.googleimagesearch.app.di

import android.content.Context
import com.googleimagesearch.app.security.LocalApiInfoProvider
import com.googleimagesearch.di.dataSourceModule
import com.googleimagesearch.di.repositoryModule
import com.googleimagesearch.di.useCaseModule
import com.googleimagesearch.feature.feed.di.feedModule
import com.googleimagesearch.feature.gallery.di.galleryModule

fun totalAppModules(context: Context) = listOf(
    dataSourceModule(LocalApiInfoProvider()),
    repositoryModule,
    useCaseModule,
    feedModule(context),
    galleryModule,
)
