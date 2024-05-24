package com.googleimagesearch.navigation.screen

import cafe.adriel.voyager.core.model.ScreenModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel

abstract class AppViewModel : ScreenModel {
    protected val viewModelScope = CoroutineScope(Dispatchers.Main)

    override fun onDispose() {
        viewModelScope.cancel()
    }
}