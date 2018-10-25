package com.ld.thinkinjava.enumm;

/**
 * Created by kevin on 2017/9/14.
 */
public class App {

    public static void main(String[] args) {
        for (Shrubbery shrubbery : Shrubbery.values()) {
            System.out.println(shrubbery + " : " + shrubbery.getDeclaringClass());
        }
    }
}
