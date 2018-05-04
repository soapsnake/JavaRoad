package com.ld;

import com.google.protobuf.ServiceException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.ClusterStatus;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.ServerName;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by liudun on 2017/9/21.
 *
 * 与表结构相关的操作
 */
public class HTableAPI {
    private static final Logger LOGGER = LoggerFactory.getLogger(HTableAPI.class);

    HTablePoolTest poolTest;

    HBaseAdmin admin;

    private HTableAPI(){
        poolTest = new HTablePoolTest(10);

        Configuration conf = HBaseConfiguration.create(); // co PutExample-1-CreateConf Create the required configuration.
        conf.setInt("timeout", 12000);
        conf.set("hbase.master", "10.4.226.212:60000");
        conf.set("hbase.zookeeper.property.clientPort", "2181");
        conf.set("zookeeper.znode.parent", "/hbase");

        try {
            admin = new HBaseAdmin(conf);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /////////////////////////////////////////////////////////////表操作api///////////////////////////////////////////////////////////////////////////////////
    /**
     * 获取单表结构描述信息
     * @param tableName
     * @return
     */
    public HTableDescriptor getDescriptor(String tableName) {
        Table table = poolTest.getHTable(tableName);
        try {
            HTableDescriptor descriptor = table.getTableDescriptor();
            LOGGER.info(descriptor.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取所有表的表结构描述信息
     * @return
     */
    public HTableDescriptor[] getAllTableDescriptors() {
        try {
            HTableDescriptor[] htds = admin.listTables();
            for (HTableDescriptor descriptor : htds) {
                LOGGER.info(descriptor.toString());
            }
            return htds;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 建表
     * @param tableName
     */
    public void createTable(String tableName, String columnFamName) {
        try {
            boolean exists = admin.tableExists(tableName);

            if (exists) {
                LOGGER.warn("table :{} already exist!!!", tableName);
                return;
            }

            HTableDescriptor descriptor = new HTableDescriptor(Bytes.toBytes(tableName));

            HColumnDescriptor columnDescriptor = new HColumnDescriptor(Bytes.toBytes(columnFamName));

            descriptor.addFamily(columnDescriptor);

            admin.createTable(descriptor);
            boolean avail = admin.isTableAvailable(Bytes.toBytes(tableName));         //表即使禁用也返回true
            boolean trueAvailable = admin.isTableEnabled(Bytes.toBytes(tableName));   //表禁用会返回false
            LOGGER.info("table:{}, columnFam:{}, create successful!!!, is table available:{}", tableName, columnFamName, trueAvailable);
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("table:{}, columnFam:{}, create fail!!!", tableName, columnFamName, e);
        }
    }

    /**
     * 删表操作
     * @param tableName
     */
    public void deleteTable(String tableName) {
        try {
            admin.disableTable(Bytes.toBytes(tableName));  //先禁用表,不先禁就删除会抛出异常!!
            admin.deleteTable(Bytes.toBytes(tableName));

            long cahs = 1000000000000021300L;
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("delete :{} failed",tableName, e);
        }
    }

    /**
     * 异步修改表结构
     * @param tableName
     * @param columnFamName
     */
    public void modifyTable(String tableName, String columnFamName,HTableDescriptor newRule) {
        HTableDescriptor descriptor = new HTableDescriptor(Bytes.toBytes(tableName));

        HColumnDescriptor columnDescriptor = new HColumnDescriptor(Bytes.toBytes(columnFamName));

        descriptor.addFamily(columnDescriptor);

        Person person = new Person();
        person.setName("hud");
        descriptor.setValue("hud",person.toString());

        try {
            //修改表之前必须先禁用表
            admin.disableTable(Bytes.toBytes(tableName));

            admin.modifyTable(Bytes.toBytes(tableName), descriptor);

            //修改完后记得启用表
            admin.enableTable(Bytes.toBytes(tableName));

            this.getDescriptor(tableName);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /////////////////////////////////////////////////////////////集群操作api///////////////////////////////////////////////////////////////////////////////////

    /**
     * 检查远程HBase集群的可用性
     * @param conf
     */
    public void checkHBase(Configuration conf) {

        try {
            HBaseAdmin.checkHBaseAvailable(conf);
            LOGGER.info("table base on this conf:{} is available!!!", conf);
        } catch (ServiceException | IOException e) {
            e.printStackTrace();
            LOGGER.error("table base on this conf:{} can't work !!!", conf, e);
        }
    }

    /**
     * 获取集群状态信息
     */
    public ClusterStatus getClusterStatus() {
        ClusterStatus status = new ClusterStatus();
        try {
            status =  admin.getClusterStatus();
            LOGGER.info("status: {}", status.toString());

            for (ServerName name : status.getServers()) {
                LOGGER.info("servers: {}", name);  //返回region服务器的IP(hostname), RPC端口, 启动时间戳(long型)
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return status;
    }





    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public static void main(String[] args) {
        HTableAPI api = new HTableAPI();


        api.getDescriptor("testtable2");
        api.createTable("testtable3", "colfam1");

//        api.deleteTable("testtable3");

        api.modifyTable("testtable3", "colfam1", new HTableDescriptor("testtable3"));



        api.getClusterStatus();


        api.getAllTableDescriptors();
    }

}
