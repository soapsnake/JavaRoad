package com.ld.thinkinjava.generics;

/**
 * Created by liudun on 2017/7/9.
 */
public abstract class GenericWithCreate<T> {
    final T element;

    GenericWithCreate() {
        element = create();
    }

    abstract T create();

}
