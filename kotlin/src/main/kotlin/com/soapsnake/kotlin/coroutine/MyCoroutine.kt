package main.kotlin.com.soapsnake.kotlin.coroutine

import kotlin.coroutines.Continuation
import kotlin.coroutines.ContinuationInterceptor
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

var list: List<Int> = emptyList()
var coroutineContext : CoroutineContext = EmptyCoroutineContext


class CoroutineName(private val name: String) : CoroutineContext.Element {

    companion object Key: CoroutineContext.Key<CoroutineName>

    override val key: CoroutineContext.Key<*>
        get() = Key

    override fun toString(): String {
        return this.name
    }
}

interface CoroutineExceptionHandler : CoroutineContext.Element {

    companion object Key : CoroutineContext.Key<CoroutineExceptionHandler>

    fun handleException(context: CoroutineContext, exception: Throwable)
}


class LogInterceptor : ContinuationInterceptor {
    override val key = ContinuationInterceptor

    override fun <T> interceptContinuation(continuation: Continuation<T>)
            = LogContinuation(continuation)
}

class LogContinuation<T>(private val continuation: Continuation<T>)
    : Continuation<T> by continuation {
    override fun resumeWith(result: Result<T>) {
        println("before resumeWith: $result")
          continuation.resumeWith(result)
        println("after resumeWith.")
    }
}



