package com.soapsnake.algorithms.paypay;

import java.util.Arrays;
import java.util.concurrent.locks.ReentrantLock;

public class ImmutableQueue<T> implements Queue<T> {

    private Object[] array;

    private final ReentrantLock lock = new ReentrantLock();

    public ImmutableQueue() {
        this.array = new Object[0];
    }

    @Override
    /**
     * que不可变,这里返回的应该是一个新的queue
     */
    public Queue<T> enQueue(T t) {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            Object[] elements = this.array;
            int len = elements.length;
            Object[] newElements = Arrays.copyOf(elements, len + 1);
            newElements[len] = t;
            this.array = newElements;
            return this;
        } finally {
            lock.unlock();
        }
    }

    @Override
    /**
     * Removes the element at the beginning of the immutable queue, and returns the new queue.
     * que不可变,这里返回的应该是一个新的queue
     */
    public Queue<T> deQueue() {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            Object[] elements = this.array;
            int len = elements.length;
            Object[] newElements = new Object[len - 1];
            System.arraycopy(elements, 0, newElements, 0, len - 1);
            this.array = newElements;
            return this;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public T head() {
        int len = this.array.length;
        return (T) this.array[len - 1];
    }

    @Override
    public boolean isEmpty() {
        return this.array == null || this.array.length == 0;
    }
}
