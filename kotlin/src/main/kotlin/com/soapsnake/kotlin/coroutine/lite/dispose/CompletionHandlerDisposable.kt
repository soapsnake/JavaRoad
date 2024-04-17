package main.kotlin.com.soapsnake.kotlin.coroutine.lite.dispose

import main.kotlin.com.soapsnake.kotlin.coroutine.lite.core.Job

class CompletionHandlerDisposable<T>(val job: Job, val onComplete: (Result<T>) -> Unit) :
    Disposable {
        override fun dispose() {
            job.remove(this)
        }

}
