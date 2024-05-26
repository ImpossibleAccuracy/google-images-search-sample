package com.googleimagesearch.feature.gallery

import com.googleimagesearch.domain.model.SearchResult
import com.googleimagesearch.feature.gallery.viewmodel.GalleryIntent
import com.googleimagesearch.feature.gallery.viewmodel.GalleryUiState
import com.googleimagesearch.navigation.screen.AppViewModel
import kotlinx.collections.immutable.PersistentList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GalleryViewModel(
    searchResults: PersistentList<SearchResult.GoogleImage>
) : AppViewModel() {
    private val _uiState = MutableStateFlow(GalleryUiState(searchResults))
    val uiState = _uiState.asStateFlow()

    fun onIntent(intent: GalleryIntent) {
        when (intent) {
            is GalleryIntent.HasStoragePermissionsChange -> _uiState.update {
                it.copy(hasStoragePermissions = intent.hasPermissions)
            }
        }
    }
}
