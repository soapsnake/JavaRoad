package main.kotlin.com.soapsnake.kotlin.coroutine.lab

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {
//sampleStart
    launch(Dispatchers.Default + CoroutineName("normal")) {
        println("I'm working in thread ${Thread.currentThread().name}")
    }
//sampleEnd
}