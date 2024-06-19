package main.kotlin.com.soapsnake.kotlin.coroutine.lite.scope

import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

object GlobalScope : CoroutineScope1 {
    override val scopeContext: CoroutineContext
        get() = EmptyCoroutineContext
}
