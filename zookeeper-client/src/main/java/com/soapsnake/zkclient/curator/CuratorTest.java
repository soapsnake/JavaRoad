package com.soapsnake.zkclient.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.RetrySleeper;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.zookeeper.CreateMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.soapsnake.zkclient.constant.ZkConstant;

/**
 * Created by soapsnake on 2017/5/26.
 */
public class CuratorTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(CuratorTest.class);
    private RetryPolicy retryPolicy;
    private CuratorFramework zkc;

    public CuratorTest() {
        retryPolicy = new RetryPolicy() {
            @Override
            public boolean allowRetry(int i, long l, RetrySleeper retrySleeper) {
                return false;
            }
        };

        new Thread(() -> System.out.println("hello"));

        zkc = CuratorFrameworkFactory.newClient(ZkConstant.ZK_HOST_PORT, retryPolicy);
        zkc.start();
    }

    public static void main(String[] args) throws Exception {
        CuratorTest test = new CuratorTest();
        //串行模式创建节点
        test.zkc.create().withMode(CreateMode.EPHEMERAL).forPath("/master", new byte[0]);

        //异步方式创建节点
        test.zkc.create().withMode(CreateMode.EPHEMERAL).inBackground().forPath("/workers", new byte[0]);

        try {
            for (int i = 0; i < 10; i++) {
                System.out.println(i + "...");
                Thread.sleep(1000);
            }
            LOGGER.warn("即将断开连接");
        } finally {
            test.zkc.close();
        }


    }
}
