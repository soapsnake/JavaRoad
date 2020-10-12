package com.soapsnake.algorithms.paypay;

import java.util.Arrays;
import java.util.concurrent.locks.ReentrantLock;

public class ImmutableQueue<T> implements Queue<T> {

    private final ReentrantLock lock = new ReentrantLock();
    public Object[] array;

    public ImmutableQueue() {
        this.array = new Object[0];
    }

    @Override
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
     */
    public Queue<T> deQueue() {
        if (this.isEmpty()) {
            return this;
        }
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            Object[] elements = this.array;
            int len = elements.length;
            Object[] newElements = new Object[len - 1];
            System.arraycopy(elements, 1, newElements, 0, len - 1);
            this.array = newElements;
            return this;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public T head() {
        if (this.isEmpty()) {
            return null;
        }
        return (T) this.array[0];
    }

    @Override
    public boolean isEmpty() {
        return this.array == null || this.array.length == 0;
    }
}
