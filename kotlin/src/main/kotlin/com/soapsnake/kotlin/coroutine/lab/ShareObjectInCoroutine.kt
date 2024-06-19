package main.kotlin.com.soapsnake.kotlin.coroutine.lab

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import kotlin.system.measureTimeMillis

suspend fun massiveRun(action: suspend () -> Unit) {
    val n = 100 // 启动的协程数量
    val k = 1000 // 每个协程重复执行同一动作的次数
    val time = measureTimeMillis {
        coroutineScope { // 协程的作用域
            repeat(n) {
                launch {
                    repeat(k) { action() }
                }
            }
        }
    }
    println("Completed ${n * k} actions in $time ms")
}

@OptIn(ObsoleteCoroutinesApi::class)
val counterContext = newSingleThreadContext("CounterContext") // 单线程

// sampleStart
var counter = 0

// fun main() = runBlocking {
//    withContext(Dispatchers.Default) {
//        massiveRun {
//            withContext(counterContext) {
//                counter++
//            }
//        }
//    }
//    println("Counter = $counter")
// }
// sampleEnd

val mutex = Mutex()
fun main() = runBlocking {
    withContext(Dispatchers.Default) {
        massiveRun {
            withContext(counterContext) {
                mutex.withLock {
                    counter++
                }
            }
        }
    }
    println("Counter = $counter")
}
