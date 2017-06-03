package com.ld.multithread.future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class AnotherTest {
	
	
	public static void main(String[] args) {
		MachCounter machCounter = new MachCounter(null,null);
		Future<Integer>  future = new Future<Integer>() {
			@Override
			public Integer get() throws InterruptedException, ExecutionException {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public boolean cancel(boolean mayInterruptIfRunning) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean isCancelled() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean isDone() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public Integer get(long timeout, TimeUnit unit)
					throws InterruptedException, ExecutionException, TimeoutException {
				// TODO Auto-generated method stub
				return null;
			}
		};
		//备注:以下两个操作均失败,说明:future对象不能被包装成为FutureTask,也不能用来构造线程
//		FutureTask<Integer> futureTask = new FutureTask<>(future);
//		Thread thread = new Thread(future);
	}
}
