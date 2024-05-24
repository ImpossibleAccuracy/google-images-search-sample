package com.googleimagesearch.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf

val LocalNavigator = staticCompositionLocalOf<Navigator?> {
    null
}

val <T> ProvidableCompositionLocal<T?>.currentOrThrow: T
    @Composable
    @ReadOnlyComposable
    get() = current!!
