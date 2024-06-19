package main.kotlin.com.soapsnake.kotlin.coroutine.lite.core

import main.kotlin.com.soapsnake.kotlin.coroutine.lab.coroutineContext
import main.kotlin.com.soapsnake.kotlin.coroutine.lite.core.CoroutineState.Incomplete
import main.kotlin.com.soapsnake.kotlin.coroutine.lite.dispose.CancellationHandlerDisposable
import main.kotlin.com.soapsnake.kotlin.coroutine.lite.dispose.CompletionHandlerDisposable
import main.kotlin.com.soapsnake.kotlin.coroutine.lite.dispose.Disposable
import main.kotlin.com.soapsnake.kotlin.coroutine.lite.scope.CoroutineScope1
import main.kotlin.com.soapsnake.kotlin.coroutine.lite.suspendCancellableCoroutine
import java.util.concurrent.atomic.AtomicReference
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.cancellation.CancellationException
import kotlin.coroutines.resume

/**
 * Job的抽象子类
 */
abstract class AbstractCoroutine<T> (context: CoroutineContext) : Job, Continuation<T>, CoroutineScope1 {

    protected val parentJob = context[Job]

    private val parentCancelDisposable: Disposable?

    init {
        parentCancelDisposable = parentJob?.invokeOnCancel {
            cancel()
        }
    }

    protected val state = AtomicReference<CoroutineState>()

    override val context: CoroutineContext

    override val scopeContext: CoroutineContext
        get() = context

    init {
        state.set(Incomplete())
        this.context = context + this
    }

    val isCompleted
        get() = state.get() is CoroutineState.Complete<*>

    override val isActive: Boolean
        get() = when (state.get()) {
            is CoroutineState.Complete<*> -> false
            is CoroutineState.Cancelling -> false
            else -> true
        }

    /**
     * 注册回调的过程分成三步:
     * 1. 构造一个CompletionHandlerDisposable对象
     * 2. 检查状态,并将回调添加得到状态中
     * 3.状态流转成功后,可以获得最终的状态,如果此时已经是完成状态,表明新回调没有注册到状态中,此时需要立刻调用回调.
     */
    protected fun doOnCompleted(block: (Result<T>) -> Unit): Disposable {
        val disposable = CompletionHandlerDisposable(this, block)
        val newState = state.updateAndGet { prev ->
            when (prev) {
                is Incomplete -> {
                    Incomplete().from(prev).with(disposable)
                }
                is CoroutineState.Cancelling -> {
                    CoroutineState.Cancelling().from(prev).with(disposable)
                }
                is CoroutineState.Complete<*> -> prev
            }
        }

        // 如果新状态是已完成,需要立刻调用回调
        (newState as? CoroutineState.Complete<T>)?.let {
            block(
                when {
                    it.value != null -> Result.success(it.value)
                    it.exception != null -> Result.failure(it.exception)
                    else -> throw IllegalStateException("Won't happen.")
                }
            )
        }
        return disposable
    }

    override fun cancel() {
        val prevState = state.getAndUpdate { prev ->
            when (prev) {
                is Incomplete -> {
                    CoroutineState.Cancelling()
                }
                is CoroutineState.Cancelling,
                is CoroutineState.Complete<*> -> prev
            }
        }
        if (prevState is Incomplete) {
            prevState.notifyCancellation()
            prevState.clear()
        }
//        parentCancelDisposable?.dispose()
    }

    override suspend fun join() {
        when (state.get()) {
            is Incomplete, is CoroutineState.Cancelling -> return joinSuspend()
            is CoroutineState.Complete<*> -> {
                val currentCallingJobState = coroutineContext[Job]?.isActive ?: return
                if (!currentCallingJobState) {
                    throw CancellationException("coroutine is cancelled!!")
                }
                return
            }
        }
    }

    /**
     * @see delay
     */
    private suspend fun joinSuspend() =
        // suspendCoroutine 会挂起当前协程
        suspendCancellableCoroutine<Unit> { continuation ->
            val dispos: Disposable = doOnCompleted { result ->
                continuation.resume(Unit)
            }
            continuation.invokeOnCancellation { dispos.dispose() }
        }

    override fun resumeWith(result: Result<T>) {
        val newState = state.updateAndGet { prev ->
            when (prev) {
                is CoroutineState.Cancelling,
                is Incomplete -> {
                    CoroutineState.Complete(result.getOrNull(), result.exceptionOrNull()).from(prev)
                }
                is CoroutineState.Complete<*> -> {
                    throw IllegalStateException("Already completed!")
                }
            }
        }
        newState.notifyCompletion(result)
        newState.clear()
    }

    override fun invokeOnCancel(onCancel: OnCancel): Disposable {
        val disposable = CancellationHandlerDisposable(this, onCancel)
        val newState = state.updateAndGet { prev ->
            when (prev) {
                is Incomplete -> {
                    Incomplete().from(prev).with(disposable)
                }
                is CoroutineState.Cancelling,
                is CoroutineState.Complete<*> -> {
                    prev
                }
            }
        }
        (newState as? CoroutineState.Cancelling)?.let {
            onCancel()
        }
        return disposable
    }

    /**
     * 完成回调的注册
     */
    override fun invokeOnCompletion(onComplete: OnComplete): Disposable {
        return doOnCompleted { _ -> onComplete() }
    }

    /**
     * 移除注册的回调
     */
    override fun remove(disposable: Disposable) {
        state.updateAndGet { prev ->
            when (prev) {
                is Incomplete -> {
                    Incomplete().from(prev).without(disposable)
                }
                is CoroutineState.Cancelling -> {
                    CoroutineState.Cancelling().from(prev).without(disposable)
                }
                is CoroutineState.Complete<*> -> {
                    prev
                }
            }
        }
    }

    override fun toString(): String {
        TODO("Not yet implemented")
    }

    protected open fun handleJobException(e: Throwable) = false
}
