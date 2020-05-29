package com.soapsnake.Atm.domain;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-05-28
 */
public class Transfer extends Transaction {
    private int destinationAccountNumber;

    public int getDestinationAccount() {
        return 0;
    }

    @Override
    public boolean makeTransation() {
        return false;
    }
}
