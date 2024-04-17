package main.kotlin.com.soapsnake.kotlin.coroutine.lite.dispose

import main.kotlin.com.soapsnake.kotlin.coroutine.lite.core.Job
import main.kotlin.com.soapsnake.kotlin.coroutine.lite.core.OnCancel

class CancellationHandlerDisposable(val job: Job, val onCancel: OnCancel) : Disposable {
    override fun dispose() {
        job.remove(this)
    }
}