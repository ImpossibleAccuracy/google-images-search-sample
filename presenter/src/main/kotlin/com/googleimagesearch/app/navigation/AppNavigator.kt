package com.googleimagesearch.app.navigation

import cafe.adriel.voyager.core.screen.Screen
import com.googleimagesearch.feature.feed.FeedPage
import com.googleimagesearch.feature.gallery.GalleryPage
import com.googleimagesearch.navigation.Navigator
import com.googleimagesearch.navigation.screen.SharedScreen

class AppNavigator(
    private val navigator: cafe.adriel.voyager.navigator.Navigator,
) : Navigator {
    override fun push(screen: SharedScreen) {
        navigator.push(getPageByScreen(screen))
    }

    override fun replace(screen: SharedScreen) {
        navigator.replace(getPageByScreen(screen))
    }

    override fun pop() {
        navigator.pop()
    }

    override fun popToRoot() {
        navigator.popUntilRoot()
    }

    private fun getPageByScreen(screen: SharedScreen): Screen =
        when (screen) {
            SharedScreen.Feed -> FeedPage()
            is SharedScreen.Gallery -> GalleryPage(screen.selected, screen.items)
        }
}
