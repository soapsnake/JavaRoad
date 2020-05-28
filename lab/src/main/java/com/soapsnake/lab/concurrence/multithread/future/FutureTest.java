package com.soapsnake.lab.concurrence.multithread.future;

import java.io.File;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTest {
    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            System.out.println("输入根目录(e.g.): ");
            String directory = in.nextLine();
            System.out.println("input keyword: ");
            String keyword = in.nextLine();

            MachCounter counter = new MachCounter(new File(directory), keyword);
            FutureTask<Integer> task = new FutureTask<>(counter);  //counter是一个callable类,被包装成了futuretask类型
            Thread thread = new Thread(task);
            thread.start();
            try {
                System.out.println(task.get() + " maching files.");
            } catch (ExecutionException e) {
                //: handle exception
            }
        } catch (InterruptedException e) {
            //: handle exception
        }
    }
}
