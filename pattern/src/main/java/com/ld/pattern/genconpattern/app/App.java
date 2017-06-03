package com.ld.pattern.genconpattern.app;


import com.ld.pattern.genconpattern.checker.Muti10;
import com.ld.pattern.genconpattern.generator.Intgen;
import com.ld.pattern.genconpattern.generator.IntgenImpl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        Intgen intgen = new IntgenImpl();
        //
        
        ExecutorService executorService = Executors.newCachedThreadPool();
        
       for(int i = 1;i<=10;i++)
    	 
    	   executorService.execute(new Muti10(intgen, i));
    	   executorService.shutdown();
       
    }
}
