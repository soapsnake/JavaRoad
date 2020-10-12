package com.soapsnake.Atm.domain;

/**
 *
 * Created on 2020-05-28
 */
public class SavingAccount extends Account {

    private double withdrawLimit;

    @Override
    public double getAvailableBalance() {
        return 0;
    }
}
