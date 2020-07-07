package com.soapsnake.Atm.domain;

/**
 *
 * Created on 2020-05-28
 */
public class Withdraw extends Transaction {
    private double amount;

    public double getAmount() {
        return 0;
    }

    @Override
    public boolean makeTransation() {
        return false;
    }
}
