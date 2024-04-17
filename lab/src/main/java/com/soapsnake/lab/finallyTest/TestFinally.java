package com.soapsnake.lab.finallyTest;

public class TestFinally {

    public static void main(String[] args) {
        System.out.println(test());
    }

    public static int test() {
        try {
            return 1;
        } finally {
            System.out.println("Finally block executed.");  //finally 块会在return语句执行前执行
        }
    }
}
