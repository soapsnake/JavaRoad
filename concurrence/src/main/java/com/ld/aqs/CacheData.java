package com.ld.aqs;

import com.ld.multithread.lock.Person;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CacheData {
    // 读写锁实例
    final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    Object data;
    volatile boolean cacheValid;

    void processCachedData() {
        // 获取读锁
        rwl.readLock().lock();
        if (!cacheValid) { // 如果缓存过期了，或者为 null
            // 释放掉读锁，然后获取写锁 (后面会看到，没释放掉读锁就获取写锁，会发生死锁情况)
            rwl.readLock().unlock();
            rwl.writeLock().lock();

            try {
                if (!cacheValid) { // 重新判断，因为在等待写锁的过程中，可能前面有其他写线程执行过了
                    data = new Person();
                    cacheValid = true;
                }
                // 获取读锁 (持有写锁的情况下，是允许获取读锁的，称为 “锁降级”，反之不行。)
                rwl.readLock().lock();
            } finally {
                // 释放写锁，此时还剩一个读锁
                rwl.writeLock().unlock(); // Unlock write, still hold read
            }
        }

        try {
            use(data);
        } finally {
            // 释放读锁
            rwl.readLock().unlock();
        }
    }

    private void use(Object data) {
        System.out.println(data.toString());
    }


}
