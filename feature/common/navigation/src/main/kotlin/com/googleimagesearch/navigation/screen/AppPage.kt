package com.googleimagesearch.navigation.screen

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel

interface AppPage : Screen

@Composable
inline fun <reified T : ScreenModel> AppPage.viewModel(): T {
    return getScreenModel<T>()
}
