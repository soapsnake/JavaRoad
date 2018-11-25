package com.soapsnake.concurrence.multithread.effective;

import java.util.HashSet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by soapsnake on 2017/5/17.
 */
public class App {

    public static void main(String[] args) {
        ObservableSet<Integer> set = new ObservableSet<>(new HashSet<>());

        set.addObserver(new SetObserver<Integer>() {
            @Override
            public void added(ObservableSet<Integer> set, Integer element) {
                System.out.println(element);
                if (element == 23) {
                    ExecutorService executor = Executors.newSingleThreadExecutor();
                    final SetObserver<Integer> observer = this;
                    try {
                        try {
                            executor.submit(new Runnable() {
                                @Override
                                public void run() {
                                    set.removeObserver(observer);
                                }
                            }).get();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } catch (ExecutionException e) {
                        throw new AssertionError(e.getCause());
                    }
                }
            }
        });

        for (int i = 0; i < 100; i++) {
            set.add(i);
        }
    }
}
