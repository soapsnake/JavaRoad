package com.ld.genconpattern.app;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import com.ld.genconpattern.checker.Muti10;
import com.ld.genconpattern.generator.Intgen;
import com.ld.genconpattern.generator.IntgenImpl;

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
