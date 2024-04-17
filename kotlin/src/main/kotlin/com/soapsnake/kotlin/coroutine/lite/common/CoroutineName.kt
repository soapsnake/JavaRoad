package main.kotlin.com.soapsnake.kotlin.coroutine.lite.common

import kotlin.coroutines.CoroutineContext

class CoroutineName(val name: String) : CoroutineContext.Element {

    companion object Key : CoroutineContext.Key<CoroutineName>

    override val key = Key

    override fun toString(): String {
        return name
    }
}