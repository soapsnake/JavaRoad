package com.soapsnake.lite.core

import com.soapsnake.lite.dispose.CancellationHandlerDisposable
import com.soapsnake.lite.dispose.CompletionHandlerDisposable
import com.soapsnake.lite.dispose.Disposable
import com.soapsnake.lite.dispose.DisposableList
import com.soapsnake.lite.dispose.loopOn
import com.soapsnake.lite.dispose.remove

sealed class CoroutineState {

    private var disposableList: DisposableList = DisposableList.Nil

    /**
     * “协程启动后立即进入该状态，直到完成或者被取消”
     *
     */
    class Incomplete : CoroutineState()

    /**
     * “协程执行中被取消后进入该状态。进入该状态后，要等待协程体内部的挂起函数调用响应取消，
     * 响应后协程成功被取消抛出CancellationException取消，否则正常执行完成，
     * 两种情况都会调用完成回调的恢复调用将状态流转为Complete，只是结果不同”
     */
    class Cancelling : CoroutineState()

    /**
     * “协程执行完成（包括正常返回和异常结束）时进入该状态”
     */
    class Complete<T>(val value: T? = null, val exception: Throwable? = null) : CoroutineState()

    /**
     * “在创建新状态的时候，使用from即可拿到上一个状态的所有回调”
     */
    fun from(state: CoroutineState): CoroutineState {
        this.disposableList = state.disposableList
        return this
    }

    /**
     * 创建一个新的state,将会把上一个state的回调拿出来,塞给新的state
     */
    fun with(disposable: Disposable): CoroutineState {
        this.disposableList = DisposableList.Cons(disposable, this.disposableList)
        return this
    }

    /**
     * 创建新的state时候不携带原来的回调
     */
    fun without(disposable: Disposable): CoroutineState {
        this.disposableList = this.disposableList.remove(disposable)
        return this
    }

    fun clear() {
        this.disposableList = DisposableList.Nil
    }

    fun <T> notifyCompletion(result: Result<T>) {
        // 挨个遍历所有已经注册的完成回调,并且调用这些完成回调
        this.disposableList.loopOn<CompletionHandlerDisposable<T>> {
            it.onComplete(result)
        }
    }

    fun notifyCancellation() {
        disposableList.loopOn<CancellationHandlerDisposable> {
            it.onCancel()
        }
    }
}
