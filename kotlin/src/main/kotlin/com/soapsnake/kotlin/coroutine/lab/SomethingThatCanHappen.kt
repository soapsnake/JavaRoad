package com.soapsnake.kotlin.coroutine.lab

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


/**
 *凭啥我想定义一个suspend变量就不行，
 * 但是：@see kotlin.coroutines.coroutineContext 就可以
 */
//public suspend inline val test: CoroutineContext get() = TODO()



fun main(): Unit = runBlocking {

    println("main Running on ${Thread.currentThread().name}")


    // 使用默认调度器
    launch {
        println("task 1 Running on ${Thread.currentThread().name}")
        // 执行一些计算密集型任务
    }


    launch {
        println("task 2 Running on ${Thread.currentThread().name}")
        // 执行一些计算密集型任务
    }

    launch {
        println("task 3 Running on ${Thread.currentThread().name}")
        // 执行一些计算密集型任务
    }

    launch {
        println("task 4 Running on ${Thread.currentThread().name}")
        // 执行一些计算密集型任务
    }

    // 使用 IO 调度器
    launch(Dispatchers.IO) {
        println("Dispatchers.IO Running on ${Thread.currentThread().name}")
        // 执行一些 I/O 密集型任务
    }
}