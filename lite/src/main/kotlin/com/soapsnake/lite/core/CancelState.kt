package com.soapsnake.lite.core

sealed class CancelState {

    object Incomplete : CancelState()

    // 取消状态的取消回调只允许注册一个
    class CancelHandler(val onCancel: OnCancel) : CancelState()

    class Complete<T>(val value: T? = null, val exception: Throwable? = null) : CancelState()

    object Cancelled : CancelState()
}

// 这个枚举用来标记对应的挂起函数是否同步返回
enum class CancelDecision {
    UNDECIDED, SUSPEND, RESUMED
}
