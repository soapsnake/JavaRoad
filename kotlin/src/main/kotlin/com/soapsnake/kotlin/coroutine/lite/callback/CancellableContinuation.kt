package main.kotlin.com.soapsnake.kotlin.coroutine.lite.callback

import main.kotlin.com.soapsnake.kotlin.coroutine.lite.core.CancelDecision
import main.kotlin.com.soapsnake.kotlin.coroutine.lite.core.CancelState
import main.kotlin.com.soapsnake.kotlin.coroutine.lite.core.Job
import main.kotlin.com.soapsnake.kotlin.coroutine.lite.core.OnCancel
import java.util.concurrent.atomic.AtomicReference
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.cancellation.CancellationException

class CancellableContinuation<T>(private val continuation: Continuation<T>) :
    Continuation<T> by continuation {
    private val state = AtomicReference<CancelState>(CancelState.Incomplete)
    private val decision = AtomicReference(CancelDecision.UNDECIDED)

    val isCompleted: Boolean
        get() = when (state.get()) {
            CancelState.Incomplete,
            is CancelState.CancelHandler -> false
            is CancelState.Complete<*>,
            CancelState.Cancelled -> true
        }

    fun invokeOnCancellation(onCancel: OnCancel) {
        val newState = state.updateAndGet {
                prev ->
            when (prev) {
                CancelState.Incomplete -> CancelState.CancelHandler(onCancel)
                is CancelState.CancelHandler -> throw IllegalStateException("Prohibited")
                is CancelState.Complete<*>, CancelState.Cancelled -> prev
            }
        }
        if (newState is CancelState.Cancelled) {
            onCancel()
        }
    }

    private fun installCancelHandler() {
        if (isCompleted) return
        val parent = continuation.context[Job] ?: return
        parent.invokeOnCancel {
            doCancel()
        }
    }

    private fun doCancel() {
        val prevState = state.getAndUpdate {
                prev ->
            when (prev) {
                is CancelState.CancelHandler,
                CancelState.Incomplete -> {
                    CancelState.Cancelled
                }
                CancelState.Cancelled,
                is CancelState.Complete<*> -> {
                    prev
                }
            }
        }
        if (prevState is CancelState.CancelHandler) {
            prevState.onCancel()
//            resumeWith Exception (Cancellation Exception("Cancelled."))
        }
    }

    fun getResult(): Any? {
        installCancelHandler()
        if (decision.compareAndSet(CancelDecision.UNDECIDED, CancelDecision.SUSPEND)) {
            // todo 应该是COROUTINE_SUSPEND????
            return CancelDecision.SUSPEND
        }
        return when (val currentState = state.get()) {
            is CancelState.CancelHandler, CancelState.Incomplete -> CancelDecision.SUSPEND
            CancelState.Cancelled -> throw CancellationException("Continuation is cancelled.")
            is CancelState.Complete<*> -> {
                (currentState as CancelState.Complete<T>).let {
                    it.exception?.let { throw it } ?: it.value
                }
            }
        }
    }

    override val context: CoroutineContext
        get() = TODO("Not yet implemented")

    override fun resumeWith(result: Result<T>) {
        when {
            decision.compareAndSet(CancelDecision.UNDECIDED, CancelDecision.RESUMED) -> {
                state.set(CancelState.Complete(result.getOrNull(), result.exceptionOrNull()))
            }
            decision.compareAndSet(CancelDecision.SUSPEND, CancelDecision.RESUMED) -> {
                state.updateAndGet { prev ->
                    when (prev) {
                        is CancelState.Complete<*> -> {
                            throw IllegalStateException("Already completed.")
                        }
                        else -> {
                            CancelState.Complete(result.getOrNull(), result.exceptionOrNull())
                        }
                    }
                }
                continuation.resumeWith(result)
            }
        }
    }

    fun intercepted(): Continuation<T> {
        TODO("Not yet implemented")
    }
}
