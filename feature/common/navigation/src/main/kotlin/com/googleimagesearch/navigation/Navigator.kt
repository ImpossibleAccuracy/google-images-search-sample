package com.googleimagesearch.navigation

import com.googleimagesearch.navigation.screen.SharedScreen

interface Navigator {
    fun push(screen: SharedScreen)

    fun replace(screen: SharedScreen)

    fun pop()

    fun popToRoot()
}
