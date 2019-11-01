package com.soapsnake.zkclient.client;

import com.soapsnake.zkclient.constant.ZkConstant;
import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.Random;

/**
 * Created by soapsnake on 2017/5/20.
 */
public class MetaZnode implements Watcher {
    private ZooKeeper zk;
    private String hostport;
    private String serverId;
    //异步回调对象,zookeeper执行完成后会回调这个对象中的processResult告知执行结果
    AsyncCallback.StringCallback createMetaCallback = new AsyncCallback.StringCallback() {
        public void processResult(int rc, String path, Object ctx, String name) {
            switch (KeeperException.Code.get(rc)) {
                case CONNECTIONLOSS:
                    createMeta(path, (byte[]) ctx);
                    break;
                case OK:
                    System.out.println("create meta successful>>>>" + serverId);
                    break;
                case NODEEXISTS:
                    System.out.println("meta 已经存在" + serverId);
                    break;
                default:
                    System.out.println("生成meta znode期间产生错误" + KeeperException.create(KeeperException.Code.get(rc), path));
                    break;
            }
            System.out.println("path: " + path);
            System.out.println("name: " + name);

        }
    };

    public MetaZnode(String hostport) {
        this.hostport = hostport;
        Random random = new Random();
        serverId = String.valueOf(random.nextLong());
    }

    public static void main(String[] args) throws InterruptedException {
        MetaZnode meta = new MetaZnode(ZkConstant.ZK_HOST_PORT);
        meta.startZK();  //建立客户端-服务端连接

        //创建4个父节点,这4个节点均为永久节点
        meta.createMeta("/workers", new byte[0]);
        meta.createMeta("/assign", new byte[0]);
        meta.createMeta("/tasks", new byte[0]);
        meta.createMeta("/status", new byte[0]);

        Thread.sleep(10000);

        System.out.println("meta 客户端即将断开!!!!!!!!");

        meta.stopZK();

    }

    void startZK() {
        try {
            zk = new ZooKeeper(hostport, 15000, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void stopZK() throws InterruptedException {
        zk.close();
    }

//    void checkMaterAsyn(){
//        zk.getData("/master",false,masterCheckCallBack,null);
//    }
//
//    AsyncCallback.DataCallback masterCheckCallBack = new AsyncCallback.DataCallback() {
//        public void processResult(int rc, String path, Object ctx, byte[] data, Stat stat) {
//            switch (KeeperException.Code.get(rc)){
//                case CONNECTIONLOSS:
//                    checkMaterAsyn();
//                    return;
//                case NONODE:
////                    runForMasterAsyn();
//                    return;
//            }
//        }
//    };

    public void process(WatchedEvent event) {
        System.out.println(event.toString() + ">>>>" + hostport);
    }

    void createMeta(String path, byte[] data) {
        zk.create(path,
                data,
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.PERSISTENT,
                createMetaCallback,
                data);
    }

}
