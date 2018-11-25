package com.soapsnake.nativemethod;

/**
 * Created by liudun on 2017/4/7.
 */
public class NativeMethodTest {

    static {
        System.loadLibrary("NativeMethodTest");
    }

    public static void main(String[] args) {
        NativeMethodTest nativeMethodTest = new NativeMethodTest();
        nativeMethodTest.printWords("hello world");
    }

    public native String printWords(String arg);

}
