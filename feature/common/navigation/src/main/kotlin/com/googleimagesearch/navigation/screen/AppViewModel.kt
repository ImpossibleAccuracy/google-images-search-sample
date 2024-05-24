package com.googleimagesearch.navigation.screen

import cafe.adriel.voyager.core.model.ScreenModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import java.io.Closeable
import kotlin.coroutines.CoroutineContext

abstract class AppViewModel : ScreenModel {
    protected val viewModelScope: CoroutineScope by lazy {
        CloseableCoroutineScope(
            SupervisorJob() + Dispatchers.Main
        )
    }

    override fun onDispose() {
        viewModelScope.cancel()
    }
}

internal class CloseableCoroutineScope(context: CoroutineContext) : Closeable, CoroutineScope {
    override val coroutineContext: CoroutineContext = context

    override fun close() {
        coroutineContext.cancel()
    }
}
