package com.googleimagesearch.domain.model

sealed interface SearchType {
    data object Patents : SearchType
    data object Images : SearchType
}