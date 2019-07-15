package com.soapsnake.pattern.structurals.bridge;

public class BridgePatternTester {

    public static void main(String[] args) {
        Bank iciciBank = new IciciBank(new CurrentAccount());
        Account current = iciciBank.openAccount();
        current.accountType();

        Bank hdfcBank = new HdfcBank(new SavingAccount());
        Account saving = hdfcBank.openAccount();
        saving.accountType();

    }
}
