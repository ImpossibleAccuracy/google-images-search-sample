package com.googleimagesearch.feature.feed

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.googleimagesearch.domain.model.SearchResult
import com.googleimagesearch.feature.feed.viewmodel.FeedIntent
import com.googleimagesearch.feature.feed.viewmodel.FeedUiState
import com.googleimagesearch.feature.feed.views.EmptyView

@Composable
fun FeedScreen(
    modifier: Modifier = Modifier,
    uiState: FeedUiState,
    searchResults: LazyPagingItems<SearchResult.GoogleImage>,
    onIntent: (FeedIntent) -> Unit,
    onOpenImage: (SearchResult.GoogleImage) -> Unit,
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        item {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(text = stringResource(R.string.label_image_search))
                },
                value = uiState.query,
                onValueChange = {
                    onIntent(FeedIntent.UpdateQuery(it))
                }
            )
        }

        with(searchResults.loadState) {
            if (refresh is LoadState.NotLoading) {
                items(
                    count = searchResults.itemCount,
                    key = {
                        val item = searchResults[it]!!

                        item.imageUrl
                    },
                ) { index ->
                    val item = searchResults[index]!!

                    Text(
                        modifier = Modifier.clickable {
                            onOpenImage(item)
                        },
                        text = item.imageUrl,
                    )
                }
            }

            when {
                !uiState.isSearchResultInit || refresh is LoadState.Loading || append is LoadState.Loading -> {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(24.dp),
                            contentAlignment = Alignment.Center,
                        ) {
                            CircularProgressIndicator(
                                color = MaterialTheme.colorScheme.secondary,
                            )
                        }
                    }
                }

                refresh is LoadState.NotLoading && searchResults.itemCount < 1 -> {
                    item {
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
                }

                refresh is LoadState.Error || append is LoadState.Error -> {
                    item {
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
                            title = stringResource(R.string.title_no_search_result),
                            subtitle = stringResource(R.string.subtitle_no_search_result),
                        )
                    }
                }
            }
        }
    }
}
