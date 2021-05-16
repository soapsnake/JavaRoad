package com.soapsnake.mydubbo.regcenter.impl;

import javax.annotation.Nullable;

import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import com.soapsnake.mydubbo.constants.Constant;
import com.soapsnake.mydubbo.regcenter.ServiceRegistry;
import com.soapsnake.mydubbo.zk.ZkManager;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * Created on 2020-10-09
 */
public class SimpleServiceRegistry implements ServiceRegistry {

    private CuratorFramework client = ZkManager.INSTANCE.getClient();

    @Override
    public void registerService(String serviceName, String serviceAddress) {
        client.start();
        if (serviceName == null || serviceAddress == null) {
            throw new IllegalArgumentException("ServiceRegistry: serviceName and serviceAddress should not be null!");
        }

        String path = Constant.SERVICES_NAME_PREFIX + serviceName;
        try {
            if (client.checkExists().forPath(path) == null) {
                //注意了,这里创建的是永久性质的节点
                client.create().creatingParentContainersIfNeeded().withMode(CreateMode.PERSISTENT)
                        .forPath(path, serviceAddress.getBytes());
                System.out.println("服务注册成功!");
            } else {
                client.setData().forPath(path, serviceAddress.getBytes());
                System.out.println("服务已存在，更新成功!");
            }
            System.out.println(client.getChildren().forPath(path));
            System.out.println(new String(client.getData().forPath(path)));
            Stat stat = new Stat();
            System.out.println(new String(client.getData().storingStatIn(stat).forPath(path)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    @Nullable
    public String discoverService(String serviceName) {
        client.start();
        //获取服务地址
        if (serviceName == null) {
            throw new IllegalArgumentException("ServiceRegistry: serviceName should not be null!");
        }
        String path = Constant.SERVICES_NAME_PREFIX + serviceName;
        try {
            return new String(client.getData().forPath(path));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
