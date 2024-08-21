package com.soapsnake.lite.scope

import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

object GlobalScope : CoroutineScope1 {
    override val scopeContext: CoroutineContext
        get() = EmptyCoroutineContext
}
