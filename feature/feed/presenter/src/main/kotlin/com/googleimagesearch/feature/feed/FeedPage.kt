package com.googleimagesearch.feature.feed

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import cafe.adriel.voyager.navigator.currentOrThrow
import com.googleimagesearch.navigation.LocalNavigator
import com.googleimagesearch.navigation.screen.AppPage
import com.googleimagesearch.navigation.screen.SharedScreen
import com.googleimagesearch.navigation.screen.viewModel
import kotlinx.collections.immutable.toImmutableList

class FeedPage : AppPage {
    @Composable
    override fun Content() {
        val viewModel: FeedViewModel = viewModel()
        val navigator = LocalNavigator.currentOrThrow

        val uiState by viewModel.uiState.collectAsState()
        val searchResults = viewModel.searchResults.collectAsLazyPagingItems()

        FeedScreen(
            modifier = Modifier.fillMaxSize(),
            uiState = uiState,
            searchResults = searchResults,
            newIntent = viewModel::onIntent,
            onOpenImage = { image ->
                navigator.push(
                    SharedScreen.Gallery(
                        selected = image,
                        items = searchResults.itemSnapshotList
                            .map { it!! }
                            .toImmutableList()
                    )
                )
            }
        )
    }
}
