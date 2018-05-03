package com.ld.multiutil;

import java.util.concurrent.Semaphore;

public class SemophoneExam {
    private static final int MAX_AVAILABLE = 100;
    private final Semaphore semaphore = new Semaphore(MAX_AVAILABLE, true);

    public Object getItem() throws InterruptedException {
        semaphore.acquire();
        return getNextAvailableItem();
    }

    public void putItem(Object x) {
        if (markAsUnused(x))
            semaphore.release();
    }

    // Not a particularly efficient data structure; just for demo

    protected Object[] items = new Object[]{};
    protected boolean[] used = new boolean[MAX_AVAILABLE];

    protected synchronized Object getNextAvailableItem() {
        for (int i = 0; i < MAX_AVAILABLE; ++i) {
            if (!used[i]) {
                used[i] = true;
                return items[i];
            }
        }
        return null; // not reached
    }

    protected synchronized boolean markAsUnused(Object item) {
        for (int i = 0; i < MAX_AVAILABLE; ++i) {
            if (item == items[i]) {
                if (used[i]) {
                    used[i] = false;
                    return true;
                } else
                    return false;
            }
        }
        return false;
    }
}
