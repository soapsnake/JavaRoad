package com.ld.commandpattern.app;

import com.ld.commandpattern.executor.Command;
import com.ld.commandpattern.executor.CommandButtonDown;
import com.ld.commandpattern.executor.CommandButtonUP;
import com.ld.commandpattern.invoker.Switch;
import com.ld.commandpattern.receiver.Computer;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
       
        Computer computer = new Computer();
        
        Command cmdoff = new CommandButtonDown(computer);      
        Command cmdon = new CommandButtonUP(computer);
        
        Switch button = new Switch(cmdon,cmdoff);
        
        button.buttonUP();
        button.buttonDown();
    }
}
