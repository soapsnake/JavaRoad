package main.kotlin.com.soapsnake.kotlin.coroutine.lite.exception

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlin.coroutines.AbstractCoroutineContextElement
import kotlin.coroutines.CoroutineContext

interface CoroutineExceptionHandler : CoroutineContext.Element {
    companion object Key : CoroutineContext.Key<CoroutineExceptionHandler>
    fun handleException(context: CoroutineContext, exception: Throwable)
}

inline fun CoroutineExceptionHandler(
    crossinline handler: (CoroutineContext, Throwable) -> Unit
): main.kotlin.com.soapsnake.kotlin.coroutine.lite.exception.CoroutineExceptionHandler =
    object :
        AbstractCoroutineContextElement(main.kotlin.com.soapsnake.kotlin.coroutine.lite.exception.CoroutineExceptionHandler),
        main.kotlin.com.soapsnake.kotlin.coroutine.lite.exception.CoroutineExceptionHandler {
        override fun handleException(context: CoroutineContext, exception: Throwable) {
            handler.invoke(context, exception)
        }
    }
