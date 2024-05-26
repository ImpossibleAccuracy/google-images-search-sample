package com.googleimagesearch.feature.gallery.viewmodel

sealed interface GalleryIntent {
    data class HasStoragePermissionsChange(
        val hasPermissions: Boolean
    ) : GalleryIntent
}