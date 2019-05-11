package com.soapsnake.pattern.structuralPatterns.genconpattern.app;


import com.soapsnake.pattern.structuralPatterns.genconpattern.generator.Intgen;
import com.soapsnake.pattern.structuralPatterns.genconpattern.generator.IntgenImpl;
import com.soapsnake.pattern.structuralPatterns.genconpattern.checker.Muti10;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        Intgen intgen = new IntgenImpl();
        //

        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 1; i <= 10; i++)

            executorService.execute(new Muti10(intgen, i));
        executorService.shutdown();

    }
}
