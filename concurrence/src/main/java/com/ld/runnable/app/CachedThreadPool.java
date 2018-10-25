package com.ld.runnable.app;

import com.ld.runnable.thread.CallableThread;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CachedThreadPool {

    public static void main(String[] args) {

//		ExecutorService executor = Executors.newCachedThreadPool();      //executor代表池对象本身
//		
//	
//			executor.execute(new RunnableThread());       //execute表示从池中随意拿一条线程来执行任务
//			//executor.shutdown();                          //关闭线程池,后面的execute不能再创建新线程
//			executor.execute(new RunnableThread());    //线程池中的第二条线程
//			executor.shutdown();                        
//			while(true){
//				try {
//					Thread.currentThread().sleep(2000);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			System.out.println(Thread.currentThread().getName()+" is running");}
//			//executor.shutdown();

//		ExecutorService executor = Executors.newFixedThreadPool(5);        //定额线程池,最多只能创建指定数量线程数
//		
//		while(true){
//		executor.execute(new RunnableThread()); 
//		}
//		

//		ExecutorService executor = Executors.newSingleThreadExecutor();
//		for(int i=0;i<10;i++){
//			/**
//			 * newSingleThreadExecutor can only hold one thread
//			 * when execute,it will let this thread to deal with the task,then dead
//			 * but the for loop force the thread pool to create another thread to run the same task
//			 * until the for loop finally ended.
//			 */
//			executor.execute(new RunnableThread());    
//			System.out.println("this is the: "+i+"th");
//			executor.shutdown();
//		}


        ExecutorService executor = Executors.newCachedThreadPool();

        ArrayList<Future<String>> results = new ArrayList<Future<String>>();

        for (int i = 0; i < 10; i++) results.add(executor.submit(new CallableThread(i)));  //这里不再是execute方法,而是submit方法

        for (Future<String> fs : results) {  //把线程执行的结果放入一个集合当中,然后对这个集合进行遍历
            try {
                System.out.println(fs.get());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                executor.shutdown();
            }
        }


    }
}
