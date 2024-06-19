package main.kotlin.com.soapsnake.kotlin.coroutine.lab

import kotlinx.coroutines.Job
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {
// sampleStart
    println("My job is ${coroutineContext[Job]}")
// sampleEnd
}
