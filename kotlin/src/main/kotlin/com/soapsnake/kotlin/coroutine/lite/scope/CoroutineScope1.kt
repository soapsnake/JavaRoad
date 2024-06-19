package main.kotlin.com.soapsnake.kotlin.coroutine.lite.scope

import main.kotlin.com.soapsnake.kotlin.coroutine.lite.core.AbstractCoroutine
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.startCoroutine
import kotlin.coroutines.suspendCoroutine

interface CoroutineScope1 {
    val scopeContext: CoroutineContext
}

suspend fun <R> coroutineScope1(block: suspend CoroutineScope1.() -> R): R =
    suspendCoroutine { continuation ->
        val coroutine = ScopeCoroutine(continuation.context, continuation)
        block.startCoroutine(coroutine, coroutine)
    }

internal open class ScopeCoroutine<T> (
    context: CoroutineContext,
    protected val continuation: Continuation<T>
) : AbstractCoroutine<T>(context) {
    override fun resumeWith(result: Result<T>) {
        super.resumeWith(result)
        continuation.resumeWith(result)
    }
}
