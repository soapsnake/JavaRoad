package com.soapsnake.Atm.domain;

import java.util.Date;

import com.soapsnake.Atm.enums.TransactionStatus;

/**
 *
 * Created on 2020-05-28
 */
public abstract class Transaction {
    private int transactionId;
    private Date creationTime;
    private TransactionStatus status;

    public abstract boolean makeTransation();
}
