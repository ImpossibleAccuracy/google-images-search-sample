package com.googleimagesearch.feature.feed.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
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
        AsyncImage(
            modifier = Modifier
                .width(104.dp)
                .clip(shape),
            model = ImageRequest.Builder(LocalContext.current)
                .data(image.thumbnailUrl)
                .crossfade(true)
                .error(R.drawable.baseline_error_24)
                .build(),
            contentDescription = image.title,
            contentScale = ContentScale.Crop,
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
