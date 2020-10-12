package com.soapsnake.Atm.domain;

/**
 *
 * Created on 2020-05-28
 */
public abstract class BalanceInquiry extends Transaction {
    private int accountId;

    public abstract double getAccountId();
}
