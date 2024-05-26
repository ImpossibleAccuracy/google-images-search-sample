package com.googleimagesearch.feature.feed.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemContentType
import com.googleimagesearch.domain.model.SearchResult
import com.googleimagesearch.feature.feed.R
import com.googleimagesearch.feature.feed.viewmodel.FeedIntent
import com.googleimagesearch.feature.feed.viewmodel.FeedUiState
import com.googleimagesearch.feature.feed.views.base.EmptyView
import com.googleimagesearch.feature.feed.views.base.Loader

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchQueryBar(
    contentPadding: PaddingValues,
    isActive: Boolean,
    uiState: FeedUiState,
    searchResults: LazyPagingItems<SearchResult.GoogleImage>,
    newIntent: (FeedIntent) -> Unit,
    onOpenImage: (SearchResult.GoogleImage) -> Unit,
    onActiveChange: (Boolean) -> Unit,
) {
    val focusManager = LocalFocusManager.current

    SearchBar(
        modifier = Modifier
            .fillMaxWidth()
            .let {
                if (isActive) it
                else it.padding(horizontal = 16.dp)
            },
        placeholder = {
            Text(text = stringResource(R.string.label_image_search))
        },
        leadingIcon = if (isActive) {
            {
                IconButton(
                    onClick = {
                        onActiveChange(false)
                        newIntent(FeedIntent.ClearQuery)
                    }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.baseline_arrow_back_24),
                        contentDescription = "Clear"
                    )
                }
            }
        } else null,
        trailingIcon = {
            Row(horizontalArrangement = Arrangement.spacedBy(ButtonDefaults.IconSpacing)) {
                if (isActive) {
                    IconButton(
                        enabled = uiState.query.isNotEmpty(),
                        onClick = {
                            newIntent(FeedIntent.ClearQuery)
                        }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.baseline_clear_24),
                            contentDescription = "Clear"
                        )
                    }
                } else {
                    Icon(
                        painter = painterResource(R.drawable.baseline_search_24),
                        contentDescription = "Search"
                    )
                }
            }
        },
        query = uiState.query,
        onQueryChange = {
            newIntent(FeedIntent.UpdateQuery(it))
        },
        onSearch = {
            focusManager.clearFocus()
            newIntent(FeedIntent.Search)
        },
        active = isActive,
        onActiveChange = onActiveChange,
    ) {
        if (uiState.isSearchResultInit) {
            SearchResults(
                contentPadding = contentPadding,
                searchResults = searchResults,
                onOpenImage = onOpenImage
            )
        } else {
            SearchSuggestions(contentPadding = contentPadding)
        }
    }
}

@Composable
private fun SearchSuggestions(
    contentPadding: PaddingValues,
) {
    // TODO
    Column(Modifier.padding(contentPadding)) {
        Text(text = "Results will be displayed after search")
    }
}

@Composable
private fun SearchResults(
    contentPadding: PaddingValues,
    searchResults: LazyPagingItems<SearchResult.GoogleImage>,
    onOpenImage: (SearchResult.GoogleImage) -> Unit
) {
    with(searchResults.loadState) {
        when {
            refresh is LoadState.Loading -> {
                Loader(
                    Modifier
                        .fillMaxSize()
                        .padding(contentPadding)
                )
            }

            refresh is LoadState.Error || append is LoadState.Error -> {
                Box(Modifier.padding(contentPadding)) {
                    LoadingErrorView()
                }
            }

            refresh is LoadState.NotLoading && searchResults.itemCount < 1 -> {
                Box(Modifier.padding(contentPadding)) {
                    NoSearchResults()
                }
            }

            refresh is LoadState.NotLoading -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = contentPadding,
                    verticalArrangement = Arrangement.spacedBy(6.dp),
                ) {
                    items(
                        count = searchResults.itemCount,
                        contentType = searchResults.itemContentType(),
                    ) { index ->
                        val item = searchResults[index]!!

                        ImageSearchResult(
                            image = item,
                            onClick = {
                                onOpenImage(item)
                            }
                        )
                    }
                }
            }

            append is LoadState.Loading -> {
                Loader(
                    Modifier
                        .fillMaxWidth()
                        .padding(24.dp, 16.dp)
                )
            }
        }
    }
}

@Composable
private fun NoSearchResults() {
    EmptyView(
        modifier = Modifier.fillMaxWidth(),
        icon = {
            Text(
                modifier = Modifier.padding(bottom = 20.dp),
                text = "(·_·)",
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.displayLarge,
                fontSize = 60.sp,
                fontWeight = FontWeight.Bold,
            )
        },
        title = stringResource(R.string.title_no_search_result),
        subtitle = stringResource(R.string.subtitle_no_search_result),
    )
}

@Composable
private fun LoadingErrorView() {
    EmptyView(
        modifier = Modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.medium)
            .background(MaterialTheme.colorScheme.errorContainer)
            .padding(
                horizontal = 16.dp,
                vertical = 12.dp
            ),
        icon = {
            Icon(
                modifier = Modifier.size(64.dp),
                painter = painterResource(id = R.drawable.baseline_error_24),
                tint = MaterialTheme.colorScheme.error,
                contentDescription = "",
            )
        },
        title = stringResource(R.string.title_error_loading_results),
        subtitle = stringResource(R.string.subtitle_error_loading_image),
    )
}
