package com.googleimagesearch.feature.gallery.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.googleimagesearch.domain.model.SearchResult
import com.googleimagesearch.feature.gallery.R
import net.engawapg.lib.zoomable.rememberZoomState
import net.engawapg.lib.zoomable.zoomable

@Composable
fun GalleryItem(
    modifier: Modifier = Modifier,
    image: SearchResult.GoogleImage,
    isInteractiveNow: Boolean = true,
) {
    val zoomState = rememberZoomState(
        maxScale = 5f,
        contentSize = Size(
            width = image.imageSize.width.toFloat(),
            height = image.imageSize.height.toFloat()
        ),
    )

    Box(
        modifier = modifier.let {
            if (isInteractiveNow) it.zoomable(zoomState)
            else it
        },
        contentAlignment = Alignment.Center,
    ) {
        val painter = rememberAsyncImagePainter(model = image.imageUrl)

        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painter,
            contentScale = ContentScale.Fit,
            contentDescription = null,
        )

        if (painter.state is AsyncImagePainter.State.Loading) {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.primary,
            )
        }

        if (painter.state is AsyncImagePainter.State.Error) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .background(MaterialTheme.colorScheme.errorContainer)
                    .padding(24.dp, 36.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                Icon(
                    modifier = Modifier.size(96.dp),
                    painter = painterResource(R.drawable.baseline_error_24),
                    tint = MaterialTheme.colorScheme.onErrorContainer,
                    contentDescription = "Loading error",
                )

                Spacer(Modifier.height(12.dp))

                Text(
                    text = stringResource(R.string.title_error_loading_image),
                    color = MaterialTheme.colorScheme.onErrorContainer,
                    style = MaterialTheme.typography.titleLarge,
                )

                Text(
                    text = stringResource(R.string.subtitle_error_loading_image),
                    color = MaterialTheme.colorScheme.onErrorContainer,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
    }
}
