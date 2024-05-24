package com.googleimagesearch.di

import com.googleimagesearch.data.datasource.declaration.ImageDataSource
import com.googleimagesearch.data.datasource.declaration.PatentsDataSource
import com.googleimagesearch.data.datasource.impl.remote.RemoteImageDataSourceImpl
import com.googleimagesearch.data.datasource.impl.remote.RemotePatentsDataSourceImpl
import org.koin.dsl.module

val dataSourceModule = module {
    factory<ImageDataSource.Remote> { RemoteImageDataSourceImpl() }
    factory<PatentsDataSource.Remote> { RemotePatentsDataSourceImpl() }
}
