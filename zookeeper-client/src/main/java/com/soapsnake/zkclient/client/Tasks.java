package com.soapsnake.zkclient.client;

import com.soapsnake.zkclient.constant.ZkConstant;
import org.apache.zookeeper.*;

import java.io.IOException;

/**
 * Created by soapsnake on 2017/5/20.
 */
public class Tasks implements Watcher {
    private ZooKeeper zk;
    private String hostport;

    Tasks(String hostport) {
        this.hostport = hostport;
    }

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        Tasks tasks = new Tasks(ZkConstant.ZK_HOST_PORT);
        tasks.startZK();

        String command = "";
        String name = tasks.createTasks(command);

        Thread.sleep(30000);

        System.out.println("task been created: " + name);
    }

    public void process(WatchedEvent event) {
        System.out.println(event.toString());
    }

    private void startZK() throws IOException {
        zk = new ZooKeeper(hostport, 1500, this);
    }

    private void stopZK() throws InterruptedException {
        zk.close();
    }

    String createTasks(String command) throws KeeperException, InterruptedException {
        while (true) {
            String name = zk.create("/tasks/task-",
                    command.getBytes(),
                    ZooDefs.Ids.OPEN_ACL_UNSAFE,
                    CreateMode.EPHEMERAL_SEQUENTIAL);
            return name;
        }
    }
}
