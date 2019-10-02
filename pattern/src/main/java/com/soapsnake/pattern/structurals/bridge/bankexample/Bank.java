package com.soapsnake.pattern.structurals.bridge.bankexample;

public abstract class Bank {

    protected Account account;
    public Bank(Account account) {
        this.account = account;
    }

    abstract Account openAccount();
}
