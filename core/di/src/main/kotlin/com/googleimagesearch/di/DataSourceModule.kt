package com.googleimagesearch.di

import com.googleimagesearch.data.datasource.declaration.ImageDataSource
import com.googleimagesearch.data.datasource.declaration.PatentsDataSource
import com.googleimagesearch.data.datasource.impl.remote.RemoteImageDataSourceImpl
import com.googleimagesearch.data.datasource.impl.remote.RemotePatentsDataSourceImpl
import com.googleimagesearch.data.security.ApiInfoProvider
import com.googleimagesearch.data.setup.retrofit.buildRetrofit
import org.koin.dsl.module

fun dataSourceModule(apiInfoProvider: ApiInfoProvider) = module {
    single<ApiInfoProvider> { apiInfoProvider }

    single { buildRetrofit(apiInfoProvider) }
    factory<ImageDataSource.Remote> { RemoteImageDataSourceImpl(get(), get()) }
    factory<PatentsDataSource.Remote> { RemotePatentsDataSourceImpl() }
}
