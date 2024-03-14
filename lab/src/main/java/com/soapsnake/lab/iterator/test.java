package com.soapsnake.lab.iterator;

import java.util.Optional;

/**
 * Created on 2023-12-17
 */
public class test {

    public static void main(String[] args) {
        String a = null;
        Optional<String> b = Optional.empty();
        try {
            System.out.println("A: " + a.length());
            System.out.println("b:" + b.orElse(" ").length());

        } catch ( Exception es) {
            System.out.println("ecxception:" + a);
        } finally {

            a = "String";
            System.out.println(a.length());
            b  = Optional.ofNullable("");
            System.out.println(b.get().length());
        }
    }
}
