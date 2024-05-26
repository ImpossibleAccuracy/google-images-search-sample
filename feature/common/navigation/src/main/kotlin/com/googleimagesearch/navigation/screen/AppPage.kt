package com.googleimagesearch.navigation.screen

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import org.koin.core.parameter.parametersOf

interface AppPage : Screen

@Composable
inline fun <reified T : ScreenModel> AppPage.viewModel(
    vararg parameters: Any?,
): T {
    return getScreenModel<T> {
        parametersOf(*parameters)
    }
}
