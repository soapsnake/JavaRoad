package main

import kotlin.coroutines.AbstractCoroutineContextElement
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.startCoroutine
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

/**
 * Created on 2024-01-28
 */
class PlayWithCouroutine {


}

suspend fun main() {

    testCoroutine()
}

suspend fun testCoroutine() {
    println("${Thread.currentThread().name} => 123")  //打印顺序: 1

    runBlocking {
        println("${Thread.currentThread().name} =>start...") //打印顺序: 2

        val list = arrayListOf<Job>()
        val job1 = launch {
            println("${Thread.currentThread().name} =>Coroutine job1 started") //打印顺序: 4
            delay(5000L)
            println("${Thread.currentThread().name} =>Coroutine job1 completed") //打印顺序: 5
        }
        list.add(job1)

        val job2 = launch {
            println("${Thread.currentThread().name} =>Coroutine job2 started") //打印顺序: 4
            delay(6000L)
            println("${Thread.currentThread().name} =>Coroutine job2 completed") //打印顺序: 5
        }
        list.add(job2)
        println("${Thread.currentThread().name} =>after two jobs launched")  //打印顺序: 3
        list.joinAll()

        println("${Thread.currentThread().name} =>end") //打印顺序: 6
    }

    globalTest()

        coroutineTest()

    println("${Thread.currentThread().name} =>main end") //打印顺序: 7

}

@OptIn(DelicateCoroutinesApi::class)
fun globalTest() {
    println("Global launch will start")
    GlobalScope.launch {
        delay(3000)
        print("GlobalScope.launch------------>")
    }
    println("Global launch  end")
}

suspend fun coroutineTest() {
    println("coroutine launch will start")
    val job = withContext(Dispatchers.IO) {
        launch {
            delay(2000)
            println("coroutine launch already complete")
        }
        async {
            delay(1000)
            println("coroutine async already complete")
        }.await()
    }
    println("coroutine launch end")
}

fun testEmpty() {
    var emptyList : List<Int> = mutableListOf()
    emptyList += 0
    var emptyCoroutine : CoroutineContext = EmptyCoroutineContext
}

suspend fun testSuspend() = withContext(Dispatchers.IO) {
    suspend {
        println("this is suspend")
        delay(1000)
    }.startCoroutine(object : Continuation<Unit> {
        override val context: CoroutineContext
            get() = TODO("Not yet implemented")

        override fun resumeWith(result: Result<Unit>) {
            TODO("Not yet implemented")
        }

    })

}


class CoroutineName(val name : String) : AbstractCoroutineContextElement(CoroutineName.Key) {

}


