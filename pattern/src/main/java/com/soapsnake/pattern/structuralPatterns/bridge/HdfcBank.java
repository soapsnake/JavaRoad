package com.soapsnake.pattern.structuralPatterns.bridge;

public class HdfcBank extends Bank {


    public HdfcBank(Account account) {
        super(account);
    }

    @Override
    Account openAccount() {
        System.out.print("Open your account with HDFC Bank");
        return account;
    }
}
