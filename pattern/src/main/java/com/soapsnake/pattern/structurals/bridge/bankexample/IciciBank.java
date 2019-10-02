package com.soapsnake.pattern.structurals.bridge.bankexample;

public class IciciBank extends Bank {

    public IciciBank(Account account) {
        super(account);
    }

    @Override
    Account openAccount() {
        System.out.print("Open your account with ICICI Bank");
        return account;
    }
}
