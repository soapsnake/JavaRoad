package com.soapsnake.mydubbo.zk;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

import com.soapsnake.mydubbo.constants.Constant;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-10-09
 */
public enum ZkManager {

    INSTANCE;
    private CuratorFramework client;

    private ZkManager() {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        client = CuratorFrameworkFactory.builder()
                        .connectString(Constant.ZK_SERVER_HOSTS)
                        .sessionTimeoutMs(5000)
                        .connectionTimeoutMs(5000)
                        .retryPolicy(retryPolicy)
                        .build();
    }

    //枚举单例?
    public CuratorFramework getClient() {
        return INSTANCE.client;
    }

}
