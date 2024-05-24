package com.googleimagesearch.feature.feed.di

import com.googleimagesearch.feature.feed.FeedViewModel
import org.koin.dsl.module

val feedModule = module {
    factory {
        FeedViewModel(get())
    }
}
