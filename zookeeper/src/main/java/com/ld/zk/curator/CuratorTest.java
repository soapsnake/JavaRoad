package com.ld.zk.curator;

import com.ld.zk.constant.ZkConstant;
import org.apache.curator.RetryPolicy;
import org.apache.curator.RetrySleeper;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.zookeeper.CreateMode;

/**
 * Created by liudun on 2017/5/26.
 */
public class CuratorTest {
    private RetryPolicy retryPolicy;
    private CuratorFramework zkc;

    public CuratorTest(){
        retryPolicy = new RetryPolicy() {
            @Override
            public boolean allowRetry(int i, long l, RetrySleeper retrySleeper) {
                return false;
            }
        };

        new Thread(() -> System.out.println("hello"));

        zkc = CuratorFrameworkFactory.newClient(ZkConstant.ZK_HOST_PORT,retryPolicy);
    }

    public static void main(String[] args) throws Exception {
        CuratorTest test = new CuratorTest();
        //串行模式创建节点
        test.zkc.create().withMode(CreateMode.EPHEMERAL).forPath("/master",new byte[0]);

        //异步方式创建节点
        test.zkc.create().withMode(CreateMode.EPHEMERAL).inBackground().forPath("/workers",new byte[0]);
    }
}
