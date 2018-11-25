package com.soapsnake.dubbo.provider.util;

/**
 * @author soapsnake
 * @date 2018/10/29
 */
public class Holder<T> {

    private volatile T value;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
