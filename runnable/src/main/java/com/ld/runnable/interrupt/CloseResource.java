package com.ld.runnable.interrupt;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CloseResource {
	
	public static void main(String[] args) throws Exception{
		
		ExecutorService executorService = Executors.newCachedThreadPool();
		ServerSocket serverSocket = new ServerSocket(8080);
		InputStream socketInput = new Socket("localhost",8080).getInputStream();
		
		BufferedInputStream iStream = (BufferedInputStream) System.in;
		executorService.execute(new IOBlocked(socketInput));
		executorService.execute(new IOBlocked(iStream));
		
		TimeUnit.MILLISECONDS.sleep(100);
		System.out.println("关闭所有的线程!!!!!!!!!!!");
		executorService.shutdown();
		TimeUnit.SECONDS.sleep(2);
		System.out.println("关闭资源socket: "+socketInput.getClass().getName());
		socketInput.close();
		TimeUnit.SECONDS.sleep(2);
		System.out.println("关闭system系统资源: "+System.in.getClass().getName());
		iStream.close();
	
	}	
}
