package com.googleimagesearch.feature.gallery.viewmodel

import com.googleimagesearch.domain.model.SearchResult
import kotlinx.collections.immutable.PersistentList

data class GalleryUiState(
    val searchResults: PersistentList<SearchResult.GoogleImage>,

    val hasStoragePermissions: Boolean = false,
)
