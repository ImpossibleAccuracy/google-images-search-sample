package com.googleimagesearch.feature.feed.di

import android.content.Context
import com.googleimagesearch.feature.feed.FeedViewModel
import com.googleimagesearch.feature.feed.data.database.createFeedDatabase
import com.googleimagesearch.feature.feed.data.datasource.HistoryDataSource
import com.googleimagesearch.feature.feed.data.repository.SearchHistoryRepositoryImpl
import com.googleimagesearch.feature.feed.domain.repository.SearchHistoryRepository
import com.googleimagesearch.feature.feed.domain.usecase.AddSearchHistoryItemUseCase
import com.googleimagesearch.feature.feed.domain.usecase.GetSearchHistoryUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

fun feedModule(context: Context) = module {
    single { createFeedDatabase(context) }
    single { HistoryDataSource(get()) }
    factory<SearchHistoryRepository> { SearchHistoryRepositoryImpl(get()) }

    factoryOf(::AddSearchHistoryItemUseCase)
    factoryOf(::GetSearchHistoryUseCase)
    factoryOf(::FeedViewModel)
}
