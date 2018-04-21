package com.ld.multithread.blockqueue;

import java.io.File;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueTest {
	
	private static final int FILE_QUEUE_SIZE=10;
	private static final int SEARCH_THREADS = 100;
	private static final File DUMMY = new File("");
	private static BlockingQueue<File> queque = new ArrayBlockingQueue<>(FILE_QUEUE_SIZE); //创建队列,大小为10

	public static void main(String[] args) {
		// Auto-generated method stub
		try (Scanner in = new Scanner(System.in)){
			System.out.println("输入根目录(e.g. /opt/jdk1.8.0/src): ");
			String directroy = in.nextLine();
			System.out.println("输入关键字(e.g. voaltile): ");
			String keyword = in.nextLine();
			
			Runnable enumerator = () ->{ //枚举线程,作用是把路径下的所有文件放进阻塞队列
				try {
					enumerate(new File(directroy));
					queque.put(DUMMY);   //最后把DUMMY文件放进队列用于标识队列的末尾
				} catch (Exception e) {
					//: handle exception
				}
			};
			new Thread(enumerator).start();   //启动枚举线程,枚举线程只有一条
			
			
			for (int i = 1; i < SEARCH_THREADS; i++) {
				Runnable searcher = ()->{       //搜索线程,搜索线程有100条
					try {
						boolean done = false;
						while(!done){
							File file = queque.take();  //把文件从队列当中拿出来
							if (file == DUMMY) { //如果队列中的这个文件和DUMMY文件相同
								queque.put(file);  //把DUMMY塞回队列中
								System.out.println("处理完毕,即将退出!!!!!!!!!!!!!!!!!!!!!!!!!!");
								done = true;  //发信号:完成了
							}
							else search(file,keyword);  //如果没有完成,就继续搜
						}
					}catch(InterruptedException e){
						e.printStackTrace();
					}
				};
				new Thread(searcher).start();  //启动这100条搜索线程
			}
		} catch (Exception e) {
			//: handle exception
			e.printStackTrace();
		}
	}
		//进入文件夹将其中的所有文件都拿出来放进一个队列当中
		public static void enumerate(File directory) throws InterruptedException{
			File[] files = directory.listFiles();
			for (File file : files) {
				if(file.isDirectory()) enumerate(file);  //假如是文件夹就迭代自己进入该文件夹继续工作
				else queque.put(file);
			}
		}
		
		public static void search(File file ,String keyword){
			try (Scanner in = new Scanner(file,"utf-8")){  //进入文件当中
				int lineNumber = 0;
				while(in.hasNext()){
					lineNumber++;
					String line = in.nextLine();
					if (line.contains(keyword)) {  //如果某行含有输入的关键字,就打印改行,否则继续下一行
						System.out.printf("%s:%d:%s%n",file.getPath(),lineNumber,line);
					}
				}
			} catch (Exception e) {
				//: handle exception
				e.printStackTrace();
			}
		}
}
