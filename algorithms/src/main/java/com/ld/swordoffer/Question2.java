package com.ld.swordoffer;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Question2 {
    public static class SingleTon {
        private static SingleTon singleTon = null;

        private SingleTon() {
        }

        //lock版线程安全的单例模式
        public SingleTon makeSingleTon() {
            Lock lock = new ReentrantLock();
            lock.lock();
            try {
                if (singleTon == null) {
                    return new SingleTon();
                }
                return singleTon;
            } finally {
                lock.unlock();
            }
        }

        //synchronize版线程安全单例模式
        public SingleTon getSingleTon() {
            synchronized (SingleTon.class) {
                if (singleTon == null) {
                    return new SingleTon();
                }
                return singleTon;
            }
        }
    }

}
