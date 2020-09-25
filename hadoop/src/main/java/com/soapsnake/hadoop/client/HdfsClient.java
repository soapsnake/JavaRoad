package com.soapsnake.hadoop.client;

import java.io.IOException;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-09-20
 */
public class HdfsClient {


    public static void main(String[] args) throws IOException {
        Configuration configuration = new Configuration();
        FileSystem fileSystem = FileSystem.get(configuration);
        URI uri = fileSystem.getUri();
        System.out.println("uri => " + uri);

        Path dir = new Path("user/dir");
        Path file = new Path(dir + "test.txt");

        fileSystem.mkdirs(dir);
        fileSystem.copyFromLocalFile(new Path("/Users/liudun/test.txt"), file);
        FileStatus fileStatus = fileSystem.getFileStatus(file);
        System.out.println(fileStatus.getPath());
        System.out.println(fileStatus.getPath().toUri().getPath());
        System.out.println(fileStatus.getBlockSize());
        System.out.println(fileStatus.getGroup());
        System.out.println(fileStatus.getOwner());
        fileSystem.delete(file, true);
        System.out.println(fileSystem.isFile(file));
    }


}
