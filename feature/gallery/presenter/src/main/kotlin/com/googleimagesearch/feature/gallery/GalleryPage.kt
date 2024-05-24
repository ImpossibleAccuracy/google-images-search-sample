package com.googleimagesearch.feature.gallery

import android.app.Activity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import cafe.adriel.voyager.core.lifecycle.LifecycleEffect
import com.googleimagesearch.domain.model.SearchResult
import com.googleimagesearch.navigation.screen.AppPage
import kotlinx.collections.immutable.ImmutableList

@OptIn(ExperimentalFoundationApi::class)
data class GalleryPage(
    val selectedItem: SearchResult.GoogleImage?,
    val searchResults: ImmutableList<SearchResult.GoogleImage>,
) : AppPage {
    @Composable
    override fun Content() {
        val pagerState = rememberPagerState(
            pageCount = { searchResults.size },
            initialPage = if (selectedItem == null) 0 else searchResults.indexOf(selectedItem)
        )

//        HandleSystemUi()

        GalleryScreen(
            modifier = Modifier.fillMaxWidth(),
            pagerState = pagerState,
            searchResults = searchResults
        )
    }

    @Composable
    private fun HandleSystemUi() {
        val view = LocalView.current

        LifecycleEffect(
            onStarted = {
                val activity = view.context as Activity
                enableImmersiveMode(activity)
            },
            onDisposed = {
                val activity = view.context as Activity
                disableImmersiveMode(activity)
            }
        )
    }

    private fun enableImmersiveMode(activity: Activity) {
        val window = activity.window

        WindowCompat.setDecorFitsSystemWindows(window, true)

        val windowInsetsController =
            WindowCompat.getInsetsController(window, window.decorView)

        windowInsetsController.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE

        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars())
    }

    private fun disableImmersiveMode(activity: Activity) {
        val window = activity.window

        WindowCompat.setDecorFitsSystemWindows(window, false)

        val windowInsetsController =
            WindowCompat.getInsetsController(window, window.decorView)

        windowInsetsController.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_DEFAULT

        windowInsetsController.show(WindowInsetsCompat.Type.systemBars())
    }
}
