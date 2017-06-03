package com.ld.multithread.threadpool;

import java.io.File;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadPoolTest {
	public static void main(String[] args) {
		try (Scanner in = new Scanner(System.in)){
			System.out.println("输入根目录(e.g.): ");
			String directory = in.nextLine();
			System.out.println("input keyword: ");
			String keyword = in.nextLine();
			
			ExecutorService exec = Executors.newCachedThreadPool();
			MachCounter counter = new MachCounter(new File(directory),keyword,exec);
			
			//callable接口对象可以直接被线程池提交执行,就像callable接口对象是一个Runnable一样
			Future<Integer> future = exec.submit(counter);
			
			try {
				System.out.println(future.get()+" maching files.");
			} catch (ExecutionException e) {
				// TODO: handle exception
			}
			
			exec.shutdown();
			int largestPoolSize = ((ThreadPoolExecutor) exec).getLargestPoolSize();
			System.out.println("largestPoolSize= "+largestPoolSize);
			
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
	}
}
