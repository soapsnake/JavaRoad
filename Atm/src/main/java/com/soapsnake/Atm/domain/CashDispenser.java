package com.soapsnake.Atm.domain;

/**
 *
 * Created on 2020-05-28
 */
public abstract class CashDispenser {

    private int totalFiveDollarBills;
    private int totalTwentyDollarBills;

    public abstract boolean dispenseCash(double amount);
    public abstract boolean canDispenseCash();
}
