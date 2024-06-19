package main.kotlin.com.soapsnake.kotlin.coroutine.lab

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun simple(): Sequence<Int> = sequence { // 序列构建器
    for (i in 1..3) {
        Thread.sleep(1000) // 假装我们正在计算
        yield(i) // 产生下一个值
    }
}

suspend fun simple1(): List<Int> {
    delay(10000) // 假装我们在这里做了一些异步的事情
    return listOf(1, 2, 3)
}

fun simple2(): Flow<Int> = flow { // 流构建器
    for (i in 1..3) {
        delay(1000) // 假装我们在这里做了一些有用的事情
        emit(i) // 发送下一个值
    }
}

fun main() {
    simple().forEach { value -> println(value) }

    runBlocking {
        simple1().forEach { value -> println(value) }
    }
    println("finally it complete")

    runBlocking {
        launch {
            for (k in 1..3) {
                println("I'm not blocked $k")
                delay(10000)
            }
        }
        simple2().collect { value -> println(value) }
    }
}
