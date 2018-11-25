package com.soapsnake.zkclient.client;

import com.soapsnake.zkclient.constant.ZkConstant;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.Date;

/**
 * Created by liudun on 2017/5/20.
 */
public class Monitor implements Watcher {
    private ZooKeeper zk;
    private String hostport;

    Monitor(String hostport) {
        this.hostport = hostport;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Monitor monitor = new Monitor(ZkConstant.ZK_HOST_PORT);

        monitor.startZK();

        monitor.listState();

        Thread.sleep(5000);

        monitor.stopZK();
    }

    public void process(WatchedEvent event) {
        System.out.println("===============================process函数触发");
        System.out.println("path: " + event.getPath());
        System.out.println("state: " + event.getState());
        System.out.println("type: " + event.getType());
    }

    private void startZK() throws IOException {
        zk = new ZooKeeper(hostport, 1500, this);
    }

    private void stopZK() throws InterruptedException {
        zk.close();
    }

    void listState() {
        Stat stat = new Stat();
        try {
            byte[] masterData = zk.getData("/master", false, stat);
            Date startDate = new Date(stat.getCtime());
            System.out.println("master: " + new String(masterData) + "since" + startDate);
        } catch (KeeperException e) {
            e.printStackTrace();
            System.out.println("根本就没有/master节点!!!!!!!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("workers:");
        try {
            byte[] workersData = zk.getData("/workers", false, stat);
            Date startDate = new Date(stat.getCtime());
            System.out.println("workers: " + new String(workersData) + "since" + startDate);
        } catch (KeeperException e) {
            e.printStackTrace();
            System.out.println("根本就没有/workers节点!!!!!!!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            for (String worker : zk.getChildren("/workers", false)) {
                byte[] data = zk.getData("/workers/" + worker, false, null);
                String state = new String(data);
                System.out.println("\t" + worker + ":" + state);
            }
        } catch (KeeperException e) {
            e.printStackTrace();
            System.out.println("/workers节点下无子节点!!!!!!!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Tasks:");
        try {
            byte[] assignData = zk.getData("/assign", false, stat);
            Date startDate = new Date(stat.getCtime());
            System.out.println("assign: " + new String(assignData) + "since" + startDate);
        } catch (KeeperException e) {
            e.printStackTrace();
            System.out.println("根本就没有/assign节点!!!!!!!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            for (String task : zk.getChildren("/assign", false)) {
                System.out.println("\t" + task);
            }
        } catch (KeeperException e) {
            e.printStackTrace();
            System.out.println("/assign节点下无子节点!!!!!!!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}