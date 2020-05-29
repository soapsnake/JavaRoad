package com.soapsnake.Atm.domain;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-05-28
 */
public abstract class ATM {

    private int atmID;
    private Address location;

    private CashDispenser cashDispenser;
    private Keypad keypad;
    private Screen screen;
    private Printer printer;
    private CheckDeposit checkDeposit;
    private CashDeposit cashDeposit;

    public abstract boolean authenticateUser();
    public abstract boolean makeTransaction(Customer customer, Transaction transaction);
}
