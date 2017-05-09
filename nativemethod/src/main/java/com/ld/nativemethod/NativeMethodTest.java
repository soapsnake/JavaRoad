package com.ld.nativemethod;

/**
 * Created by liudun on 2017/4/7.
 */
public class NativeMethodTest {

    public native String printWords(String arg);

    static{
        System.loadLibrary("NativeMethodTest");
    }

    public static void main(String[] args){
        NativeMethodTest nativeMethodTest = new NativeMethodTest();
        nativeMethodTest.printWords("hello world");
    }

}
