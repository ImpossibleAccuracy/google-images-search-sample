package com.googleimagesearch.feature.gallery

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.googleimagesearch.domain.model.SearchResult
import com.googleimagesearch.feature.gallery.viewmodel.GalleryUiState
import com.googleimagesearch.feature.gallery.views.GalleryBottomBar
import com.googleimagesearch.feature.gallery.views.GalleryItem
import com.googleimagesearch.feature.gallery.views.GalleryTopBar


internal val ControlsContainerColor
    @Composable
    @ReadOnlyComposable
    get() = MaterialTheme.colorScheme
        .surfaceContainerHighest.copy(alpha = 0.3f)

data class GalleryScreenActions(
    val navigateUp: () -> Unit,
    val onSystemUiVisibilityChange: (Boolean) -> Unit,

    val startDownloadingImage: (image: SearchResult.GoogleImage) -> Unit,
)

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GalleryScreen(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    uiState: GalleryUiState,
    actions: GalleryScreenActions,
) {
    val context = LocalContext.current

    var isControlsVisible by remember { mutableStateOf(false) }

    LaunchedEffect(isControlsVisible) {
        actions.onSystemUiVisibilityChange(isControlsVisible)
    }

    Scaffold(
        topBar = {
            val currentItem = uiState.searchResults[pagerState.currentPage]

            AnimatedVisibility(
                modifier = Modifier.fillMaxWidth(),
                visible = isControlsVisible,
                enter = slideInVertically(
                    initialOffsetY = {
                        -it
                    },
                ),
                exit = slideOutVertically(
                    targetOffsetY = {
                        -it
                    },
                ),
            ) {
                GalleryTopBar(
                    title = currentItem.title,
                    navigateUp = actions.navigateUp,
                )
            }
        },
        bottomBar = {
            val currentItem = uiState.searchResults[pagerState.currentPage]

            AnimatedVisibility(
                modifier = Modifier.fillMaxWidth(),
                visible = isControlsVisible,
                enter = slideInVertically(
                    initialOffsetY = {
                        it
                    },
                ),
                exit = slideOutVertically(
                    targetOffsetY = {
                        it
                    },
                ),
            ) {
                GalleryBottomBar(
                    onSaveImage = {
                        actions.startDownloadingImage(currentItem)
                    },
                    onShare = {
                        shareImage(context, currentItem)
                    },
                    onOpenSourceLink = {
                        openImageExternal(context, currentItem)
                    },
                )
            }
        }
    ) {
        Box(modifier = modifier) {
            HorizontalPager(
                modifier = Modifier.fillMaxSize(),
                state = pagerState,
                pageSpacing = 12.dp,
            ) { page ->
                val item = uiState.searchResults[page]

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .pointerInput(Unit) {
                            detectTapGestures {
                                isControlsVisible = !isControlsVisible
                            }
                        },
                    contentAlignment = Alignment.Center,
                ) {
                    GalleryItem(
                        modifier = Modifier.fillMaxWidth(),
                        image = item,
                    )
                }
            }
        }
    }
}

private fun shareImage(context: Context, image: SearchResult.GoogleImage) {
    val sendIntent = Intent(Intent.ACTION_SEND).apply {
        putExtra(Intent.EXTRA_TEXT, image.imageUrl)
        type = "text/plain"
    }

    val shareIntent = Intent.createChooser(sendIntent, null)

    context.startActivity(shareIntent)
}


private fun openImageExternal(context: Context, image: SearchResult.GoogleImage) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(image.source.sourceLink))

    context.startActivity(intent)
}
