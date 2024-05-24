package com.googleimagesearch.feature.gallery

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.googleimagesearch.domain.model.SearchResult
import kotlinx.collections.immutable.ImmutableList


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GalleryScreen(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    searchResults: ImmutableList<SearchResult.GoogleImage>,
) {
    Box(modifier = modifier) {
        HorizontalPager(
            modifier = Modifier.fillMaxSize(),
            state = pagerState,
            pageSpacing = 12.dp,
        ) { page ->
            val item = searchResults[page]

            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(item.imageUrl)
                    .crossfade(true)
                    .error(R.drawable.baseline_error_24)
                    .build(),
                contentDescription = item.title,
                contentScale = ContentScale.Fit,
            )
//            val painter = rememberImagePainter(item.imageUrl)
//
//            Image(
//                modifier = Modifier.fillMaxSize(),
//                painter = painter,
//                contentDescription = item.title,
//            )
        }
    }
}
