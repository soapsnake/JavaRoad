package com.soapsnake.hbaseclient;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.io.compress.Compression;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by liudun on 2017/9/16.
 */
public class HbaseClient {

    public static void main(String[] args) throws IOException {
        Configuration conf = HBaseConfiguration.create(); // co PutExample-1-CreateConf Create the required configuration.
        conf.setInt("timeout", 12000);
        conf.set("hbase.master", "10.4.226.212:60000");
        conf.set("hbase.zookeeper.property.clientPort", "2181");
        conf.set("zookeeper.znode.parent", "/hbase");

        //连接hbase
        System.out.println("Trying to connect to HBase");
        Connection connection = ConnectionFactory.createConnection(conf);

        Admin admin = connection.getAdmin();
        System.out.println("Connected to HBase");

        //获取hbase中所有的表
        System.out.println("this tables in hbase: " + Arrays.toString(admin.listTableNames()));


        //创建表
        HTableDescriptor newtable = new HTableDescriptor(Bytes.toBytes("testtable"));
        HColumnDescriptor cf = new HColumnDescriptor(Bytes.toBytes("colfam1")).setCompressionType(Compression.Algorithm.NONE);
        newtable.addFamily(cf);

        HTableDescriptor newtable2 = new HTableDescriptor(Bytes.toBytes("testtable2"));
        HColumnDescriptor cf2 = new HColumnDescriptor(Bytes.toBytes("colfam2-1")).setCompressionType(Compression.Algorithm.NONE);
        newtable2.addFamily(cf2);

        try {
            System.out.print("Creating table: ");
            admin.createTable(newtable2);
            System.out.println("create table success!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("create table failed!!");
        }

        //删除表

        //修改表

        //查看表的信息


        //对testtable这张表进行put操作: 增,改
        HTable table = new HTable(conf, "testtable");
        //行键
        Put put = new Put(Bytes.toBytes("row1"));

        //列族名                     //列名                       //值
        put.add(Bytes.toBytes("colfam1"), Bytes.toBytes("qual1"), Bytes.toBytes("val1"));
        put.add(Bytes.toBytes("colfam1"), Bytes.toBytes("qual2"), Bytes.toBytes("val2"));
        table.setAutoFlush(false);     //这里设置false激活缓冲区
        List<Put> puts = new ArrayList<>();   //批量put
        puts.add(put);
        table.put(puts);        //实际是一个rpc调用

        Put put1 = new Put(Bytes.toBytes("row1"));
        put1.add(Bytes.toBytes("colfam1"), Bytes.toBytes("qual1"), Bytes.toBytes("val1"));
        //CAS操作 compare and set 原子操作
        try {
            boolean putRes = table.checkAndPut(Bytes.toBytes("colfam1"), Bytes.toBytes("qual1"), Bytes.toBytes("val1"), null, put1);
            System.out.println("CAS put res: " + putRes);

            //也可以调用这个方法:
            Object[] processRes = new Object[puts.size()];   //接受结果的数组

            //void 版batch,这个方法抛出异常后仍然会填充部分结果到processRes当中
            table.batch(puts, processRes);

            //返回值版batch,这个方法假如抛了异常将会没有任何返回值,已弃用
            Object[] processRes1 = table.batch(puts);

            System.out.println(Arrays.toString(processRes));

        } catch (Exception e) {
            e.printStackTrace();
        }


        //对testtable这张表进行delete操作  删
        Delete delete = new Delete(Bytes.toBytes("row1"));
        //指定delete操作要作用的列族/列定义符,注意,hbase任何的增删改查操作都要带列,否则就是一个空操作
        //delete操作必须指定要删除的keyvalue的时间戳或者版本号,一个单元格最多保留3个版本,所以,要想把一个单元格删空,需要执行三次指定时间戳的删除操作才行
        //假如删除的列族+列名对应的keyvalue单元格已经为空,再次删除也不会报错,说明列族+列名仍然存在
        delete.addColumn(Bytes.toBytes("colfam1"), Bytes.toBytes("qual1"), 1505751025065L);

        //但是,如果尝试删除不存在的列族(列名不会),将会抛出异常
//        delete.addColumn(Bytes.toBytes("colfam3"), Bytes.toBytes("qual4"), 1505751025065L);

        try {
            table.delete(delete);

            boolean deleteRes = table.checkAndDelete(Bytes.toBytes("colfam1"), Bytes.toBytes("qual1"), Bytes.toBytes("val1"), null, delete);
            System.out.println("CAS delete, res:" + deleteRes);
        } catch (Exception e) {
            e.printStackTrace();
        }

        table.flushCommits();    //缓冲区激活后需要调这个函数发生实际远程调用


        //对testtable表进行get操作     查
        Get get = new Get(Bytes.toBytes("row1"));
        Result result = table.get(get);
        System.out.println("get row1 result: " + result);


        //精确版get
        get.addColumn(Bytes.toBytes("colfam1"), Bytes.toBytes("qual1"));
        Result result1 = table.get(get);
        byte[] val = result.getValue(Bytes.toBytes("colfam1"), Bytes.toBytes("qual1"));
        System.out.println("value: " + Bytes.toString(val));


        //scan操作
        Person person = new Person();
        person.setName("tail");
        person.setAge(18);
        person.setGender(2);
        HTable table2 = new HTable(conf, "testtable2");
        Put put2 = new Put(Bytes.toBytes("row2-1"));
        put2.add(Bytes.toBytes("colfam2-1"), Bytes.toBytes("qual2-1"), Bytes.toBytes(person.toString()));
        table2.put(put2);

        Get get2 = new Get(Bytes.toBytes("row2-1"));
        get2.addFamily(Bytes.toBytes("colfam2-1"));
        get2.setMaxVersions(3);
        //获取一个单元格(cell)中所有版本的数据,需要服务端首先开启多版本才行!!
        Result multiVersionsRes = table2.get(get2);
        System.out.println("multiVersionsRes:" + multiVersionsRes.getMap().entrySet());

//        boolean putRes2 = table2.checkAndPut(Bytes.toBytes("colfam2-1"), Bytes.toBytes("qual2-1"), Bytes.toBytes("val2-1"), null, put2);
//        System.out.println("put2 is success?: " + putRes2);

        Scan scan = new Scan();
//        scan.addFamily(Bytes.toBytes("colfam1"));
        //对scan使用过滤器
        scan.setFilter(CustomFilter.getFamilyFilter());

        ResultScanner scanner = table2.getScanner(scan);
        System.out.println("scanner: " + scanner.toString());
        for (Result scanRes : scanner) {
            System.out.println("scanner process: " + scanRes);
        }

        scanner.close();


        System.out.println("buffer size: " + table.getWriteBufferSize());   //默认缓冲区大小


        table.close();
    }
}
