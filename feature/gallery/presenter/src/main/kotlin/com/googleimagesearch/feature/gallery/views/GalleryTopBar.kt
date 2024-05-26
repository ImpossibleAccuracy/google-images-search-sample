package com.googleimagesearch.feature.gallery.views

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import com.googleimagesearch.feature.gallery.ControlsContainerColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GalleryTopBar(
    title: String,
    navigateUp: () -> Unit,
) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = navigateUp) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }
        },
        title = {
            Text(
                text = title,
                maxLines = 1,
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = ControlsContainerColor,
        )
    )
}
