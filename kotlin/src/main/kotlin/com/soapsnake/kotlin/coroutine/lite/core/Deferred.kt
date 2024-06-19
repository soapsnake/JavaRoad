package main.kotlin.com.soapsnake.kotlin.coroutine.lite.core

interface Deferred<T> : Job {

    /**
     * 1. 协程已经执行完成时,立即返回协程的结果,如果协程异常结束,则抛出该异常.
     * 2. 如果协程尚未完成,则挂起直到协程执行完成,这一点与join类似.
     */
    suspend fun await(): T
}
