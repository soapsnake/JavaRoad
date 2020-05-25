package com.soapsnake.hbaseclient;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.HTablePool;
import org.apache.hadoop.hbase.client.Table;

import java.io.IOException;

/**
 * Created by soapsnake on 2017/9/20.
 * hbase表池,多线程可从该表池中获取HTable
 */
public class HTablePoolTest {

    private HTablePool tablePool;

    public HTablePoolTest(int count) {
        if (count == 0) {
            count = 1;
        }
        Configuration conf = HBaseConfiguration.create(); // co PutExample-1-CreateConf Create the required configuration.
        conf.setInt("timeout", 12000);
        conf.set("hbase.master", "10.4.226.212:60000");
        conf.set("hbase.zookeeper.property.clientPort", "2181");
        conf.set("zookeeper.znode.parent", "/hbase");
        this.tablePool = new HTablePool(conf, count);
    }

    //HTableInterface继承了Table
    public Table getHTable(String tableName) {
        return this.tablePool.getTable(tableName);
    }

    /**
     * @param tableName
     */
    @Deprecated
    public void closeHTable(String tableName) {
        HTableInterface table = this.tablePool.getTable(tableName);
        try {
            this.tablePool.putTable(table);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
