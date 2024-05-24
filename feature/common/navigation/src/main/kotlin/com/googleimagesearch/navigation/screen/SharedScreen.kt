package com.googleimagesearch.navigation.screen

import com.googleimagesearch.domain.model.SearchResult
import kotlinx.collections.immutable.ImmutableList

sealed interface SharedScreen {
    data object Feed : SharedScreen

    data class Gallery(
        val selected: SearchResult.GoogleImage?,
        val items: ImmutableList<SearchResult.GoogleImage>
    ) : SharedScreen
}
