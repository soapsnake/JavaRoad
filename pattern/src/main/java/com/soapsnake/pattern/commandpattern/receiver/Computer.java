package com.soapsnake.pattern.commandpattern.receiver;

/**
 * @author Liu.Dun
 * 命令接受者,命令接受者和命令发起者完全解耦,不知道
 * 对自己发号施令的到底是个什么货色
 */
public class Computer {

    public Computer() {
    }

    public void turnOn() {
        System.out.println("computer has been turned ON!!!!!");
    }

    public void turnOff() {
        System.out.println("computer has been turned OFF!!!!");
    }
}
