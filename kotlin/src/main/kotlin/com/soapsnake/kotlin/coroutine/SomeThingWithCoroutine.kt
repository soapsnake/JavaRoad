package main.kotlin.com.soapsnake.kotlin.coroutine

import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.RestrictsSuspension
import kotlin.coroutines.createCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.startCoroutine
import kotlinx.coroutines.delay


suspend fun main() {
    val continuation = suspend {
        println("In Coroutine.")
        5
    }.createCoroutine(object : Continuation<Int> {
        override fun resumeWith(result: Result<Int>) {
            //完成回调
            println("Coroutine End : $result")
        }

        override val context = EmptyCoroutineContext
    })

    continuation.resume(Unit)


    val continuation2 = suspend {
        println("In Coroutine2.")
        10
    }.startCoroutine(object : Continuation<Int> {
        override val context: CoroutineContext
            get() = EmptyCoroutineContext

        override fun resumeWith(result: Result<Int>) {
            println("Coroutine2 End : $result")
        }
    })


    ProducerScope<Int>().callLaunchCoroutine()
}

fun <R, T> launchCoroutine(receiver: R, block: suspend R.() -> T) {
    block.startCoroutine(receiver, object : Continuation<T> {
        override val context: CoroutineContext
            get() = EmptyCoroutineContext

        override fun resumeWith(result: Result<T>) {
            println("Coroutine End : $result")
        }
    })
}

class ProducerScope<T> {
    private suspend fun produce(value : T) {
        println("ProduceScope::produce being called => $value")
    }

    fun callLaunchCoroutine() {
        launchCoroutine(ProducerScope<Int>()) {
            println("In Coroutine.")
            this.produce(1024)
            delay(1000)
            produce(2049)
        }
    }
}

@RestrictsSuspension
class RestrictProducerScope<T> {
    suspend fun produce(value : T) {
        println("ProduceScope::produce being called => $value")
    }

    fun callLaunchCoroutineRestricted() {
        launchCoroutine(RestrictProducerScope<Int>()) {
            println("In Coroutine.")
            this.produce(1024)
//            delay(1000)   //can't call external function because it's RestrictsSuspension
            produce(2049)
        }
    }
}