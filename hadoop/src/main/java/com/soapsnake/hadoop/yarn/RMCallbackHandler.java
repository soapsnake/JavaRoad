package com.soapsnake.hadoop.yarn;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang3.concurrent.AtomicInitializer;
import org.apache.hadoop.yarn.api.records.Container;
import org.apache.hadoop.yarn.api.records.ContainerStatus;
import org.apache.hadoop.yarn.api.records.NodeReport;
import org.apache.hadoop.yarn.api.records.UpdatedContainer;
import org.apache.hadoop.yarn.client.api.async.AMRMClientAsync;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-09-27
 */
@Slf4j
public class RMCallbackHandler extends AMRMClientAsync.AbstractCallbackHandler {

    private final ExecutorService threadPoolExecutor = Executors.newFixedThreadPool(10);

    @Override
    public void onContainersCompleted(List<ContainerStatus> statuses) {

    }

    /**
     * 回调函数,当ResourceManager结束container创建后将会执行该函数
     * @param allocatedContainers
     */
    @Override
    public void onContainersAllocated(List<Container> allocatedContainers) {
        log.info("Got response from RM for container ask, allocatedCnt="
                + allocatedContainers.size());
        List<Container> numAllocatedContainers = new ArrayList<>();
        //        numAllocatedContainers.addAndGet(allocatedContainers.size());
        for (Container allocatedContainer : allocatedContainers) {
            LaunchContainerRunnable runnableLaunchContainer =
                    new LaunchContainerRunnable(allocatedContainer);
            Thread launchThread = new Thread(runnableLaunchContainer);

            // launch and start the container on a separate thread to keep
            // the main thread unblocked
            // as all containers may not be allocated at one go.
            threadPoolExecutor.execute(launchThread);
            launchThread.start();
        }
    }

    @Override
    public void onContainersUpdated(List<UpdatedContainer> containers) {

    }

    @Override
    public void onShutdownRequest() {

    }

    @Override
    public void onNodesUpdated(List<NodeReport> updatedNodes) {

    }

    /**
     * 传递心跳时将会调用该函数显示当前处理进度
     */
    @SneakyThrows
    @Override
    public float getProgress() {
        int numTotalContainers = 1;
        // set progress to deliver to RM on next heartbeat
        AtomicInitializer<Object> numCompletedContainers = null;
        float progress = (float) numCompletedContainers.get()
                / numTotalContainers;
        return progress;
    }

    @Override
    public void onError(Throwable e) {

    }

}
