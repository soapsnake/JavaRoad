package com.soapsnake.lab.concurrence.multithread.threadpool;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class MachCounter implements Callable<Integer> {   //callable接口必须有返回对象

    private File directory;
    private String keyword;
    private ExecutorService exec;

    public MachCounter(File directory, String keyword, ExecutorService exec) {
        // Auto-generated method stub
        this.keyword = keyword;
        this.directory = directory;
        this.exec = exec;
    }

    @Override
    public Integer call() throws Exception {
        // Auto-generated method stub

        int count = 0;
        try {
            File[] files = directory.listFiles();
            List<Future<Integer>> results = new ArrayList<>();
            for (File file : files) {
                if (file.isDirectory()) {
                    MachCounter counter = new MachCounter(file, keyword, exec);
                    Future<Integer> future = exec.submit(counter);
                    results.add(future);
                } else {
                    if (search(file)) {
                        count++;
                    }
                }
                for (Future<Integer> future : results) {
                    try {
                        count += future.get();
                    } catch (ExecutionException e) {
                        //: handle exception
                    }
                }
            }
        } catch (InterruptedException e) {
            //: handle exception
        }
        return count;
    }

    public boolean search(File file) {
        try {
            try (Scanner in = new Scanner(file, "utf-8")) {
                boolean found = false;
                while (!found && in.hasNextLine()) {
                    String line = in.nextLine();
                    if (line.contains(keyword)) {
                        found = true;
                    }
                    return found;
                }
            } catch (Exception e) {
                //: handle exception
            }
        } catch (Exception e) {
            //: handle exception
        }
        return false;
    }


}
