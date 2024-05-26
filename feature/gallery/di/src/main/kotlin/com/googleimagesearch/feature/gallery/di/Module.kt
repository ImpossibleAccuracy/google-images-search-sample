package com.googleimagesearch.feature.gallery.di

import com.googleimagesearch.domain.model.SearchResult
import com.googleimagesearch.feature.gallery.GalleryViewModel
import kotlinx.collections.immutable.PersistentList
import org.koin.dsl.module

val galleryModule = module {
    factory { (searchResults: PersistentList<SearchResult.GoogleImage>) ->
        GalleryViewModel(searchResults = searchResults)
    }
}
