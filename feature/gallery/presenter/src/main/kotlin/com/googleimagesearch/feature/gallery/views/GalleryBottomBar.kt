package com.googleimagesearch.feature.gallery.views

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.googleimagesearch.feature.gallery.ControlsContainerColor
import com.googleimagesearch.feature.gallery.R

@Composable
fun GalleryBottomBar(
    onSaveImage: () -> Unit,
    onShare: () -> Unit,
    onOpenSourceLink: () -> Unit,
) {
    BottomAppBar(
        containerColor = ControlsContainerColor,
        actions = {
            IconButton(onClick = onSaveImage) {
                Icon(
                    painter = painterResource(R.drawable.baseline_download_24),
                    tint = MaterialTheme.colorScheme.onSurface,
                    contentDescription = "Save image"
                )
            }

            IconButton(onClick = onShare) {
                Icon(
                    painter = painterResource(R.drawable.baseline_share_24),
                    tint = MaterialTheme.colorScheme.onSurface,
                    contentDescription = "Share image"
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onOpenSourceLink) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_open_in_new_24),
                    contentDescription = "Open link",
                )
            }
        }
    )
}
