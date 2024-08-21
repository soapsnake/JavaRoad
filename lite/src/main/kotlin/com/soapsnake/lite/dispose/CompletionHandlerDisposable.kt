package com.soapsnake.lite.dispose

import com.soapsnake.lite.core.Job

class CompletionHandlerDisposable<T>(val job: Job, val onComplete: (Result<T>) -> Unit) :
    Disposable {
    override fun dispose() {
        job.remove(this)
    }
}
