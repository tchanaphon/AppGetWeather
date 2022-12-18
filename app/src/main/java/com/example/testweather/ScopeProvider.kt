package com.example.testweather

import dagger.hilt.android.internal.Contexts
import kotlinx.coroutines.*
import java.io.Closeable
import kotlin.coroutines.CoroutineContext

public val ScopeProvider: CoroutineScope
    get() {
        var scope: CoroutineScope? = null
        if (scope != null)
            return scope
        return CloseableCoroutineScope(SupervisorJob() + Dispatchers.IO)
    }

internal class CloseableCoroutineScope(context:CoroutineContext) : Closeable, CoroutineScope{
    override val coroutineContext: CoroutineContext = context

    override fun close() {
        coroutineContext.cancel()
    }
}