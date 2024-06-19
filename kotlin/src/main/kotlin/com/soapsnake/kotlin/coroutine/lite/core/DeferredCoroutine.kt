package main.kotlin.com.soapsnake.kotlin.coroutine.lite.core

import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.suspendCoroutine

class DeferredCoroutine<T> (context: CoroutineContext) : Deferred<T>, AbstractCoroutine<T>(context) {
    override val context: CoroutineContext
        get() = context

    /**
     * @see join
     * 与join函数的差异只在对结果的处理
     */
    override suspend fun await(): T {
        val currentState = state.get()
        return when (currentState) {
            is CoroutineState.Incomplete,
            is CoroutineState.Cancelling -> awaitSuspend()
            is CoroutineState.Complete<*> -> {
                currentState.exception?.let {
                    throw it
                } ?: (currentState.value as T)
            }
        }
    }

    /**
     * @see delay
     * @see joinSuspend
     */
    private suspend fun awaitSuspend() =
        suspendCoroutine<T> {
                continuation ->
            doOnCompleted { result ->
                continuation.resumeWith(result)
            }
        }
}
