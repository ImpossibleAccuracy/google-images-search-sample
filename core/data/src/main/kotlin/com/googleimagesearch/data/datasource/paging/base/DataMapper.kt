package com.googleimagesearch.data.datasource.paging.base

fun interface DataMapper<T, R> {
    fun map(source: T): R
}