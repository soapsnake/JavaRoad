package main.kotlin.com.soapsnake.kotlin.coroutine.lab

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.runBlocking

// fun main() = runBlocking {
// //sampleStart
//    val channel = Channel<Int>()
//    launch {
//        // 这里可能是消耗大量 CPU 运算的异步逻辑，我们将仅仅做 5 次整数的平方并发送
//        for (x in 1..5) channel.send(x * x)
//    }
//    // 这里我们打印了 5 次被接收的整数：
//    repeat(5) { println(channel.receive()) }
//    log("Done!")
// //sampleEnd
// }

// fun main() = runBlocking {
// //sampleStart
//    val channel = Channel<Int>()
//    launch {
//        for (x in 1..5) channel.send(x * x)
//        channel.close() // 我们结束发送
//    }
//    // 这里我们使用 `for` 循环来打印所有被接收到的元素（直到通道被关闭）
//    for (y in channel) println(y)
//    log("Done!")
// //sampleEnd
// }

fun CoroutineScope.produceSquares(): ReceiveChannel<Int> = produce {
    for (x in 1..5) send(x * x)
}

fun main() = runBlocking {
// sampleStart
    val squares = produceSquares()
    squares.consumeEach { println(it) }
    println("Done!")
// sampleEnd
}
