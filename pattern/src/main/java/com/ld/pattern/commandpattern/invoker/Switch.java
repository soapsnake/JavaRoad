package com.ld.pattern.commandpattern.invoker;

import com.ld.pattern.commandpattern.executor.Command;

/**
 * @author Liu.Dun
 * 调用者,这里是一个开关,开关和命令接收者解耦,虽然知道自己能够发出命令
 * 但是并不知道自己发出的命令会被什么东西接收
 */
public class Switch {
    private Command bup;
    private Command bdown;

    public Switch(Command bup, Command bdown) {
        this.bup = bup;
        this.bdown = bdown;
    }

    public void buttonUP() {

        bup.executeCmd();
    }

    public void buttonDown() {
        bdown.executeCmd();
    }

}
