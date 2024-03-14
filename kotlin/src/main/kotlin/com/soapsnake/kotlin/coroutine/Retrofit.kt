package main.kotlin.com.soapsnake.kotlin.coroutine

import java.io.IOException
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.startCoroutine
import kotlin.coroutines.suspendCoroutine
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface GitHubApi {
    @GET("users/{login}")
    fun getUserCallBack(@Path("login") login : String) : Call
}

class GitHubApiImpl : GitHubApi {
    override fun getUserCallBack(login: String): Call {
        TODO("Not yet implemented")
    }
}

fun main() {
val res = GitHubApiImpl()
    async {
//        val user = await {
//            res.getUserCallBack("bennyhuo") }
//            println(user)
    }
}


interface AsyncScope
fun async(context: CoroutineContext = EmptyCoroutineContext,
          block : suspend AsyncScope.() -> Unit) {
    val completion = AsyncCoroutine(context)
    block.startCoroutine(completion, completion)
}


class AsyncCoroutine(override val context: CoroutineContext = EmptyCoroutineContext) : Continuation<Unit>, AsyncScope {
    override fun resumeWith(result: Result<Unit>) {
        result.getOrThrow()
    }
}

suspend fun <T> AsyncScope.await(block: () -> Call) = suspendCoroutine<T> {
    continuation ->
    val call = block()
    call.enqueue(object : Callback {
        override fun onFailure(p0: Call, p1: IOException) {
            continuation.resumeWithException(p1)
        }

        override fun onResponse(p0: Call, p1: Response) {
            if(p1.isSuccessful) {
                p1.body()?.let {
                    continuation::resume  //如果callback成功回调那就直接resume
                } ?: continuation.resumeWithException(NullPointerException())
            } else {
//                continuation.resumeWithException(HttpException(httprep1))
            }
        }
    })
}