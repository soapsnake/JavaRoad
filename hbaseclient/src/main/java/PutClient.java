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
import org.apache.hadoop.hbase.io.compress.Compression;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by liudun on 2017/9/16.
 */
public class PutClient {

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
        System.out.println("this tables exit in hbase: " +Arrays.toString(admin.listTableNames()));


        //创建表
        HTableDescriptor newtable = new HTableDescriptor(Bytes.toBytes("testtable"));
        HColumnDescriptor cf = new HColumnDescriptor(Bytes.toBytes("colfam1")).setCompressionType(Compression.Algorithm.NONE);
        newtable.addFamily(cf);
        System.out.print("Creating table. ");
        try {
            admin.createTable(newtable);
            System.out.println(" Done.");
            System.out.println("Tables created");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("create table failed!!!");
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

        //对testtable这张表进行delete操作  删
        Delete delete = new Delete(Bytes.toBytes("row1"));
        table.delete(delete);
        table.flushCommits();    //缓冲区激活后需要调这个函数发生实际远程调用


        //对testtable表进行get操作     查
        Get get = new Get(Bytes.toBytes("row1"));
        Result result = table.get(get);
        System.out.println("get row1 result: " + result);
        System.out.println("buffer size: " + table.getWriteBufferSize());   //默认缓冲区大小

    }
}
