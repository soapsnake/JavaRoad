package main.kotlin.com.soapsnake.kotlin.coroutine.lab

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.IOException

// sampleStart
// @OptIn(DelicateCoroutinesApi::class)
// fun main() = runBlocking {
//    val job = GlobalScope.launch { // launch 根协程
//        println("Throwing exception from launch")
//        throw IndexOutOfBoundsException() // 我们将在控制台打印 Thread.defaultUncaughtExceptionHandler
//    }
//    job.join()
//    println("Joined failed job")
//    val deferred = GlobalScope.async { // async 根协程
//        println("Throwing exception from async")
//        throw ArithmeticException() // 没有打印任何东西，依赖用户去调用等待
//    }
//    try {
//        deferred.await()
//        println("Unreached")
//    } catch (e: ArithmeticException) {
//        println("Caught ArithmeticException")
//    }
// }
// sampleEnd

@OptIn(DelicateCoroutinesApi::class)
fun main() = runBlocking {
    val handler = CoroutineExceptionHandler { _, exception ->
        println("CoroutineExceptionHandler got $exception with suppressed ${exception.suppressed.contentToString()}")
    }
    val job = GlobalScope.launch(handler) {
        launch {
            try {
                delay(Long.MAX_VALUE) // 当另一个同级的协程因 IOException  失败时，它将被取消
            } finally {
                throw ArithmeticException() // 第二个异常
            }
        }
        launch {
            delay(100)
            throw IOException() // 首个异常
        }
        delay(Long.MAX_VALUE)
    }
    job.join()
}
