package main.kotlin.com.soapsnake.kotlin.coroutine.lite.core

import kotlin.coroutines.CoroutineContext
import main.kotlin.com.soapsnake.kotlin.coroutine.lite.exception.CoroutineExceptionHandler

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