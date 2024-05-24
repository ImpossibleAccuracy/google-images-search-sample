package com.googleimagesearch.app.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import com.googleimagesearch.app.navigation.AppNavigator
import com.googleimagesearch.app.ui.theme.GoogleImageSearchTheme
import com.googleimagesearch.feature.feed.FeedPage
import com.googleimagesearch.navigation.LocalNavigator

@Composable
fun App() {
    GoogleImageSearchTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
        ) {
            Navigator(
                screen = FeedPage(),
                onBackPressed = {
                    true
                }
            ) { navigator ->
                val appNavigator = remember(navigator) { AppNavigator(navigator) }

                CompositionLocalProvider(LocalNavigator provides appNavigator) {
                    CurrentScreen()
                }
            }
        }
    }
}
