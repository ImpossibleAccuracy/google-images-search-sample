package com.googleimagesearch.feature.feed

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.googleimagesearch.domain.model.SearchResult
import com.googleimagesearch.feature.feed.viewmodel.FeedIntent
import com.googleimagesearch.feature.feed.viewmodel.FeedUiState
import com.googleimagesearch.feature.feed.views.SearchQueryBar
import com.googleimagesearch.feature.feed.views.base.EmptyView

private val contentPadding = PaddingValues(
    horizontal = 16.dp,
    vertical = 12.dp,
)

@Composable
fun FeedScreen(
    modifier: Modifier = Modifier,
    uiState: FeedUiState,
    searchResults: LazyPagingItems<SearchResult.GoogleImage>,
    newIntent: (FeedIntent) -> Unit,
    onOpenImage: (SearchResult.GoogleImage) -> Unit,
) {
    var isSearchActive by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        modifier = modifier,
        topBar = {
            SearchQueryBar(
                contentPadding = contentPadding,
                isActive = isSearchActive,
                uiState = uiState,
                searchResults = searchResults,
                newIntent = newIntent,
                onOpenImage = onOpenImage,
                onActiveChange = {
                    isSearchActive = it
                }
            )
        }
    ) { paddingValues ->
        Box(
            Modifier
                .padding(paddingValues)
                .padding(contentPadding)
        ) {
            StartSearchView()
        }
    }
}

@Composable
private fun StartSearchView() {
    EmptyView(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        title = stringResource(R.string.title_start_search),
        subtitle = stringResource(R.string.subtitle_start_search),
    )
}

