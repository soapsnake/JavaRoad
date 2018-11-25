package com.soapsnake.concurrence.multithread.effective;

/**
 * Created by soapsnake on 2017/5/17.
 */
public interface SetObserver<E> {

    void added(ObservableSet<E> set, E element);
}
