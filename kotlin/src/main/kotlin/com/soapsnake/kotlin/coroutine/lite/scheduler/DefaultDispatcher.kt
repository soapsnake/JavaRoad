package main.kotlin.com.soapsnake.kotlin.coroutine.lite.scheduler

import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicInteger

object DefaultDispatcher : Dispatcher {

    private val threadGroup = ThreadGroup("DefaultDispatcher")

    private val threadIndex = AtomicInteger(0)

    private val executor = Executors.newFixedThreadPool(
        Runtime.getRuntime().availableProcessors() + 1
    ) { runnable ->
        Thread(
            threadGroup,
            runnable,
            "soapsnake-${threadGroup.name}-worker-${threadIndex.getAndIncrement()}"
        )
            // 如果Java虚拟机中只剩幽灵线程，虚拟机会直接退出，我们这样设置的目的是希望调度器在后台空载的时候不要阻碍虚拟机的退出。
            .apply { isDaemon = true }
    }

    override fun dispatch(block: () -> Unit) {
        executor.submit(block)
    }
}
