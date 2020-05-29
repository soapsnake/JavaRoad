package com.soapsnake.Atm.domain;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-05-28
 */
public abstract class Account {

    private int accountNumber;
    private double totalBalance;
    private double availableBalance;

    public abstract double getAvailableBalance();
}
