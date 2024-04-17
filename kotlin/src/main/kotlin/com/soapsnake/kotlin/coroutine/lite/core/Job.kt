package main.kotlin.com.soapsnake.kotlin.coroutine.lite.core

import kotlin.coroutines.CoroutineContext
import main.kotlin.com.soapsnake.kotlin.coroutine.lite.dispose.Disposable

/**
 * 协程抽象, 类比Java的线程Thread来理解
 */
interface Job : CoroutineContext.Element {

    companion object Key : CoroutineContext.Key<Job>


    /**
     * 用于将协程的Job实例存入上下文,这样就可以从上下文中随意获取协程实例
     */
    override val key : CoroutineContext.Key<*> get() = Job


    /**
     * @see Thread.isAlive
     */
    val isActive : Boolean

    /**
     * 注册一个协程被取消时触发的回调函数
     */
    fun invokeOnCancel(onCancel : OnCancel) : Disposable

    /**
     * 注册一个协程完成时的回调函数
     */
    fun invokeOnCompletion(onComplete : OnComplete): Disposable

    /**
     * 类比线程的interrupt()
     * @see Thread.interrupt
     */
    fun cancel()

    /**
     * 用于移除回调(取消 or 完成)函数
     */
    fun remove(disposable : Disposable)

    /**
     * Thread的join会阻塞线程
     * 协程的joint则会挂起协程(yield?????)
     * @see Thread.join
     *
     * join的实现和delay思想一致
     * @see delay
     */
    suspend fun join()

}

typealias OnComplete = () -> Unit
typealias OnCancel = () -> Unit
