package com.soapsnake.pattern.structuralPatterns.commandpattern.app;

import com.soapsnake.pattern.structuralPatterns.commandpattern.executor.Command;
import com.soapsnake.pattern.structuralPatterns.commandpattern.executor.CommandButtonDown;
import com.soapsnake.pattern.structuralPatterns.commandpattern.executor.CommandButtonUP;
import com.soapsnake.pattern.structuralPatterns.commandpattern.invoker.Switch;
import com.soapsnake.pattern.structuralPatterns.commandpattern.receiver.Computer;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        Computer computer = new Computer();

        Command cmdoff = new CommandButtonDown(computer);
        Command cmdon = new CommandButtonUP(computer);

        Switch button = new Switch(cmdon, cmdoff);

        button.buttonUP();
        button.buttonDown();
    }
}
