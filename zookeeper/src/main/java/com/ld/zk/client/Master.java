package com.ld.zk.client;

import com.ld.zk.constant.ZkConstant;
import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.Random;

import static org.apache.zookeeper.ZooDefs.Ids.OPEN_ACL_UNSAFE;

/**
 * 用来在zookeeper中创建master节点(znode)
 * Created by liudun on 2017/5/19.
 */
public class Master implements Watcher {

    private static boolean isLeader = false;
    private ZooKeeper zk;
    //异步回调对象,zookeeper执行完成后会回调这个对象中的processResult告知执行结果
    AsyncCallback.StringCallback masterCreateCallback = new AsyncCallback.StringCallback() {
        public void processResult(int rc, String path, Object ctx, String name) {
            switch (KeeperException.Code.get(rc)) {
                case CONNECTIONLOSS:
                    checkMaterAsyn();
                    return;
                case OK:
                    isLeader = true;
                    break;
                default:
                    isLeader = false;
            }
            System.out.println(">>>>>>>>>>>>>>>>>>I'm " + (isLeader ? "" : "not") + "leader");
            System.out.println("rc: " + rc);
            System.out.println("path: " + path);
            System.out.println("name: " + name);
            try {
                //为master节点上监听器,后续master节点发生变化将会触发event事件
                zk.exists("/master", true);
            } catch (KeeperException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    };
    private String hostport;
    private String serverId;
    AsyncCallback.DataCallback masterCheckCallBack = new AsyncCallback.DataCallback() {
        public void processResult(int rc, String path, Object ctx, byte[] data, Stat stat) {
            switch (KeeperException.Code.get(rc)) {
                case CONNECTIONLOSS:
                    checkMaterAsyn();
                    return;
                case NONODE:
                    runForMasterAsyn();
                    return;
            }
        }
    };

    public Master(String hostport) {
        this.hostport = hostport;
        Random random = new Random();
        serverId = String.valueOf(random.nextLong());
    }

    public static void main(String[] args) throws InterruptedException {
        Master master = new Master(ZkConstant.ZK_HOST_PORT);
        master.startZK();

//        master.runForMaster();


        master.runForMasterAsyn();  //异步请求,发出命令后立刻返回
//
//        if (isLeader){
//            System.out.println("I'm the leader!!");
//            Thread.sleep(15000);
//        }else {
//            System.out.println("someone else is leader!");
//        }

        Thread.sleep(5000);

        System.out.println("delete master node");
        master.delMaster();

        Thread.sleep(30000);
        System.out.println("master 客户端即将断开!");
        master.stopZK();
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

    //事件触发回调函数
    @Override
    public void process(WatchedEvent event) {
        System.out.println("==============事件触发===========");
        System.out.println("event.getClass(): " + event.getClass());
        System.out.println("event.getWrapper(): " + event.getWrapper());
        System.out.println("event.getPath(): " + event.getPath());
        System.out.println("event.getType(): " + event.getType());
        System.out.println("event.getState(): " + event.getState());
    }

    boolean checkMaster() {
        while (true) {
            try {
                Stat stat = new Stat();
                byte[] data = zk.getData("/master", false, stat);
                isLeader = new String(data).equals(serverId);
                return true;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (KeeperException e) {
                e.printStackTrace();
                return false;
            }
        }
    }

    void runForMaster() {
        while (true) {
            try {
                zk.create("/master", serverId.getBytes(), OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
                isLeader = true;
                break;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (KeeperException e) {
                e.printStackTrace();
                isLeader = false;
                break;
            }
            if (checkMaster()) break;
        }
    }

    private void delMaster() {
        try {
            zk.delete("/master", -1);
        } catch (InterruptedException | KeeperException e) {
            e.printStackTrace();
        }
    }

    void runForMasterAsyn() {
        zk.create("/master", serverId.getBytes(), OPEN_ACL_UNSAFE
                , CreateMode.EPHEMERAL, masterCreateCallback, null);
    }

    void checkMaterAsyn() {
        zk.getData("/master", false, masterCheckCallBack, null);
    }


}
