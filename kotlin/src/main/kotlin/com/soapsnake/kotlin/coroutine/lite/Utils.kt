package main.kotlin.com.soapsnake.kotlin.coroutine.lite

import kotlinx.coroutines.suspendCancellableCoroutine
import main.kotlin.com.soapsnake.kotlin.coroutine.lite.callback.CancellableContinuation
import main.kotlin.com.soapsnake.kotlin.coroutine.lite.common.CoroutineName
import main.kotlin.com.soapsnake.kotlin.coroutine.lite.core.Deferred
import main.kotlin.com.soapsnake.kotlin.coroutine.lite.core.DeferredCoroutine
import main.kotlin.com.soapsnake.kotlin.coroutine.lite.core.Job
import main.kotlin.com.soapsnake.kotlin.coroutine.lite.core.StandaloneCoroutine
import main.kotlin.com.soapsnake.kotlin.coroutine.lite.scheduler.Dispatchers
import main.kotlin.com.soapsnake.kotlin.coroutine.lite.scope.CoroutineScope1
import main.kotlin.com.soapsnake.kotlin.coroutine.lite.scope.GlobalScope
import main.kotlin.com.soapsnake.kotlin.coroutine.lite.scope.coroutineScope1
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicInteger
import kotlin.coroutines.Continuation
import kotlin.coroutines.ContinuationInterceptor
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.startCoroutine
import kotlin.coroutines.suspendCoroutine

private val threadIndex = AtomicInteger(0)
private val coroutineIndex = AtomicInteger(0)

private val executor = Executors.newScheduledThreadPool(1) { runnable ->
    Thread(runnable, "Scheduler-${threadIndex.getAndIncrement()}").apply { isDaemon = true }
}

suspend fun delay(time: Long, unit: TimeUnit = TimeUnit.MILLISECONDS) {
    if (time <= 0) {
        return
    }
    main.kotlin.com.soapsnake.kotlin.coroutine.lite.suspendCancellableCoroutine { continuation ->
        val future = executor.schedule({
            log("delay has been called")
            continuation.resume(Unit)
        }, time, unit)
        continuation.invokeOnCancellation { future.cancel(true) }
    }
}

fun CoroutineScope1.launch(context: CoroutineContext = EmptyCoroutineContext, block: suspend CoroutineScope1.() -> Unit): Job {
    // 默认启动带default调度器的协程
    val completion = StandaloneCoroutine(newCoroutineContext(context))
    block.startCoroutine(completion, completion)
    return completion
}

fun <T> CoroutineScope1.async(context: CoroutineContext = EmptyCoroutineContext, block: suspend CoroutineScope1.() -> T): Deferred<T> {
    // 默认启动带default调度器的协程
    val completion = DeferredCoroutine<T>(newCoroutineContext(context))
    block.startCoroutine(completion, completion)
    return completion
}

fun CoroutineScope1.newCoroutineContext(context: CoroutineContext): CoroutineContext {
    val combined = context + CoroutineName("@coroutine#${coroutineIndex.getAndIncrement()}}")
    return if (combined != Dispatchers.Default && combined[ContinuationInterceptor] == null) {
        combined + Dispatchers.Default
    } else {
        combined
    }
}

suspend fun getValue(): String {
    delay(10000L)
    return "HelloWorld"
}

fun log(msg: String) = println("[${Thread.currentThread().name}] => $msg")

// suspend inline fun <T> suspendCancellableCoroutine(
//    crossinline block: (CancellableContinuation<T>) -> Unit) : T = suspendCoroutineUninterceptedOrReturn {
//        continuation ->
//
// }
// )

/**
 * 不支持取消的挂起函数
 */
suspend fun nonCancellableFunction() = suspendCoroutine<Int> { continuation ->
    val comletableFuture = CompletableFuture.supplyAsync {
    }
    comletableFuture.thenApply {
        continuation.resume(it as Int)
    }.exceptionally {
        continuation.resumeWithException(it)
    }
}

/**
 * 支持取消的挂起函数,区别就是会更改continuation的状态,并且会注册一个cancel的回调函数,一旦continuation状态流转到cancel就会调用这个回调
 */
suspend fun cancellableFunction() = suspendCancellableCoroutine<Int> { continuation ->
    val completableFuture = CompletableFuture.supplyAsync {
    }
    continuation.invokeOnCancellation {
        completableFuture.cancel(true)
    }
}

suspend inline fun <T> suspendCancellableCoroutine(
    crossinline block: (CancellableContinuation<T>) -> Unit
): T =
    suspendCoroutineUninterceptedOrReturn {
        val cancellable = CancellableContinuation(it!!)
        block(cancellable)
        cancellable.getResult()
    }

suspend fun <T> suspendCoroutineUninterceptedOrReturn(function: (Continuation<T>?) -> Unit): T {
    TODO("Not yet implemented")
}

suspend fun main() {
//    val deferred = async {
//        getValue()
//    }
//    val result = deferred.await()
//    println(result)

    println("GlobalScope will start")
    GlobalScope.launch {
        println("GlobalScope will start1") // 这里是父协程

        GlobalScope.launch { // 这里仍旧是是父协程,因为GlobalScope是最顶层的作用域
            println("GlobalScope will start5")
        }

        launch { // 这里是子协程1号
            log("1") // log1将出现在main线程
            println("GlobalScope will start2")
            delay(2000)
            log("2")
        }.join() // log2将出现在delay的线程上

        println("GlobalScope will start3")
        launch(Dispatchers.Default) { // 这里是子协程2号
            log("3") // -> 这个将运行在default调度器的线程池里
            delay(2000)
            log("4") // log1和log2都将只出现在Default调度器的线程上
        }.join()

        println("GlobalScope will start4")
        async(Dispatchers.Swing) { // 这里是子协程3号
            log("5") // -> 这个将运行在default调度器的线程池里
            delay(2000)
            log("6") // log1和log2都将只出现在Default调度器的线程上
        }.join()
    }.join()

    coroutineScope1 {
        launch {
        }
    }
}
