package com.soapsnake.lite.scheduler

import javax.swing.SwingUtilities

/**
 * “调度的具体过程其实就是在delegate（也就是真正协程恢复时要执行的逻辑）的恢复调用之前，通过dispatch将其调度到指定的调度器上。”
 */
interface Dispatcher {

    fun dispatch(block: () -> Unit)
}

object Dispatchers {
    val Default by lazy {
        DispatcherContext(DefaultDispatcher)
    }
    val Android by lazy {
        DispatcherContext(AndroidDispatcher)
    }
    val Swing by lazy {
        DispatcherContext(SwingDispatcher)
    }
}

object AndroidDispatcher : Dispatcher {

    override fun dispatch(block: () -> Unit) {
        TODO()
    }
}

object SwingDispatcher : Dispatcher {

    override fun dispatch(block: () -> Unit) {
        SwingUtilities.invokeLater(block)
    }
}
