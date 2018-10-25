package com.ld.zk.client;

import com.ld.zk.constant.ZkConstant;
import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.Random;

/**
 * Created by liudun on 2017/5/20.
 */
public class Worker implements Watcher {

    AsyncCallback.StatCallback existsCallBack = new AsyncCallback.StatCallback() {
        public void processResult(int rc, String path, Object ctx, Stat stat) {
            System.out.println("existsCallBack被调用");
            System.out.println(">>>>>>>stat>>>>>>>:" + stat.toString());
        }
    };
    private ZooKeeper zk;
    private String hostport;
    private String serverId;
    //异步回调对象,zookeeper执行完成后会回调这个对象中的processResult告知执行结果
    AsyncCallback.StringCallback createWorkerCallback = new AsyncCallback.StringCallback() {
        public void processResult(int rc, String path, Object ctx, String name) {
            switch (KeeperException.Code.get(rc)) {
                case CONNECTIONLOSS:
                    registWorker();
                    break;
                case OK:
                    System.out.println("create worker success >>>>" + serverId);
                    break;
                case NODEEXISTS:
                    System.out.println("这个worker已经存在" + serverId);
                    break;
                case NONODE:
                    System.out.println("没有这个节点,这是为什么?????" + serverId);
                    break;
                default:
                    System.out.println("生成worker znode 期间产生错误" + KeeperException.create(KeeperException.Code.get(rc), path));
                    break;
            }
            System.out.println("path: " + path);
            System.out.println("name: " + name);
        }
    };

    public Worker(String hostport) {
        super();
        this.hostport = hostport;
        Random random = new Random();
        serverId = String.valueOf(random.nextLong());
    }

    public static void main(String[] args) throws InterruptedException, KeeperException {
        Worker worker = new Worker(ZkConstant.ZK_HOST_PORT);

        worker.startZK();  //建立客户端-服务端连接

        worker.registMaster();

        Thread.sleep(3000);
        //建立worker
        worker.registWorker();

        Thread.sleep(6000);

        System.out.println("worker 客户端即将断开!!!!!!!!");

        worker.stopZK();
    }

    void startZK() {
        try {
            zk = new ZooKeeper(hostport, 15000, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void stopZK() throws InterruptedException, KeeperException {
//        zk.exists("/workers",this,existsCallBack,null);
        zk.delete("/workers/worker-test", -1);
        Thread.sleep(2000);
        zk.delete("/workers", -1);
        zk.close();
    }

    public void process(WatchedEvent event) {
        System.out.println("===============================process函数触发");
        System.out.println("path: " + event.getPath());
        System.out.println("state: " + event.getState());
        System.out.println("type: " + event.getType());
    }

    void setMonitor() {
        try {
            System.out.println(">>>>>>>>监控/workers节点!!!!!!!");
            zk.exists("/workers", true);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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

    void registMaster() {
        try {
            zk.create("/workers",
                    "Idle".getBytes(),
                    ZooDefs.Ids.OPEN_ACL_UNSAFE,
                    CreateMode.PERSISTENT);

            //这句话将使/workers节点下的所有子节点都受到监控!!!!!!!!
            zk.getChildren("/workers", true);

//            System.out.println("那么删除这个节点了,会不会触发事件了???"); //事实证明:会!!!
//            zk.delete("/workers",-1);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void registWorker() {
        //创建节点
        try {
            zk.create("/workers/worker-test",
                    "Idle".getBytes(),
                    ZooDefs.Ids.OPEN_ACL_UNSAFE,
                    CreateMode.PERSISTENT);

            //手动监控子节点
            zk.exists("/workers/worker-test", true);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//                createWorkerCallback,
    }
}
