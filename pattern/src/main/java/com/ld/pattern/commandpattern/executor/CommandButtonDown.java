package com.ld.pattern.commandpattern.executor;

import com.ld.pattern.commandpattern.receiver.Computer;

/**
 * @author Liu.Dun
 * 命令的实际执行者,实际执行者才了解自己的作用对象是什么,也知道自己接受能够接收谁的命令
 */
public class CommandButtonDown implements Command {
    private Computer computer;

    public CommandButtonDown(Computer computer) {
        this.computer = computer;
    }

    public void executeCmd() {
        // Auto-generated method stub
        computer.turnOff();
    }

}
