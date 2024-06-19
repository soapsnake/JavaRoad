package main.kotlin.com.soapsnake.kotlin.coroutine.lite.core

import main.kotlin.com.soapsnake.kotlin.coroutine.lite.exception.CoroutineExceptionHandler
import kotlin.coroutines.CoroutineContext

class StandaloneCoroutine(coroutineContext: CoroutineContext) : AbstractCoroutine<Unit>(coroutineContext) {
    override fun handleJobException(e: Throwable): Boolean {
        super.handleJobException(e)
        context[CoroutineExceptionHandler]?.handleException(context, e)
            ?: Thread.currentThread().let {
                it.uncaughtExceptionHandler.uncaughtException(it, e)
            }
        return true
    }
}
