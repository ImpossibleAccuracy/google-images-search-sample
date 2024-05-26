package com.googleimagesearch.di

import com.googleimagesearch.domain.usecase.SearchImagesUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val useCaseModule = module {
    factoryOf(::SearchImagesUseCase)
}
