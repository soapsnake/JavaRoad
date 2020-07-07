package com.soapsnake.lab.exception;

/**
 * @author soapsnake <soapsnake@.com>
 * Created on 2020-04-30
 */
public class ExceptionTestor {

    public void test(int n) {
        if (n == 1) {
            throw new RuntimeException("此时抛异常");
        }
    }

    public static void main(String[] args) {
        ExceptionTestor exceptionTestor = new ExceptionTestor();

        try {
            exceptionTestor.test(1);
            System.out.println("can here 1");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }

        System.out.println("can herere 2" );
    }

}
