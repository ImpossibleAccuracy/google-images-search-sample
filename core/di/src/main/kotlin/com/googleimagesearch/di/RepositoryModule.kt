package com.googleimagesearch.di

import com.googleimagesearch.data.repository.SearchRepositoryImpl
import com.googleimagesearch.domain.model.SearchType
import com.googleimagesearch.domain.repository.SearchRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory<SearchRepository<SearchType.Images>> {
        SearchRepositoryImpl(get(), get())
    }
    factory<SearchRepository<SearchType.Patents>> {
        SearchRepositoryImpl(get(), get())
    }
}
