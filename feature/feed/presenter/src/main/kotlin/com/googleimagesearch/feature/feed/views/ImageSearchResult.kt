package com.googleimagesearch.feature.feed.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.googleimagesearch.domain.model.SearchResult
import com.googleimagesearch.feature.feed.R

@Composable
fun ImageSearchResult(
    modifier: Modifier = Modifier,
    image: SearchResult.GoogleImage,
    onClick: () -> Unit,
) {
    val shape = MaterialTheme.shapes.small

    Row(
        modifier = modifier
            .height(64.dp)
            .clip(shape)
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        SearchImage(
            modifier = Modifier
                .width(104.dp)
                .fillMaxHeight(),
            shape = shape,
            image = image
        )

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = image.title,
                maxLines = 1,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.titleMedium,
            )

            Text(
                text = stringResource(R.string.label_source, image.source.source),
                maxLines = 1,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.labelMedium,
            )
        }
    }
}

@Composable
private fun SearchImage(
    modifier: Modifier = Modifier,
    shape: CornerBasedShape,
    image: SearchResult.GoogleImage
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        val painter = rememberAsyncImagePainter(image.imageUrl)

        Image(
            modifier = Modifier.clip(shape),
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
                    .fillMaxSize()
                    .clip(shape)
                    .background(MaterialTheme.colorScheme.errorContainer),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Icon(
                    modifier = Modifier.size(36.dp),
                    painter = painterResource(R.drawable.baseline_error_24),
                    tint = MaterialTheme.colorScheme.onErrorContainer,
                    contentDescription = "Loading error",
                )
            }
        }
    }
}
