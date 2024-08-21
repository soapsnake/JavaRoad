package com.soapsnake.lite.dispose

import com.soapsnake.lite.core.Job
import com.soapsnake.lite.core.OnCancel

class CancellationHandlerDisposable(val job: Job, val onCancel: OnCancel) : Disposable {
    override fun dispose() {
        job.remove(this)
    }
}
