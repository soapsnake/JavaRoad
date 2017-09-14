package com.ld.thinkinjava.nio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by liudun on 2017/9/12.
 */
public class FileLock {

    public static void main(String[] args) throws IOException, InterruptedException {
        FileOutputStream stream = new FileOutputStream("file.txt");

        java.nio.channels.FileLock fileLock = stream.getChannel().tryLock();

        if (fileLock != null) {
            System.out.println("lock file!!!");

            TimeUnit.MILLISECONDS.sleep(1000);


            fileLock.release();

            System.out.println("release lock!!");
        }

        stream.close();
    }
}
