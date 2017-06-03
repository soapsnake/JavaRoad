package com.ld.multithread.effective;

/**
 * Created by liudun on 2017/5/17.
 */
public interface SetObserver<E> {

    void added(ObservableSet<E> set, E element);
}
