package main.kotlin.com.soapsnake.kotlin.coroutine

import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.createCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.startCoroutine
import kotlin.coroutines.suspendCoroutine


interface Generator<T> {
    operator fun iterator() : Iterator<T>
}

interface GeneratorScope<T> {
    suspend fun yield(value: T)
}

fun <T> generator(block: suspend GeneratorScope<T>.(T) -> Unit): (T) -> Generator<T> {
    return {
        GeneratorImpl(block)
    }
}

class GeneratorImpl<T>(block: suspend GeneratorScope<T>.(T) -> Unit) : Generator<T> {
    override fun iterator(): Iterator<T> {
        TODO("Not yet implemented")
    }

}

class GeneratorIterator<T> (
    private val block : suspend GeneratorScope<T>.(T) -> Unit,
    private val parameter : T
    ) : GeneratorScope<T>, Iterator<T>, Continuation<Any?> {
    override val context: CoroutineContext
        get() = EmptyCoroutineContext

    private var state: State

    init {
        val coroutineBlock : suspend GeneratorScope<T>.() -> Unit = { block(parameter) }
        val start = coroutineBlock.createCoroutine(this, this)
        state = State.NotReady(start)
    }

    override fun hasNext(): Boolean {
        resume()
        return state != State.Done
    }

    private fun resume() {
        when(val currentState = state) {
            is State.NotReady -> currentState.continuation.resume(Unit)
            is State.Done -> TODO()
            is State.Ready<*> -> TODO()
        }
    }

    override fun next(): T {
        return when(val currentState = state) {
            is State.NotReady -> {
                resume()
                return next()
            }
            is State.Ready<*> -> {
                state = State.NotReady(currentState.continuation)
                (currentState as State.Ready<T>).nextValue
            }
            is State.Done -> throw IndexOutOfBoundsException("No value left.")
        }
    }

    override fun resumeWith(result: Result<Any?>) {
        state = State.Done
        result.getOrThrow()
    }

    override suspend fun yield(value: T) = suspendCoroutine {
        continuation ->
        state = when(state) {
            is State.NotReady -> State.Ready(continuation, value)
            is State.Ready<*> -> throw  IllegalStateException("cannot yield while ready.")
            is State.Done -> throw IllegalStateException("cannot yield while done.")
        }
    }
}

sealed class State {

    /**
     * “NotReady：下一个元素尚未就绪，通常是挂起后，尚未恢复执行时的情况，此时由于生成器函数尚未执行，
     * 后续是否存在新元素仍然未知，需要恢复执行之后确定。Continuation记录了当前生成器挂起的位置，用于后续恢复生成器的执行”
     */
    class NotReady(val continuation : Continuation<Unit>) : State()

    /**
     * “Ready：恢复执行后，再次遇到yield调用产生新元素时进入该状态，此时生成器挂起。
     * Continuation记录了当前生成器挂起的位置，用于后续恢复生成器的执行”
     */
    class Ready<T> (val continuation: Continuation<Unit>, val nextValue: T): State()

    /**
     * “Done：生成器已经执行完毕，无新元素产生”
     */
    data object Done : State()
}


fun main() {
//    val nums = generator<Int> { start: Int ->
//        for (i in 0..5) {
//            yield(start + i)
//        }
//    }
//    val gen = nums(10)
//    for (j in gen) {
//        println(j)
//    }

//    val sequence = sequence<Int> {
//        yield(1)
//        yield(2)
//        yield(3)
//        yield(4)
//        yield(5)
//        yield(6)
//        yield(7)
//        yield(8)
//        yieldAll(listOf(9,10,11))
//    }
//
//    for (element in sequence) {
//        println(element)
//    }

    val fibnocci = sequence {
        yield(1L)
        var current = 1L
        var next = 1L
        while(true) {
            yield(next)
            next += current
            current = next - current
        }
    }
    fibnocci.take(10).forEach(::println)


    suspend {
        val user = getUser()
        println(user)
    }.startCoroutine(completion = TODO())

}

suspend fun getUser(): User = suspendCoroutine {

}

class User{

}

