package main.kotlin.com.soapsnake.kotlin.coroutine.lab

import kotlinx.coroutines.Job
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {
//sampleStart
    println("My job is ${coroutineContext[Job]}")
//sampleEnd

    val map = mapOf("opp_user_id" to "fdafdasfa",
                    "123" to "jfdajfidsa")
    val newMap = map.toMutableMap().apply {
        remove("jfdaofdoias")
    }
    println(newMap)
}